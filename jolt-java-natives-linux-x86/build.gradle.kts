plugins {
    id("natives-linux-conventions")
}

dependencies {
    runtimeOnly(libs.cpuFeaturesJavaNativesLinuxX86)
}

natives {
    destDir.set("linux_x86")
}
