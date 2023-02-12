plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isLinux }
    bindingsFileName.set("libJoltJNI.so")
    destInnerDir.set("linux")
}
