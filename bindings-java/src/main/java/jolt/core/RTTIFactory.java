package jolt.core;

import jolt.jni.JniBind;
import jolt.JoltNative;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Core/Factory.h>")
@JniType("Factory")
public final class RTTIFactory extends JoltNative {
    private RTTIFactory(long address) { this.address = address; }
    public static RTTIFactory ref(long address) { return address == 0 ? null : new RTTIFactory(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public RTTIFactory() { address = _ctor(); }
    @JniBind("return (jlong) new Factory();")
    private static native long _ctor();

    public static @Nullable RTTIFactory getInstance() { return ref(_getInstance()); }
    @JniBind("return (jlong) Factory::sInstance;")
    private static native long _getInstance();

    public static void setInstance(@Nullable RTTIFactory value) { _setInstance(ptr(value)); }
    @JniBind("Factory::sInstance = (Factory*) value;")
    private static native void _setInstance(long value);
}
