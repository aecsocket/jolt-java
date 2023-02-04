plugins {
    id("java")
    id("maven-publish")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.joltJniAnnotations)
    annotationProcessor(projects.joltJniProcessor)

    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersion))
}
