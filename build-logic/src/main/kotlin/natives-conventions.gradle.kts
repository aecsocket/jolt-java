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
                bindings.tasks.withType<LinkSharedLibrary> {
                    this@jar.dependsOn(this)
                }
                from("${bindings.buildDir}/lib/main/release/${nativesExt.bindingsFileName.get()}") {
                    into("jolt/${nativesExt.destInnerDir.get()}")
                }
            }
        }
    }
}
