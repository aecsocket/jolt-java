plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(rootProject)
    testRuntimeOnly(projects.joltJniNativesLinux)
    testRuntimeOnly(projects.joltJniNativesWindows)
    testRuntimeOnly(projects.joltJniNativesMacos)
    testRuntimeOnly(projects.joltJniNativesMacosArm64)
}

kotlin {
    jvmToolchain(17)
}

tasks {
    test {
        useJUnitPlatform()
        dependsOn(rootProject.tasks.assemble)
    }
}
