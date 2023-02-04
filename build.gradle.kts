plugins {
    kotlin("jvm")
    id("maven-publish")
}

allprojects {
    group = "io.github.aecsocket"
    version = "0.1.0-SNAPSHOT"
    description = "Java JNI bindings for the Jolt physics engine"

    ext {
        set(BUILD_TYPE, project.property(BUILD_TYPE)?.let {
            JoltBuildType.valueOf(it.toString().toUpperCase())
        } ?: JoltBuildType.DEBUG)
        set(FLAVOR, project.property(FLAVOR)?.let {
            JoltFlavor.valueOf(it.toString().toUpperCase())
        } ?: JoltFlavor.SP)
        set(JVM_VERSION, 16)
    }
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.joltJniBindingsJava)

    testImplementation(kotlin("test"))
    testRuntimeOnly(projects.joltJniNativesLinux)
    testRuntimeOnly(projects.joltJniNativesWindows)
    testRuntimeOnly(projects.joltJniNativesMacos)
    testRuntimeOnly(projects.joltJniNativesMacosArm64)
}

kotlin {
    jvmToolchain(jvmVersion)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks {
    test {
        dependsOn(assemble)
    }
}
