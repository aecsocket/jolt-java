package jolt;

import jolt.jni.JniInclude;

@JniInclude("<Jolt/RegisterTypes.h>")
public final class JoltEnvironment {
    private JoltEnvironment() {}

    public static void registerDefaultAllocator() { _registerDefaultAllocator(); }
    private static native void _registerDefaultAllocator();

    public static void registerTypes() { _registerTypes(); }
    private static native void _registerTypes();
}
