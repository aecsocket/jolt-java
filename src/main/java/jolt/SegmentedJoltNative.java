package jolt;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;

public abstract class SegmentedJoltNative implements JoltNative {
    protected final MemorySegment segment;

    public SegmentedJoltNative(MemorySegment segment) {
        this.segment = segment;
    }

    public MemorySegment getMemorySegment() {
        return segment;
    }

    @Override
    public MemoryAddress address() {
        return segment.address();
    }
}
