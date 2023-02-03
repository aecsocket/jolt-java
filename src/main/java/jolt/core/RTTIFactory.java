package jolt.core;

import jolt.jni.JniBind;
import jolt.JoltNative;

import javax.annotation.Nullable;

public final class RTTIFactory extends JoltNative {
    private RTTIFactory(long address) { this.address = address; }
    public static RTTIFactory ref(long address) { return address == 0 ? null : new RTTIFactory(address); }

    public RTTIFactory() { address = _ctor(); }
    @JniBind("return (jlong) new Factory();")
    private static native long _ctor();

    public static @Nullable RTTIFactory getInstance() { return ref(_getInstance()); }
    @JniBind("return (jlong) Factory::sInstance;")
    private static native long _getInstance();

    public static void setInstance(@Nullable RTTIFactory value) { _setInstance(value == null ? 0L : value.address); }
    @JniBind("Factory::sInstance = (Factory*) value;")
    private static native void _setInstance(long value);
}
