package jolt.core;

import jolt.jni.JniBind;
import jolt.JoltNative;
import jolt.jni.JniInclude;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Core/Factory.h>")
public final class RTTIFactory extends JoltNative {
    private RTTIFactory(long address) { this.address = address; }
    public static RTTIFactory ref(long address) { return address == 0 ? null : new RTTIFactory(address); }

    public RTTIFactory() { address = _ctor(); }
    @JniBind("return (long) new Factory();")
    private static native long _ctor();

    public static @Nullable RTTIFactory getInstance() { return ref(_getInstance()); }
    @JniBind("return (long) Factory::sInstance;")
    private static native long _getInstance();

    public static void setInstance(@Nullable RTTIFactory value) { _setInstance(value == null ? 0L : value.address); }
    @JniBind("Factory::sInstance = (Factory*) value;")
    private static native void _setInstance(long value);
}
