package jolt.core;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Core/TempAllocator.h>")
public class TempAllocator extends JoltNative {
    protected TempAllocator(long address) { super(address); }
    public static TempAllocator ref(long address) { return address == 0 ? null : new TempAllocator(address); }

    protected TempAllocator() {}
}
