package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.headers.JPC_GJKClosestPoint;
import jolt.math.FVec3;
import jolt.physics.collision.shape.ConvexShape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_GJKClosestPoint.*;
import static jolt.headers.JoltPhysicsC.*;

public final class GJKClosestPoint extends SegmentedJoltNative {
    //region Jolt-Value
    private GJKClosestPoint(MemorySegment handle) {
        super(handle);
    }

    public static GJKClosestPoint at(MemorySegment segment) {
        return new GJKClosestPoint(segment);
    }

    public static GJKClosestPoint at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new GJKClosestPoint(ofAddress(addr, alloc));
    }

    public static GJKClosestPoint of(SegmentAllocator alloc) {
        return new GJKClosestPoint(allocate(alloc));
    }
    //endregion Jolt-Value

    public boolean intersects(ConvexShape a, ConvexShape b, float tolerance, FVec3 ioSeparating) {
        return JPC_GJKClosestPoint_IntersectsConvexConvex(handle, a.address(), b.address(), tolerance, ioSeparating.address());
    }

    public boolean intersects(ConvexShape a, PointConvexSupport b, float tolerance, FVec3 ioSeparating) {
        return JPC_GJKClosestPoint_IntersectsConvexPoint(handle, a.address(), b.address(), tolerance, ioSeparating.address());
    }

    public float getClosestPoints(ConvexShape a, ConvexShape b, float tolerance, float maxDistSq, FVec3 ioSeparating, FVec3 outPointA, FVec3 outPointB) {
        return JPC_GJKClosestPoint_GetClosestPointsConvexConvex(handle, a.address(), b.address(), tolerance, maxDistSq, ioSeparating.address(), outPointA.address(), outPointB.address());
    }

    public float getClosestPoints(ConvexShape a, PointConvexSupport b, float tolerance, float maxDistSq, FVec3 ioSeparating, FVec3 outPointA, FVec3 outPointB) {
        return JPC_GJKClosestPoint_GetClosestPointsConvexPoint(handle, a.address(), b.address(), tolerance, maxDistSq, ioSeparating.address(), outPointA.address(), outPointB.address());
    }
}
