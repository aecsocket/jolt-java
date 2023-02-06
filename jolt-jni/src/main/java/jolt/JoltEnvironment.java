package jolt;

import jolt.jni.JniBind;
import jolt.jni.JniInclude;
import jolt.jni.JniNative;

import java.util.List;

@JniNative(JoltNative.MODEL)
@JniInclude("<Jolt/RegisterTypes.h>")
public final class JoltEnvironment {
    private JoltEnvironment() {}

    private static int featureBits = -1;

    public static int featureBits() {
        if (featureBits == -1) {
            featureBits = _featureBits();
        }
        return featureBits;
    }
    @JniBind("""
            int features = 0;
            #ifdef JPH_DOUBLE_PRECISION
            features |= 0x1;
            #endif
            #ifdef JPH_USE_SSE4_1
            features |= 0x2;
            #endif
            #ifdef JPH_USE_SSE4_2
            features |= 0x4;
            #endif
            #ifdef JPH_USE_AVX
            features |= 0x8;
            #endif
            #ifdef JPH_USE_AVX2
            features |= 0x10;
            #endif
            #ifdef JPH_USE_AVX512
            features |= 0x20;
            #endif
            #ifdef JPH_USE_LZCNT
            features |= 0x40;
            #endif
            #ifdef JPH_USE_TZCNT
            features |= 0x80;
            #endif
            #ifdef JPH_USE_F16C
            features |= 0x100;
            #endif
            #ifdef JPH_USE_FMADD
            features |= 0x200;
            #endif
            return features;""")
    private static native int _featureBits();

    public static List<JoltFeature> features() {
        return JoltFeature.flagsOf(featureBits());
    }

    public static boolean uses(JoltFeature feature) {
        return feature.isSet(featureBits());
    }

    public static void registerDefaultAllocator() { _registerDefaultAllocator(); }
    @JniBind("RegisterDefaultAllocator();")
    private static native void _registerDefaultAllocator();

    public static void registerTypes() { _registerTypes(); }
    @JniBind("RegisterTypes();")
    private static native void _registerTypes();
}
