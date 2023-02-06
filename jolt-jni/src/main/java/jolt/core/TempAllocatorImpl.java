package jolt.core;

import jolt.jni.JniBind;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Core/TempAllocator.h>")
@JniType("TempAllocatorImpl")
public final class TempAllocatorImpl extends TempAllocator {
    private TempAllocatorImpl(long address) { super(address); }
    public static TempAllocatorImpl ref(long address) { return address == 0 ? null : new TempAllocatorImpl(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public static TempAllocatorImpl ofBytes(long size) { return ref(_ofBytes(size)); }
    @JniBind("return (jlong) new TempAllocatorImpl(size);")
    private static native long _ofBytes(long size);
}
