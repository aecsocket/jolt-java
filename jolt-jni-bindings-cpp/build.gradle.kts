plugins {
    id("cpp-library")
}

val buildType = ext.get(BUILD_TYPE) as JoltBuildType
val flavor = ext.get(FLAVOR) as JoltFlavor
val features = listOfNotNull(
    if (flavor == JoltFlavor.DP) JoltFeature.DOUBLE_PRECISION else null,
    //JoltFeature.USE_SSE4_1,
    //JoltFeature.USE_SSE4_2,
    //JoltFeature.USE_AVX,
    //JoltFeature.USE_AVX2,
    //JoltFeature.USE_AVX512,
    //JoltFeature.USE_LZCNT,
    //JoltFeature.USE_TZCNT,
    //JoltFeature.USE_F16C,
    //JoltFeature.USE_FMADD,
)

val bindings = projects.joltJni.dependencyProject

evaluationDependsOn(bindings.path)

library {
    binaries.configureEach {
        val compileTask = compileTask.get()
        // generate C++
        compileTask.dependsOn(bindings.tasks["compileJava"])

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
        when (buildType) {
            JoltBuildType.DEBUG -> {
                compileTask.macros["JPH_PROFILE_ENABLED"] = ""
                compileTask.macros["JPH_ENABLE_ASSERTS"] = ""
            }
            JoltBuildType.RELEASE -> {}
            JoltBuildType.DISTRIBUTION -> {}
        }
        features.forEach { feature ->
            compileTask.macros[feature.macro()] = ""
        }

        // include generated C++
        compileTask.includes("${bindings.buildDir}/generated/sources/annotationProcessor/java/main/jolt/")
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
        }

        workingDir = File(buildDir)
        JoltFeature.values().forEach { feature ->
            environment[feature.cmakeFlag()] = if (feature in features) "ON" else "OFF"
        }
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
