plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(rootProject)
    testRuntimeOnly(project(":jolt-jni-natives-linux"))
    testRuntimeOnly(project(":jolt-jni-natives-windows"))
    testRuntimeOnly(project(":jolt-jni-natives-macos"))
    testRuntimeOnly(project(":jolt-jni-natives-macos-arm64"))
}

kotlin {
    jvmToolchain(11)
}

tasks {
    test {
        useJUnitPlatform()
        dependsOn(rootProject.tasks.assemble)
    }
}
