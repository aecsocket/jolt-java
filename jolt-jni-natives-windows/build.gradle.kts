plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isWindows }
    bindingsFileName.set("jolt-jni-bindings.dll")
    destInnerDir.set("windows")
}
