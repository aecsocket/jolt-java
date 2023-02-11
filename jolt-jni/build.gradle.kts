plugins {
    id("publishing-conventions")
}

dependencies {
    implementation(libs.findBugs)
    implementation(libs.jniGlueAnnotations)
    annotationProcessor(libs.jniGlueProcessor)
    implementation(libs.cpuFeaturesJni)
}

tasks.register("writeToolchainPath", WriteToolchainPath::class) {
    toolchainSpec.set(java.toolchain)
    outputFile.set(layout.buildDirectory.file("javaToolchainJavaHome.txt"))
}
