plugins {
    id("cpp-library")
}

val buildType = ext.get(BUILD_TYPE) as JoltBuildType
val flavor = ext.get(FLAVOR) as JoltFlavor

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
    val makeWorkers = minOf(32, Runtime.getRuntime().availableProcessors())

    val nativesLinux = register<Exec>("generateNativesLinux") {
        group = "natives"
        val buildDir = "$rootDir/JoltPhysics/Build"
        val outputDir = "$buildDir/Linux_${buildType.key}"

        doFirst {
            delete(outputDir)

            // TODO modify build script to add -fPIC
            // append:
            /*
            # Jolt-JNI
            if ("${CMAKE_SYSTEM_NAME}" STREQUAL "Linux" OR "${CMAKE_SYSTEM_NAME}" STREQUAL "Darwin" OR "${CMAKE_SYSTEM_NAME}" STREQUAL "iOS" OR MINGW OR EMSCRIPTEN)
                set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
            endif()
             */
        }

        workingDir = File(buildDir)
        commandLine = listOf("$buildDir/cmake_linux_clang_gcc.sh", buildType.key)

        doLast {
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

    val nativesWindows = register<Exec>("generateNativesWindows") {
        group = "natives"
        val buildDir = "$rootDir\\JoltPhysics\\Build"
        val outputDir = "$buildDir\\MinGW_${buildType.key}"

        doFirst {
            delete(outputDir)
        }

        workingDir = File(buildDir)
        commandLine = listOf(".\\cmake_windows_mingw.sh", buildType.key)

        doLast {
            exec {
                workingDir = File(buildDir)
                commandLine = listOf("cmake", "--build", "MinGW_Debug")
            }

            exec {
                workingDir = File(outputDir)
                commandLine = listOf("$buildDir\\UnitTests.exe")
            }
        }
    }

    val os = org.gradle.internal.os.OperatingSystem.current()
    when {
        os.isLinux -> {
            register("generateNatives") {
                dependsOn(nativesLinux)
            }

            withType<LinkSharedLibrary> {
                libs.from("$rootDir/JoltPhysics/Build/Linux_${buildType.key}/libJolt.a")
            }
        }
        os.isWindows -> {
            register("generateNatives") {
                dependsOn(nativesWindows)
            }

            withType<LinkSharedLibrary> {
                libs.from("$rootDir/JoltPhysics/Build/Windows_${buildType.key}/Jolt.dll")
            }
        }
        os.isMacOsX -> {
            withType<LinkSharedLibrary> {
                libs.from("$rootDir/JoltPhysics/Build/XCode_MacOS_${buildType.key}/libJolt.dylib")
            }
        }
        else -> throw IllegalStateException("Unsupported OS $os")
    }
}
