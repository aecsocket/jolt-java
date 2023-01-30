pluginManagement {
    plugins {
        kotlin("jvm") version "1.8.0"
        id("org.jetbrains.dokka") version "1.7.20"
    }
}

rootProject.name = "jolt-jni"

listOf(
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
