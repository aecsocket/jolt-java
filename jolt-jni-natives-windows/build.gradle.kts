plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isWindows }
    buildScriptName.set("build.ps1")
    sourceLibraryName.set("libJoltJNI.dll")
    targetLibraryName.set("libJoltJNI-windows.dll")
}
