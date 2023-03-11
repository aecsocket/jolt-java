import org.gradle.internal.os.OperatingSystem

plugins {
    id("java-conventions")
}

val nativesExt = extensions.create("natives", NativesExtension::class)
val workers = kotlin.math.min(32, Runtime.getRuntime().availableProcessors())

afterEvaluate {
    val os = OperatingSystem.current()
    if (!nativesExt.platformPredicate.get().test(os)) return@afterEvaluate

    val nativesBuildDir = "$buildDir/natives"

    // only publish if we can actually build the artifact
    apply(plugin = "publishing-conventions")

    tasks {
        val assembleNatives = register<Exec>("assembleNatives") {
            group = "build natives"

            doFirst {
                println("Assembling natives $buildType $buildFlavor ${buildFeatures.joinToString(" ")}")
            }

            workingDir = file(joltDir)
            commandLine = listOf(
                "cmake",
                "-S", ".",                                  // source at `JoltPhysicsC/`
                "-B", file(nativesBuildDir).absolutePath,   // put makefiles into this project's `build/natives/`
                "-G", nativesExt.generator.get(),           // use the platform-specific generator
                "-DCMAKE_BUILD_TYPE=${buildType.key}",      // release build type
            )
            JoltBuildFeature.values().forEach { feature ->
                environment[feature.cmakeFlag()] = if (buildFeatures.contains(feature)) "ON" else "OFF"
            }

            doLast {
                exec {
                    workingDir = file(nativesBuildDir)
                    commandLine = listOf(
                        nativesExt.generatorBinary.get(),
                        "-j$workers"
                    )
                }
            }
        }

        jar {
            dependsOn(assembleNatives.get())
            from("$nativesBuildDir/${nativesExt.sourceLibraryName.get()}") {
                into("jolt/${nativesExt.destDir.get()}")
            }
        }
    }
}

//val makeWorkers = kotlin.math.min(32, Runtime.getRuntime().availableProcessors())
//
//afterEvaluate {
//    val os = OperatingSystem.current()
//    if (nativesExt.platformPredicate.get()(os)) {
//        apply(plugin = "publishing-conventions")
//
//        tasks {
//            val assembleNatives = register<Exec>("assembleNatives") {
//                dependsOn(":jolt-jni:compileJava")
//                val bindingsDir = File("$rootDir/JoltJNIBindings")
//
//                doFirst {
//                    println("Assembling natives $buildType $buildFlavor $buildFeatures")
//                }
//
//                workingDir = bindingsDir
//                commandLine = nativesExt.buildScriptBase.get() + listOf(
//                    buildDir.absolutePath,
//                    buildType.key,
//                    "$makeWorkers"
//                )
//                buildFeatures.forEach { feature ->
//                    environment[feature.cmakeFlag()] = "ON"
//                }
//            }
//
//            jar {
//                dependsOn(assembleNatives.get())
//                from("$buildDir/${nativesExt.sourceLibraryName.get()}") {
//                    into("jolt/")
//                    rename { nativesExt.targetLibraryName.get() }
//                }
//            }
//        }
//    }
//}
