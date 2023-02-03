enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    plugins {
        kotlin("jvm") version "1.8.0"
    }
}

rootProject.name = "jolt-jni"

listOf(
    "annotations",
    "processor",
    "bindings",
    "natives-linux",
    "natives-windows",
    "natives-macos",
    "natives-macos-arm64",
    "test"
).forEach {
    val name = "${rootProject.name}-$it"
    include(name)
    project(":$name").apply {
        projectDir = file(it)
    }
}
