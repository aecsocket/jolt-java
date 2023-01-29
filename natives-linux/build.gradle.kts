plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(rootProject)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks {

}
