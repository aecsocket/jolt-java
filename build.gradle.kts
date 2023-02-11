plugins {
    id("parent-conventions")
}

val nativesLinux      = projects.joltJniNativesLinux.dependencyProject
val nativesWindows    = projects.joltJniNativesWindows.dependencyProject
val nativesMacos      = projects.joltJniNativesMacos.dependencyProject
val nativesMacosArm64 = projects.joltJniNativesMacosArm64.dependencyProject

group = "io.github.aecsocket"
version = "0.1.0-SNAPSHOT"
description = "Java bindings for JoltPhysics"
