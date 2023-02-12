plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isMacOsX }
    bindingsFileName.set("libJoltJNI.dylib")
    destInnerDir.set("macos")
}
