plugins {
    id("java-conventions")
}

if (!ci.get() || ciPublishApi.get()) {
    plugins.apply("publishing-conventions")
}

dependencies {
    compileOnlyApi(libs.findBugs)
    implementation(libs.jniGlueAnnotations)
    annotationProcessor(libs.jniGlueProcessor)
    implementation(libs.cpuFeaturesJni)

    testImplementation(libs.findBugs)
}

tasks.register("writeToolchainPath", WriteToolchainPath::class) {
    toolchainSpec.set(java.toolchain)
    outputFile.set(layout.buildDirectory.file("javaToolchainJavaHome.txt"))
}
