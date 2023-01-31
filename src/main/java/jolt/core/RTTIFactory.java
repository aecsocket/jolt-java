package jolt.core;

import jolt.JoltNative;

import javax.annotation.Nullable;

public final class RTTIFactory extends JoltNative {
    private RTTIFactory(long address) { super(address); }
    public static RTTIFactory ofPointer(long address) { return new RTTIFactory(address); }

    public RTTIFactory() {
        address = _create();
    }
    private static native long _create();

    public static @Nullable RTTIFactory instance() { return ofPointer(_instance()); }
    private static native long _instance();

    public static void instance(@Nullable RTTIFactory value) { _instance(value == null ? 0 : value.address); }
    private static native void _instance(long value);
}
