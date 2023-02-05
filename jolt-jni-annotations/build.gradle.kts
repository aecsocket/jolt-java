plugins {
    id("java")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersion))
}
