plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isMacOsX }
    bindingsFileName.set("libjolt-jni-bindings.dylib")
    destInnerDir.set("macos")
}
