import org.gradle.internal.os.OperatingSystem

plugins {
    id("java-conventions")
}

val bindings = project(":jolt-jni-bindings")
val nativesExt = extensions.create("natives", NativesExtension::class)
val buildType = JoltBuildType.DEBUG // TODO

dependencies {
    implementation(project(":jolt-jni"))
}

val makeWorkers = minOf(32, Runtime.getRuntime().availableProcessors())

afterEvaluate {
    val os = OperatingSystem.current()
    if (nativesExt.platformPredicate.get()(os)) {
        apply(plugin = "publishing-conventions")

        tasks {
            register<Exec>("generateNatives") {
                group = "natives"
                val buildDir = "$rootDir/JoltPhysics/Build"
                val outputDir = "$buildDir/Linux_${buildType.key}"

                doFirst {
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

            jar {
                bindings.tasks.withType<LinkSharedLibrary> {
                    this@jar.dependsOn(this)
                }
                from("${bindings.buildDir}/lib/main/debug/${nativesExt.bindingsFileName.get()}") {
                    into("jolt/${nativesExt.destInnerDir.get()}")
                }
            }
        }
    }
}
