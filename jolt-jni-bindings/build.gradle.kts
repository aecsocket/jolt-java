import kotlin.math.min

plugins {
    id("base-conventions")
}

val javaSource: Project = projects.joltJni.dependencyProject
val makeWorkers = min(32, Runtime.getRuntime().availableProcessors())

tasks {
    register<Delete>("clean") {
        delete("$buildDir")
    }

    register<Exec>("assembleNatives") {
        dependsOn(javaSource.tasks["compileJava"])

        val buildDir = "build"

        doFirst {
            println("Assembling natives $buildType $flavor $features")
        }

        workingDir = projectDir
        commandLine = listOf("cmake", "-S.", "-B$buildDir", "-G Unix Makefiles", "-DCMAKE_BUILD_TYPE=${buildType.key}", "-DCMAKE_CXX_COMPILER=clang++")
        features.forEach { feature ->
            environment[feature.cmakeFlag()] = "ON"
        }

        doLast {
            exec {
                workingDir = projectDir.resolve(buildDir)
                commandLine = listOf("make", "-j$makeWorkers")
            }
        }
    }
}
