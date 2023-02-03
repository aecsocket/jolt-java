package jolt.jni

import java.io.Writer
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

fun Writer.writeLine(str: String) = write(str + '\n')

@SupportedAnnotationTypes("jolt.jni.*")
class JniAnnotationProcessor : AbstractProcessor() {
    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (annotations.isEmpty()) return false
        val jniHeader = processingEnv.filer.createResource(
            StandardLocation.SOURCE_OUTPUT,
            "",
            "jolt/JoltJNI.h"
        )
        jniHeader.openWriter().use { writer ->
            writer.writeLine("#include <jni.h>")
            writer.writeLine("""extern "C" {""")
            roundEnv.getElementsAnnotatedWith(JniBind::class.java).forEach { element ->
                //processingEnv.filer.createResource()
            }
            writer.writeLine("}")
        }
        return true
    }
}
