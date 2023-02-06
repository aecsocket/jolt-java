plugins {
    kotlin("jvm")
    id("maven-publish")
}

// change using `-PbuildType=value` and `-PdoublePrecision=true` on the command line or in gradle.properties
val buildType = project.findProperty(BUILD_TYPE)?.let {
    JoltBuildType.valueOf(it.toString().toUpperCase())
} ?: JoltBuildType.DEBUG
val doublePrecision = project.findProperty(DOUBLE_PRECISION)?.toString()?.toBoolean() ?: false

allprojects {
    group = "io.github.aecsocket"
    version = "0.1.0-SNAPSHOT"
    description = "Java JNI bindings for the Jolt physics engine"

    ext {
        set(BUILD_TYPE, buildType)
        set(DOUBLE_PRECISION, doublePrecision)
        set(JVM_VERSION, 16)
    }
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.joltJni)

    testImplementation(kotlin("test"))
    testRuntimeOnly(projects.joltJniNativesLinux)
    testRuntimeOnly(projects.joltJniNativesWindows)
    testRuntimeOnly(projects.joltJniNativesMacos)
    testRuntimeOnly(projects.joltJniNativesMacosArm64)
}

kotlin {
    jvmToolchain(jvmVersion)
}

val bindings = projects.joltJniBindingsCpp.dependencyProject

tasks {
    assemble {
        dependsOn(bindings.tasks.assemble)
    }
}
