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
