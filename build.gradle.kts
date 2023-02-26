plugins {
    id("parent-conventions")
    id("java-conventions")
}

if (!ci.get() || ciPublishApi.get()) {
    plugins.apply("publishing-conventions")
}

group = "io.github.aecsocket"
version = "0.1.0-SNAPSHOT"
description = "Java bindings for JoltPhysics"

dependencies {
    implementation(projects.joltJavaHeaders)
    implementation(libs.cpuFeaturesJava)
    compileOnlyApi(libs.findBugs)

    testImplementation(libs.jUnitJupiterApi)
    testImplementation(libs.jUnitJupiterEngine)
    testImplementation(libs.findBugs)
    testRuntimeOnly(projects.joltJavaNativesLinuxX86)
    //testRuntimeOnly(projects.joltJavaNativesWindowsX86)
    //testRuntimeOnly(projects.joltJavaNativesMacosX86)
}

tasks.register("printVersionType") {
    doFirst {
        println(if (net.kyori.indra.util.Versioning.isSnapshot(project)) "snapshot" else "release")
    }
}
