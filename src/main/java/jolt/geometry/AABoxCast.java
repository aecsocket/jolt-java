package jolt.geometry;

import jolt.headers.JPC_AABoxCast;
import jolt.math.FVec3;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_AABoxCast.*;

public record AABoxCast(AABox box, FVec3 direction) {
    public static AABoxCast read(MemorySegment segment) {
        return new AABoxCast(
                AABox.read(box$slice(segment)),
                FVec3.read(direction$slice(segment))
        );
    }

    public void write(MemorySegment segment) {
        box.write(box$slice(segment));
        direction.write(direction$slice(segment));
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        var segment = JPC_AABoxCast.allocate(allocator);
        write(segment);
        return segment;
    }
}
