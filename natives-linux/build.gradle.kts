plugins {
    id("java")
    id("maven-publish")
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

val projBindings = project(":jolt-jni-bindings")

tasks {
    jar {
        val jarTask = this
        projBindings.tasks.withType<LinkSharedLibrary> {
            jarTask.mustRunAfter(this)
        }

        from("${projBindings.buildDir}/lib/main/debug/libjolt-jni-bindings.so") {
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
