package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_CollidePointResult.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CollidePointResult extends SegmentedJoltNative {
    //region Jolt-Value
    private CollidePointResult(MemorySegment handle) {
        super(handle);
    }

    public static CollidePointResult at(MemorySegment segment) {
        return new CollidePointResult(segment);
    }

    public static CollidePointResult at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollidePointResult(ofAddress(addr, alloc));
    }

    public static CollidePointResult of(SegmentAllocator alloc) {
        return new CollidePointResult(allocate(alloc));
    }
    //endregion Jolt-Value

    public int getBodyId() {
        return body_id$get(handle);
    }

    public void setBodyId(int bodyId) {
        body_id$set(handle, bodyId);
    }

    public int getSubShapeId() {
        return sub_shape_id$get(handle);
    }

    public void setSubShapeId(int subShapeId) {
        sub_shape_id$set(handle, subShapeId);
    }
}
