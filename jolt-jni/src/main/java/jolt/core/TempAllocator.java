package jolt.core;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Core/TempAllocator.h>")
@JniTypeMapping("TempAllocator")
public class TempAllocator extends JoltNativeImpl {
    protected TempAllocator(long address) { super(address); }
    public static TempAllocator ref(long address) { return address == 0 ? null : new TempAllocator(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    protected TempAllocator() {}
}
