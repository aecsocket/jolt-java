package jolt.physics.collision;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_BroadPhaseCastResult.*;

public sealed class BroadPhaseCastResult extends SegmentedJoltNative
        permits RayCastResult {
    //region Jolt-Value-Protected
    protected BroadPhaseCastResult(MemorySegment handle) {
        super(handle);
    }

    public static BroadPhaseCastResult at(MemorySegment segment) {
        return new BroadPhaseCastResult(segment);
    }

    public static BroadPhaseCastResult at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BroadPhaseCastResult(ofAddress(addr, alloc));
    }

    public static BroadPhaseCastResult of(SegmentAllocator alloc) {
        return new BroadPhaseCastResult(allocate(alloc));
    }
    //endregion Jolt-Value-Protected

    public int getBodyId() {
        return body_id$get(handle);
    }

    public void setBodyId(int bodyId) {
        body_id$set(handle, bodyId);
    }

    public float getFraction() {
        return fraction$get(handle);
    }

    public void setFraction(float fraction) {
        fraction$set(handle, fraction);
    }
}
