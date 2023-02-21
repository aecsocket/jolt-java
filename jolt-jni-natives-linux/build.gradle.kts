plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isLinux }
    buildScriptBase.add("./build.sh")
    sourceLibraryName.set("libJoltJNI.so")
    targetLibraryName.set("libJoltJNI-linux.so")
}
