enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

plugins {
    id("ca.stellardrift.polyglot-version-catalogs") version "6.0.1"
}

rootProject.name = "jolt-jni-parent"

include("jolt-jni")
include("jolt-jni-bindings")
include("jolt-jni-kotlin")
include("jolt-jni-natives-linux")
include("jolt-jni-natives-windows")
include("jolt-jni-natives-macos")
include("jolt-jni-natives-macos-arm64")
include("jolt-jni-test")
