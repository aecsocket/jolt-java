enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    plugins {
        kotlin("jvm") version "1.8.0"
    }
}

rootProject.name = "jolt-jni-parent"

include("jolt-jni")
listOf(
    "bindings-cpp", "kotlin",
    "natives-linux", "natives-windows", "natives-macos", "natives-macos-arm64"
).forEach { include("jolt-jni-$it") }
