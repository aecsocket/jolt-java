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
        val osFamily = targetPlatform.targetMachine.operatingSystemFamily
        compileTask.includes(javaInclude)
        when {
            osFamily.isLinux -> compileTask.includes("$javaInclude/linux")
            osFamily.isWindows -> compileTask.includes("$javaInclude/win32")
            osFamily.isMacOs -> compileTask.includes("$javaInclude/darwin")
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
        // todo
        libs.from("$rootDir/JoltPhysics/Build/Linux_Debug/libJolt.a")
    }
}
