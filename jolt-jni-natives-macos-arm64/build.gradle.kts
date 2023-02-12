plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isMacOsX }
    sourceLibraryName.set("libJoltJNI.dylib")
    targetLibraryName.set("libJoltJNI-macos-arm64.dylib")
}
