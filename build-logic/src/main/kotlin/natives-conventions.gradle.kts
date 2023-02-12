import org.gradle.internal.os.OperatingSystem

plugins {
    id("java-conventions")
}

val nativesExt = extensions.create("natives", NativesExtension::class)

dependencies {
    implementation(project(":jolt-jni"))
}

val makeWorkers = kotlin.math.min(32, Runtime.getRuntime().availableProcessors())

afterEvaluate {
    val os = OperatingSystem.current()
    if (nativesExt.platformPredicate.get()(os)) {
        apply(plugin = "publishing-conventions")

        tasks {
            val assembleNatives = register<Exec>("assembleNatives") {
                dependsOn(":jolt-jni:compileJava")

                doFirst {
                    println("Assembling natives $buildType $flavor $features")
                }

                workingDir = File("$rootDir/JoltJNIBindings")
                commandLine = listOf(
                    "./${nativesExt.buildScriptName.get()}",
                    buildDir.absolutePath,
                    buildType.key,
                    "$makeWorkers"
                )
                features.forEach { feature ->
                    environment[feature.cmakeFlag()] = "ON"
                }
            }

            jar {
                dependsOn(assembleNatives.get())
                from("$buildDir/${nativesExt.sourceLibraryName.get()}") {
                    into("jolt/")
                    rename { nativesExt.targetLibraryName.get() }
                }
            }
        }
    }
}
