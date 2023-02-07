plugins {
    kotlin("jvm")
    id("maven-publish")
}

// change using `-PbuildType=value` and `-Pflavor=value` on the command line or in gradle.properties
val buildType = project.findProperty(BUILD_TYPE)?.let {
    JoltBuildType.valueOf(it.toString().toUpperCase())
} ?: JoltBuildType.DEBUG
val flavor = project.findProperty(FLAVOR)?.let {
    JoltFlavor.valueOf(it.toString().toUpperCase())
} ?: JoltFlavor.SP

allprojects {
    group = "io.github.aecsocket"
    version = "0.1.0-SNAPSHOT"
    description = "Java JNI bindings for the Jolt physics engine"

    ext {
        set(BUILD_TYPE, buildType)
        set(FLAVOR, flavor)
        set(JVM_VERSION, 16)
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

val jvmVersion = ext.get(JVM_VERSION) as Int

dependencies {
    implementation(projects.joltJni)
    implementation(projects.joltJniKotlin)

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
