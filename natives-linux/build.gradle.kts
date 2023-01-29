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
    jar {
        val jarTask = this
        rootProject.tasks.withType<LinkSharedLibrary> {
            jarTask.mustRunAfter(this)
        }

        from("${rootProject.buildDir}/lib/main/debug/libjolt-jni.so") {
            into("jolt/")
        }
    }
}
