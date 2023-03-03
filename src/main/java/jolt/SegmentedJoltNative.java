package jolt;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;

public abstract class SegmentedJoltNative implements JoltNative {
    protected final MemorySegment handle;

    protected SegmentedJoltNative(MemorySegment handle) {
        this.handle = handle;
    }

    @Override
    public MemoryAddress address() {
        return handle.address();
    }
}
