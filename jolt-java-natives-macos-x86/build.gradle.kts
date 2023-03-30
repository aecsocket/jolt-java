plugins {
    id("natives-macos-conventions")
}

dependencies {
    runtimeOnly(libs.cpuFeaturesJavaNativesMacosX86)
}

natives {
    destDir.set("macos_x86")
}
