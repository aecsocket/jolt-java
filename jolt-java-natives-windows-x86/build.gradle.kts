plugins {
    id("natives-windows-conventions")
}

dependencies {
    runtimeOnly(libs.cpuFeaturesJavaNativesWindowsX86)
}

natives {
    destDir.set("windows_x86")
}
