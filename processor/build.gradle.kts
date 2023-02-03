plugins {
    kotlin("jvm")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.joltJniAnnotations)
}

kotlin {
    jvmToolchain(jvmVersion)
}
