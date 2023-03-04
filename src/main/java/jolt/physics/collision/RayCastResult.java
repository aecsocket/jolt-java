 package jolt.physics.collision;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_RayCastResult.*;

public final class RayCastResult extends BroadPhaseCastResult {
    //region Jolt-Value
    private RayCastResult(MemorySegment handle) {
        super(handle);
    }

    public static RayCastResult at(MemorySegment segment) {
        return new RayCastResult(segment);
    }

    public static RayCastResult at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new RayCastResult(ofAddress(addr, alloc));
    }

    public static RayCastResult of(SegmentAllocator alloc) {
        return new RayCastResult(allocate(alloc));
    }
    //endregion Jolt-Value

    public int getSubShapeId() {
        return sub_shape_id$get(handle);
    }

    public void setSubShapeId(int subShapeId) {
        sub_shape_id$set(handle, subShapeId);
    }
}
