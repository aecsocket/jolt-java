plugins {
    id("java")
    id("maven-publish")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

dependencies {
    implementation(libs.jniGlueAnnotations)
    annotationProcessor(libs.jniGlueProcessor)

    implementation(libs.findBugs)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersion))
    withSourcesJar()
    withJavadocJar()
}

// so that `publishing` can get `tasks.jar`
listOf(
    projects.joltJniNativesLinux,
    projects.joltJniNativesWindows,
    projects.joltJniNativesMacos,
    projects.joltJniNativesMacosArm64
).forEach {
    evaluationDependsOn(it.dependencyProject.path)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            artifact(projects.joltJniNativesLinux.dependencyProject.tasks.jar) {
                classifier = "natives-linux"
            }

            artifact(projects.joltJniNativesWindows.dependencyProject.tasks.jar) {
                classifier = "natives-windows"
            }

            artifact(projects.joltJniNativesMacos.dependencyProject.tasks.jar) {
                classifier = "natives-macos"
            }

            artifact(projects.joltJniNativesMacosArm64.dependencyProject.tasks.jar) {
                classifier = "natives-macos-arm64"
            }
        }
    }
}
