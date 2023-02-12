plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isMacOsX }
    buildScriptName.set("build.sh")
    sourceLibraryName.set("libJoltJNI.dylib")
    targetLibraryName.set("libJoltJNI-macos-arm64.dylib")
}
