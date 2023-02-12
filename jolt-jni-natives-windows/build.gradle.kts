plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isWindows }
    sourceLibraryName.set("libJoltJNI.dll")
    targetLibraryName.set("libJoltJNI-windows.dll")
}
