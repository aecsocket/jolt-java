package jolt.core;

import io.github.aecsocket.jniglue.JniBind;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;

/**
 * Default implementation of the temp allocator that allocates a large block through malloc upfront.
 */
@JniInclude("<Jolt/Core/TempAllocator.h>")
@JniTypeMapping("TempAllocatorImpl")
public final class TempAllocatorImpl extends TempAllocator {
    private TempAllocatorImpl(long address) { super(address); }
    public static TempAllocatorImpl ref(long address) { return address == 0 ? null : new TempAllocatorImpl(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    /**
     * Constructs the allocator with a maximum allocatable size of inSize, in bytes.
     * <b>When used in a PhysicsSystem, the size of the allocator must be at least {@code maxContactConstraints * sizeof(ContactConstraint)}</b>.
     */
    public static TempAllocatorImpl ofBytes(long size) { return ref(_ofBytes(size)); }
    @JniBind("return (jlong) new TempAllocatorImpl(size);")
    private static native long _ofBytes(long size);
}
