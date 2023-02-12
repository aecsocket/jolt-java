plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isLinux }
    buildScriptName.set("build.sh")
    sourceLibraryName.set("libJoltJNI.so")
    targetLibraryName.set("libJoltJNI-linux.so")
}
