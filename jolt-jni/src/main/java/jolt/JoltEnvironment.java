package jolt;

import jolt.jni.JniBind;
import jolt.jni.JniInclude;
import jolt.jni.JniNative;

@JniNative(JoltNative.MODEL)
@JniInclude("<Jolt/RegisterTypes.h>")
public final class JoltEnvironment {
    private JoltEnvironment() {}

    public static boolean isDoublePrecision() { return _isDoublePrecision(); }
    @JniBind("""
            #ifdef JPH_DOUBLE_PRECISION
            return true;
            #endif
            return false;""")
    private static native boolean _isDoublePrecision();

    public static void registerDefaultAllocator() { _registerDefaultAllocator(); }
    @JniBind("RegisterDefaultAllocator();")
    private static native void _registerDefaultAllocator();

    public static void registerTypes() { _registerTypes(); }
    @JniBind("RegisterTypes();")
    private static native void _registerTypes();
}
