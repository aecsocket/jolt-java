plugins {
    id("java")
    id("maven-publish")
}

val jvmVersion = ext.get(JVM_VERSION) as Int

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.joltJniBindingsJava)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersion))
}

val bindings: Project = projects.joltJniBindingsCpp.dependencyProject

tasks {
    jar {
        val jarTask = this
        bindings.tasks.withType<LinkSharedLibrary> {
            jarTask.mustRunAfter(this)
        }

        from("${bindings.buildDir}/lib/main/debug/jolt-jni-bindings.dll") {
            into("jolt/")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
