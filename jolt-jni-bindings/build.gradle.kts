plugins {
    id("base-conventions")
    id("cpp-library")
}

val baseProject: Project = projects.joltJni.dependencyProject

library {
    binaries.configureEach {
        val compileTask = compileTask.get()
        compileTask.compilerArgs.addAll(listOf("--std=c++17", "-flto"))
        // include generated native code
        compileTask.dependsOn(baseProject.tasks["compileJava"])
        compileTask.includes("${baseProject.buildDir}/generated/sources/annotationProcessor/java/main/jolt")

        // include JNI
        val os = targetPlatform.targetMachine.operatingSystemFamily
        compileTask.includes.from(baseProject.tasks.named<WriteToolchainPath>("writeToolchainPath").flatMap {
            it.outputFile.map { f -> f.asFile.readText() + "/include" }
        })
        compileTask.includes.from(baseProject.tasks.named<WriteToolchainPath>("writeToolchainPath").flatMap {
            it.outputFile.map { f ->
                val javaInclude = f.asFile.readText() + "/include"
                when {
                    os.isLinux -> "$javaInclude/linux"
                    os.isWindows -> "$javaInclude/win32"
                    os.isMacOs -> "$javaInclude/darwin"
                    else -> throw IllegalStateException("Unsupported OS $os")
                }
            }
        })

        // include Jolt
        compileTask.includes("$rootDir/JoltPhysics")
        when (buildType) {
            JoltBuildType.DEBUG -> {
                compileTask.macros["JPH_PROFILE_ENABLED"] = "1"
                compileTask.macros["JPH_ENABLE_ASSERTS"] = "1"
            }
            else -> {}
        }
        features.forEach { feature ->
            compileTask.macros[feature.macro()] = "1"
        }
    }
}

tasks {
    withType<LinkSharedLibrary> {
        // todo
        libs.from("$rootDir/JoltPhysics/Build/Linux_${buildType.key}/libJolt.a")
    }
}
