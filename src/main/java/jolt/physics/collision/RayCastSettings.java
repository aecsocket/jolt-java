package jolt.physics.collision;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_RayCastSettings.*;

public final class RayCastSettings extends SegmentedJoltNative {
    //region Jolt-Value
    private RayCastSettings(MemorySegment handle) {
        super(handle);
    }

    public static RayCastSettings at(MemorySegment segment) {
        return new RayCastSettings(segment);
    }

    public static RayCastSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new RayCastSettings(ofAddress(addr, alloc));
    }

    public static RayCastSettings of(SegmentAllocator alloc) {
        return new RayCastSettings(allocate(alloc));
    }
    //endregion Jolt-Value

    public BackFaceMode getBackFaceMode() {
        return BackFaceMode.values()[back_face_mode$get(handle)];
    }

    public void setBackFaceMode(BackFaceMode backFaceMode) {
        back_face_mode$set(handle, (byte) backFaceMode.ordinal());
    }

    public boolean getTreatConvexAsSolid() {
        return treat_convex_as_solid$get(handle);
    }

    public void setTreatConvexAsSolid(boolean treatConvexAsSolid) {
        treat_convex_as_solid$set(handle, treatConvexAsSolid);
    }
}
