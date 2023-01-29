plugins {
    id("java")
}

allprojects {
    group = "io.gitlab.aecsocket"
    version = "0.1.0"
    description = "Java JNI bindings for the Jolt physics engine"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

val projBindings = project(":jolt-jni-bindings")

tasks {
    /*
    1. git submodule update -> Jolt headers
    2. : -> build bindings for platform -> libjolt-jni.so
    3. :jolt-jni-natives-linux -> copy libjolt-jni.so to src/main/resources
    4. :jolt-jni-test -> runtimeOnly(:jolt-jni-natives-linux)
     */

    compileJava {
        options.headerOutputDirectory.set(File("${projBindings.projectDir}/src/main/cpp"))
    }
}
