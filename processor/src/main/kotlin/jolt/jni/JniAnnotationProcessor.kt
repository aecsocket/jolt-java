package jolt.jni

import java.io.Writer
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

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

@SupportedAnnotationTypes("jolt.jni.*")
class JniAnnotationProcessor : AbstractProcessor() {
    data class IncludeEntry(
        val entry: String,
        val priority: Int
    )

    class CppModel {
        val includes = ArrayList<IncludeEntry>().apply {
            add(IncludeEntry("<jni.h>", IncludePriority.EARLIEST))
        }
        val header = ArrayList<String>()
        val methodBindings = ArrayList<List<String>>()
    }

    val models = HashMap<String, CppModel>()

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (roundEnv.processingOver()) {
            models.forEach { (path, model) ->
                val outFile = processingEnv.filer.createResource(
                    StandardLocation.SOURCE_OUTPUT,
                    "",
                    "$path.cpp"
                )

                val sections = ArrayList<List<String>>()

                val includes = LinkedHashSet(model.includes
                    .sortedBy { it.priority }
                    .map { it.entry })

                sections += includes.map { include ->
                    if (include.startsWith("<") && include.endsWith(">"))
                        "#include $include"
                    else
                        """#include "$include""""
                }

                sections += model.header

                sections += listOf("""extern "C" {""") + model.methodBindings.flatJoin(newline) + "}"

                outFile.openWriter().use { writer ->
                    sections.flatJoin(newline).forEach { writer.writeLine(it) }
                }
            }
        } else {
            roundEnv.getElementsAnnotatedWith(JniNative::class.java).forEach { classElement ->
                val jniNative = classElement.getAnnotation(JniNative::class.java)
                val model = models.computeIfAbsent(jniNative.value) { CppModel() }

                classElement.getAnnotation(JniInclude::class.java)?.let { jniInclude ->
                    model.includes += jniInclude.value.lineSequence()
                        .map { IncludeEntry(it, jniInclude.priority) }
                }

                classElement.getAnnotation(JniHeader::class.java)?.let { jniHeader ->
                    model.header += jniHeader.value.lineSequence()
                }

                classElement.enclosedElements.forEach { childElement ->
                    println(childElement.asType())
                }
            }

//            roundEnv.getElementsAnnotatedWith(JniBind::class.java).forEach { element ->
//                val jniBind = element.getAnnotation(JniBind::class.java)
//                val jniNative = element.enclosingElement.getAnnotation(JniNative::class.java)
//                    ?: throw IllegalStateException("Element $element is annotated ${JniBind::class.simpleName}, but enclosing element ${element.enclosingElement} is not annotated ${JniNative::class.simpleName}")
//
//                val model = models.computeIfAbsent(jniNative.value) { CppModel() }
//                model.header += jniNative.header.split('\n')
//
//                val funcName = "Java_${element.enclosingElement}_${element.simpleName}"
//
//                jniMethods += listOf(
//                    "JNIEXPORT jvoid JNICALL $funcName",
//                    "  (JNIEnv* env, jobject obj) {"
//                ) + jniBind.value.split('\n').map { "    $it" } + "}"
//            }
//
//            roundEnv.getElementsAnnotatedWith(JniSelfBind::class.java).forEach { element ->
//                val selfBind = element.getAnnotation(JniSelfBind::class.java)
//                val selfType = element.enclosingElement.getAnnotation(JniSelfType::class.java) ?: return@forEach
//
//                val funcName = "Java_${element.enclosingElement.simpleName}_${element.simpleName}"
//                val typeName = selfType.value
//
//                jniMethods += listOf(
//                    "JNIEXPORT jvoid JNICALL $funcName",
//                    "  (JNIEnv* env, jobject obj) {",
//                    "    $typeName* self = ($typeName*) address;"
//                ) + selfBind.value.split('\n').map { "    $it" } + "}"
//            }
        }
        return false
    }
}
