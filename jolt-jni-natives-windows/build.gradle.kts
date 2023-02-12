plugins {
    id("natives-conventions")
}

natives {
    platformPredicate.set { it.isWindows }
    bindingsFileName.set("JoltJNI.dll")
    destInnerDir.set("windows")
}
