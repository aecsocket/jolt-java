import io.github.krakowski.jextract.JextractTask
import io.github.krakowski.jextract.LibraryDefinition

plugins {
    id("java-conventions")
}

publishIfNeeded()

fun LibraryDefinition.defaults() {
    className.set("JoltPhysicsC")
}

tasks {
    register<JextractTask>("generateHeaders") {
        toolchain.convention(org.gradle.internal.jvm.Jvm.current().javaHome.absolutePath)

        header("$joltDir/JoltC/JoltPhysicsC.h") {
            defaults()
            targetPackage.set("jolt.headers")
        }

        header("$joltDir/JoltC/JoltPhysicsC.h") {
            defaults()
            targetPackage.set("jolt.headers_f")
        }

        header("$joltDir/JoltC/JoltPhysicsC.h") {
            defaults()
            targetPackage.set("jolt.headers_d")
            definedMacros.add("JPH_DOUBLE_PRECISION")
        }

        doLast {
            copy {
                from("${outputDir.get()}")
                into("$projectDir/src/main/java")
            }
        }
    }
}
