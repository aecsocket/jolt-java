package jolt.core;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Core/TempAllocator.h>")
@JniTypeMapping("TempAllocator")
public class TempAllocator extends JoltNative {
    protected TempAllocator(long address) { super(address); }
    public static TempAllocator ref(long address) { return address == 0 ? null : new TempAllocator(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected TempAllocator() {}
}
