package jolt.jni

import java.io.Writer
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror
import javax.tools.StandardLocation
import kotlin.reflect.KClass

private fun Writer.writeLine(str: String = "") = write(str + '\n')

private fun <T> List<List<T>>.flatJoin(separator: List<T> = emptyList()): List<T> {
    val res = mutableListOf<T>()
    forEachIndexed { idx, child ->
        res += child
        if (idx < size - 1)
            res += separator
    }
    return res
}

private val newline = listOf("")

// https://docs.oracle.com/javase/7/docs/technotes/guides/jni/spec/design.html
private fun mangleMethodName(name: String) = name
    .replace("_", "_1")
    .replace(";", "_2")
    .replace("[", "_3")

private fun mangleType(type: TypeMirror) = when (type.toString()) {
    "void" -> "V"
    "boolean" -> "Z"
    "char" -> "C"
    "byte" -> "B"
    "short" -> "S"
    "int" -> "I"
    "float" -> "F"
    "long" -> "J"
    "double" -> "D"
    else -> "L${type.toString().replace('.', '/')};"
}

private fun mangleMethod(className: String, methodName: String) =
    "Java_${className.replace('.', '_')}_${mangleMethodName(methodName)}"

private fun TypeMirror.cType() = when (toString()) {
    "void" -> "void"
    else -> "j$this"
}

@SupportedAnnotationTypes("jolt.jni.*")
class JniAnnotationProcessor : AbstractProcessor() {
    data class CallbackBinding(
        val classPackage: String,
        val className: String,
        val methodName: String,
        val params: List<TypeMirror>,
        val returns: TypeMirror
    )

    data class MethodBinding(
        val name: String,
        val params: List<String>,
        val returns: String,
        val body: List<String>
    )

    class ClassModel(val priority: Int) {
        val includes = ArrayList<List<String>>()
        val callbackBindings = ArrayList<CallbackBinding>()
        val headers = ArrayList<List<String>>()
        val init = ArrayList<List<String>>()
        val methodBindings = ArrayList<MethodBinding>()
    }

    class CppModel {
        val originElements = HashSet<Element>()
        val classes = ArrayList<ClassModel>()
    }

    val models = HashMap<String, CppModel>()
    val errors = ArrayList<String>()

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (roundEnv.processingOver()) {
            if (errors.isNotEmpty()) {
                throw IllegalStateException(
                    "JNI annotation processing failed:\n" +
                    errors.joinToString("\n") { " - $it" }
                )
            }

            models.forEach { (path, model) ->
                val outFile = processingEnv.filer.createResource(
                    StandardLocation.SOURCE_OUTPUT,
                    "",
                    "$path.cpp",
                    *model.originElements.toTypedArray()
                )

                val sections = ArrayList<List<String>>()

                val classModels = model.classes.sortedBy { it.priority }

                // includes
                val includes = LinkedHashSet(listOf("<jni.h>") +
                    classModels
                        .flatMap { it.includes }
                        .flatten()
                )
                sections += includes.map { include ->
                    if (include.startsWith("<") && include.endsWith(">"))
                        "#include $include"
                    else
                        """#include "$include""""
                }

                // callback bindings
                data class CallbackMethod(
                    val name: String,
                    val signature: String
                )

                data class CallbackClass(
                    val simpleName: String
                ) {
                    val methods = ArrayList<CallbackMethod>()
                }

                val callbackClasses = LinkedHashMap<String, CallbackClass>()
                classModels.flatMap { it.callbackBindings }.forEach { callback ->
                    val className = "${callback.classPackage}.${callback.className}"
                    val callbackClass = callbackClasses.computeIfAbsent(className) { CallbackClass(callback.className) }
                    callbackClass.methods += CallbackMethod(
                        callback.methodName,
                        "(${callback.params.joinToString("") { mangleType(it) }})${mangleType(callback.returns)}"
                    )
                }

                val callbackFields = ArrayList<String>()
                val jniInit = ArrayList<String>()
                callbackClasses.toList().forEachIndexed { idx, (className, callback) ->
                    if (callback.methods.isEmpty()) return@forEachIndexed

                    val classVar = "c$idx"
                    jniInit += listOf(
                        """jclass $classVar = env->FindClass("${className.replace('.', '/')}");""",
                        "if (env->ExceptionCheck()) return;"
                    )
                    callback.methods.forEach { method ->
                        val fieldName = "${callback.simpleName}_${method.name.removePrefix("_")}"
                        callbackFields += "jmethodID $fieldName;"
                        jniInit += """$fieldName = env->GetMethodID($classVar, "${method.name}", "${method.signature}");"""
                    }
                }

                sections += callbackFields

                // headers
                sections += classModels.flatMap { it.headers }.flatJoin(newline)

                // JNI init
                jniInit += classModels.flatMap { it.init }.flatten()
                sections += listOf("void JNIInit(JNIEnv* env) {") + jniInit.map { "    $it" } + "}"

                // method bindings
                sections += listOf("""extern "C" {""") + classModels.flatMap { it.methodBindings }
                    .map { method ->
                        listOf(
                            "JNIEXPORT ${method.returns} JNICALL ${method.name}",
                            "  (${method.params.joinToString(", ")}) {"
                        ) + method.body.map { "    $it" } + "}"
                    }
                    .flatJoin(newline) + "}"

                outFile.openWriter().use { writer ->
                    writer.writeLine("// This file was auto-generated by JniGlue - do not edit!")
                    sections.flatJoin(newline).forEach { writer.writeLine(it) }
                }
            }
        } else {
            roundEnv.getElementsAnnotatedWith(JniNative::class.java).forEach { classElement ->
                val jniNative = classElement.getAnnotation(JniNative::class.java)
                val jniPriority = classElement.getAnnotation(JniPriority::class.java)?.value ?: NativePriority.NORMAL
                val cppModel = models.computeIfAbsent(jniNative.value) { CppModel() }
                val classModel = ClassModel(jniPriority).also { cppModel.classes += it }
                cppModel.originElements += classElement

                val packageName = (classElement.enclosingElement as PackageElement).qualifiedName
                val className = "$packageName.${classElement.simpleName}"

                classElement.getAnnotation(JniInclude::class.java)?.let { jniInclude ->
                    classModel.includes += jniInclude.value.lines()
                }

                classElement.getAnnotation(JniHeader::class.java)?.let { jniHeader ->
                    classModel.headers += jniHeader.value.lines()
                }

                classElement.getAnnotation(JniInit::class.java)?.let { jniInit ->
                    classModel.init += jniInit.value.lines()
                }

                val jniType: JniType? = classElement.getAnnotation(JniType::class.java)

                fun bindMethod(element: ExecutableElement, body: List<String>) {
                    cppModel.originElements += element

                    classModel.methodBindings += MethodBinding(
                        mangleMethod(className, element.simpleName.toString()),
                        listOf(
                            "JNIEnv* env",
                            if (element.modifiers.contains(Modifier.STATIC)) "jclass cls" else "jobject obj",
                        ) + element.parameters.map { "${it.asType().cType()} ${it.simpleName}" },
                        element.returnType.cType(),
                        body
                    )
                }

                classElement.enclosedElements.forEach { childElement ->
                    when (childElement) {
                        is ExecutableElement -> {
                            val methodName = childElement.simpleName.toString()

                            fun error(annotationType: KClass<*>, message: String) {
                                errors += "Method $className.$methodName is annotated with ${annotationType.simpleName}, but $message"
                            }

                            fun requireType(annotationType: KClass<*>) =
                                error(annotationType, "class is not annotated with ${JniType::class.simpleName}")

                            childElement.getAnnotation(JniBind::class.java)?.let { jniBind ->
                                bindMethod(childElement, jniBind.value.lines())
                            }

                            childElement.getAnnotation(JniBindSelf::class.java)?.let { jniBindSelf ->
                                val selfType = jniType?.value ?: run {
                                    requireType(JniBindSelf::class)
                                    return@let
                                }

                                val params = childElement.parameters
                                if (
                                    params.isEmpty()
                                    || params[0].simpleName.toString() != "_a"
                                    || params[0].asType().toString() != "long"
                                ) {
                                    error(JniBindSelf::class, "requires 1st argument `long _a` = `address`")
                                    return@let
                                }

                                bindMethod(childElement, listOf(
                                    "auto* self = ($selfType*) _a;"
                                ) + jniBindSelf.value.lines())
                            }

                            childElement.getAnnotation(JniBindDelete::class.java)?.let {
                                val selfType = jniType?.value ?: run {
                                    requireType(JniBindDelete::class)
                                    return@let
                                }

                                val params = childElement.parameters
                                if (
                                    params.size != 1
                                    || params[0].simpleName.toString() != "_a"
                                    || params[0].asType().toString() != "long"
                                ) {
                                    error(JniBindDelete::class, "requires one argument `long _a` = `address`")
                                    return@let
                                }

                                bindMethod(childElement, listOf("delete ($selfType*) _a;"))
                            }

                            childElement.getAnnotation(JniBindInit::class.java)?.let {
                                bindMethod(childElement, listOf("JNIInit(env);"))
                            }

                            childElement.getAnnotation(JniCallback::class.java)?.let {
                                classModel.callbackBindings += CallbackBinding(
                                    packageName.toString(),
                                    classElement.simpleName.toString(),
                                    methodName,
                                    childElement.parameters.map { param -> param.asType() },
                                    childElement.returnType
                                )
                            }
                        }
                    }
                }
            }
        }
        return false
    }
}
