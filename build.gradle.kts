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
        set(JVM_VERSION, 16)
    }
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.findbugs", "jsr305", "3.0.2")
    implementation(projects.joltJniAnnotations)
    annotationProcessor(projects.joltJniProcessor)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersion))
}

val bindings: Project = projects.joltJniBindings.dependencyProject

tasks {
    assemble {
        dependsOn(bindings.tasks.assemble)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
