plugins {
    id("java-conventions")
}

dependencies {
    implementation(projects.joltJni)

    testImplementation(libs.jUnitJupiterApi)
    testImplementation(libs.jUnitJupiterEngine)
    testRuntimeOnly(projects.joltJniNativesLinux)
    testRuntimeOnly(projects.joltJniNativesWindows)
    testRuntimeOnly(projects.joltJniNativesMacos)
    testRuntimeOnly(projects.joltJniNativesMacosArm64)
}
