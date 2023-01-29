plugins {
    kotlin("jvm")
    id("cpp")
}

group = "io.gitlab.aecsocket"
version = "0.1.0"
description = "Java JNI bindings for the Jolt physics engine"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(8)
}

tasks {
    test {
        dependsOn(":jolt-jni-natives:linkDebug")
        useJUnitPlatform()
        systemProperty("java.library.path", file("${project(":jolt-jni-natives").buildDir}/lib/main/debug").absolutePath)
    }
}
