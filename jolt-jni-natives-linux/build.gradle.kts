plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isLinux }
    bindingsFileName.set("libjolt-jni-bindings.so")
    destInnerDir.set("linux")
}
