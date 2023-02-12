plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isWindows }
    buildScriptBase.addAll(listOf("cmd", "/c", "Powershell -File build.ps1"))
    sourceLibraryName.set("libJoltJNI.dll")
    targetLibraryName.set("libJoltJNI-windows.dll")
}
