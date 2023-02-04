package jolt.core;

import jolt.jni.JniBind;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Core/TempAllocator.h>")
public final class TempAllocatorImpl extends TempAllocator {
    private TempAllocatorImpl(long address) { super(address); }
    public static TempAllocatorImpl ref(long address) { return address == 0 ? null : new TempAllocatorImpl(address); }

    public static TempAllocatorImpl ofSize(long size) { return ref(_ofSize(size)); }
    @JniBind("return (long) new TempAllocatorImpl(size);")
    private static native long _ofSize(long size);
}
