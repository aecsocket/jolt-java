package jolt.geometry;

import jolt.headers.JPC_AABox;
import jolt.math.FVec3;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_AABox.*;

public record AABox(FVec3 min, FVec3 max) {
    public static final AABox ZERO = new AABox(FVec3.ZERO, FVec3.ZERO);

    public static AABox read(MemorySegment segment) {
        return new AABox(
                FVec3.read(min$slice(segment)),
                FVec3.read(max$slice(segment))
        );
    }

    public void write(MemorySegment segment) {
        min.write(min$slice(segment));
        max.write(max$slice(segment));
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        var segment = JPC_AABox.allocate(allocator);
        write(segment);
        return segment;
    }
}
