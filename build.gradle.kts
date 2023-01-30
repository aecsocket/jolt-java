plugins {
    id("java")
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
    }
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
    compileJava {
        options.headerOutputDirectory.set(File("${projBindings.projectDir}/src/main/cpp"))
    }

    assemble {
        dependsOn(projBindings.tasks.assemble)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
