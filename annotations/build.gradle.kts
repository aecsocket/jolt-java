plugins {
    kotlin("jvm")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(jvmVersion)
}
