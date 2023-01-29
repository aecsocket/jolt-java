plugins {
    id("cpp-library")
    id("java")
}

allprojects {
    group = "io.gitlab.aecsocket"
    version = "0.1.0"
    description = "Java JNI bindings for the Jolt physics engine"
}

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

library {
    binaries.configureEach {
        val compileTask = compileTask.get()
        // generate headers
        compileTask.dependsOn(":compileJava")

        // include JNI
        val javaInclude = "${org.gradle.internal.jvm.Jvm.current().javaHome}/include"
        compileTask.includes(javaInclude)
        val os = targetPlatform.targetMachine.operatingSystemFamily
        when {
            os.isLinux -> compileTask.includes("$javaInclude/linux")
            os.isWindows -> compileTask.includes("$javaInclude/win32")
            os.isMacOs -> compileTask.includes("$javaInclude/darwin")
            else -> throw IllegalStateException("Unsupported OS $os")
        }

        // include Jolt
        compileTask.includes("$rootDir/JoltPhysics")
    }
}

tasks {
    /*
    1. git submodule update -> Jolt headers
    2. : -> build bindings for platform -> libjolt-jni.so
    3. :jolt-jni-natives-linux -> copy libjolt-jni.so to src/main/resources
    4. :jolt-jni-test -> runtimeOnly(:jolt-jni-natives-linux)
     */

    compileJava {
        options.headerOutputDirectory.set(File("$projectDir/src/main/cpp"))
    }

    val makeWorkers = minOf(32, Runtime.getRuntime().availableProcessors())
    JoltBuildType.values().forEach { buildType ->
        register<Exec>("generateNativesLinux${buildType.key}") {
            group = "natives"
            val buildDir = "$rootDir/JoltPhysics/Build"
            val outputDir = "$buildDir/Linux_${buildType.key}"

            doFirst {
                delete(outputDir)
            }

            workingDir = File(buildDir)
            commandLine = listOf("$buildDir/cmake_linux_clang_gcc.sh", buildType.key)

            doLast {
                // TODO modify build script to add -fPIC
                // append:
                /*
                # Jolt-JNI
                if ("${CMAKE_SYSTEM_NAME}" STREQUAL "Linux" OR "${CMAKE_SYSTEM_NAME}" STREQUAL "Darwin" OR "${CMAKE_SYSTEM_NAME}" STREQUAL "iOS" OR MINGW OR EMSCRIPTEN)
	                set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
	            endif()
                 */

                exec {
                    workingDir = File(outputDir)
                    commandLine = listOf("make", "-j$makeWorkers")
                }

                exec {
                    workingDir = File(outputDir)
                    commandLine = listOf("$outputDir/UnitTests")
                }
            }
        }
    }

    register("generateNativesLinux") {
        group = "natives"
        dependsOn("generateNativesLinux${JoltBuildType.Default.key}")
    }

    withType<LinkSharedLibrary> {
        val os = org.gradle.internal.os.OperatingSystem.current()
        when {
            // TODO different build types
            os.isLinux -> libs.from("$rootDir/JoltPhysics/Build/Linux_${JoltBuildType.Default.key}/libJolt.a")
            else -> throw IllegalStateException("Unsupported OS $os")
        }
    }
}
