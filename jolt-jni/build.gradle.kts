plugins {
    id("java")
    id("maven-publish")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.joltJniAnnotations)
    annotationProcessor(projects.joltJniProcessor)

    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersion))
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
