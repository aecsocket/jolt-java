import org.gradle.internal.os.OperatingSystem

plugins {
    id("java-conventions")
}

val bindings = project(":jolt-jni-bindings")
val nativesExt = extensions.create("natives", NativesExtension::class)

dependencies {
    implementation(project(":jolt-jni"))
}

afterEvaluate {
    val os = OperatingSystem.current()
    if (nativesExt.platformPredicate.get()(os)) {
        apply(plugin = "publishing-conventions")

        tasks {
            jar {
                dependsOn(bindings.tasks["assembleNatives"])
                from("${bindings.buildDir}/${nativesExt.bindingsFileName.get()}") {
                    into("jolt/${nativesExt.destInnerDir.get()}")
                }
            }
        }
    }
}