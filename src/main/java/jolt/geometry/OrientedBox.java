package jolt.geometry;

import jolt.headers.JPC_OrientedBox;
import jolt.math.FMat44;
import jolt.math.FVec3;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_OrientedBox.*;

public record OrientedBox(FMat44 orientation, FVec3 halfExtent) {
    public static OrientedBox read(MemorySegment segment) {
        return new OrientedBox(
                FMat44.read(orientation$slice(segment)),
                FVec3.read(half_extents$slice(segment))
        );
    }

    public void write(MemorySegment segment) {
        orientation.write(orientation$slice(segment));
        halfExtent.write(half_extents$slice(segment));
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        var segment = JPC_OrientedBox.allocate(allocator);
        write(segment);
        return segment;
    }
}
