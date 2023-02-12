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

        doFirst {
            println("Assembling natives $buildType $flavor $features")
        }

        workingDir = projectDir
        commandLine = listOf(
            "cmake",
            "-S=.",
            "-B=build",
            "-G=Ninja",
            "-DCMAKE_BUILD_TYPE=$buildType.key",
            "-DCMAKE_CXX_COMPILER=g++",
        )
        features.forEach { feature ->
            environment[feature.cmakeFlag()] = "ON"
        }

        doLast {
            exec {
                workingDir = buildDir
                commandLine = listOf("ninja", "-j$makeWorkers")
            }
        }
    }
}
