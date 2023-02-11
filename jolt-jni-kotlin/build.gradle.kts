plugins {
    id("publishing-conventions")
    kotlin("jvm")
}

dependencies {
    implementation(projects.joltJni)
}

kotlin {
    jvmToolchain(indra.javaVersions().target().get())
}
