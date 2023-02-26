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

rootProject.name = "jolt-java"

include("jolt-java-headers")
include("jolt-java-kotlin")
include("jolt-java-natives-linux-x86")
include("jolt-java-natives-windows-x86")
include("jolt-java-natives-macos-x86")
