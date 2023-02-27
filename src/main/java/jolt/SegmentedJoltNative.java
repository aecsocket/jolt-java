package jolt;

import java.lang.foreign.MemorySegment;

public abstract class SegmentedJoltNative extends AbstractJoltNative {
    protected final MemorySegment segment;

    public SegmentedJoltNative(MemorySegment segment) {
        super(segment.address());
        this.segment = segment;
    }
}
