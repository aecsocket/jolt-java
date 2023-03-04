package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_CollideShapeSettings.*;

public final class CollideShapeSettings extends CollideSettingsBase {
    //region Jolt-Value
    private CollideShapeSettings(MemorySegment handle) {
        super(handle);
    }

    public static CollideShapeSettings at(MemorySegment segment) {
        return new CollideShapeSettings(segment);
    }

    public static CollideShapeSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollideShapeSettings(ofAddress(addr, alloc));
    }

    public static CollideShapeSettings of(SegmentAllocator alloc) {
        return new CollideShapeSettings(allocate(alloc));
    }
    //endregion Jolt-Value

    public float getMaxSeparationDistance() {
        return max_separation_distance$get(handle);
    }

    public void setMaxSeparationDistance(float maxSeparationDistance) {
        max_separation_distance$set(handle, maxSeparationDistance);
    }

    public BackFaceMode getBackFaceMode() {
        return BackFaceMode.values()[back_face_mode$get(handle)];
    }

    public void setBackFaceMode(BackFaceMode backFaceMode) {
        back_face_mode$set(handle, (byte) backFaceMode.ordinal());
    }
}
