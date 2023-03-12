package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_PointConvexSupport.*;

public final class PointConvexSupport extends SegmentedJoltNative {
    //region Jolt-Value
    private PointConvexSupport(MemorySegment handle) {
        super(handle);
    }

    public static PointConvexSupport at(MemorySegment segment) {
        return new PointConvexSupport(segment);
    }

    public static PointConvexSupport at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new PointConvexSupport(ofAddress(addr, alloc));
    }

    public static PointConvexSupport of(SegmentAllocator alloc) {
        return new PointConvexSupport(allocate(alloc));
    }
    //endregion Jolt-Value

    public static PointConvexSupport of(SegmentAllocator alloc, FVec3 point) {
        var segment = allocate(alloc);
        point.write(point$slice(segment));
        return new PointConvexSupport(segment);
    }

    public FVec3 getPoint() {
        return FVec3.at(point$slice(handle));
    }

    public void setPoint(FVec3 point) {
        point.write(point$slice(handle));
    }
}
