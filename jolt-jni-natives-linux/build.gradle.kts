plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isLinux }
    sourceLibraryName.set("libJoltJNI.so")
    targetLibraryName.set("libJoltJNI-linux.so")
}
