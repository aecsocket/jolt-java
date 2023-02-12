plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isLinux }
    bindingsFileName.set("libjolt-jni-bindings.so")
    destInnerDir.set("linux")
}

val makeWorkers = minOf(32, Runtime.getRuntime().availableProcessors())

tasks {
    register<Exec>("generateNatives") {
        group = "natives"
        val buildDir = "$rootDir/JoltPhysics/Build"
        val outputDir = "$buildDir/Linux_${buildType.key}"

        doFirst {
            println("Generating Linux natives ($buildType $flavor $features)")
            delete(outputDir)
        }

        workingDir = File(buildDir)
        commandLine = listOf("$buildDir/cmake_linux_clang_gcc.sh", buildType.key)
        features.forEach { feature ->
            environment[feature.cmakeFlag()] = "ON"
        }

        doLast {
            exec {
                workingDir = File(outputDir)
                commandLine = listOf("make", "-j$makeWorkers")
            }
        }
    }
}
