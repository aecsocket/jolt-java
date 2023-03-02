package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_CollideShapeResult.ofAddress;
import static jolt.headers.JPC_ContactManifold.*;

public final class ContactManifold extends SegmentedJoltNative {
    public static ContactManifold at(MemorySegment segment) {
        return new ContactManifold(segment);
    }

    public static ContactManifold at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    private ContactManifold(MemorySegment segment) {
        super(segment);
    }

    public FVec3 getBaseOffsetSp() {
        return FVec3.read(base_offset$slice(segment));
    }

    public void setBaseOffset(FVec3 baseOffset) {
        baseOffset.write(base_offset$slice(segment));
    }

    public DVec3 getBaseOffsetDp() {
        return DVec3.read(base_offset$slice(segment).address());
    }

    public void setBaseOffset(DVec3 baseOffset) {
        baseOffset.write(base_offset$slice(segment));
    }

    public FVec3 getWorldSpaceNormal() {
        return FVec3.read(normal$slice(segment));
    }

    public void setWorldSpaceNormal(FVec3 worldSpaceNormal) {
        worldSpaceNormal.write(normal$slice(segment));
    }

    public float getPenetrationDepth() {
        return penetration_depth$get(segment);
    }

    public void setPenetrationDepth(float penetrationDepth) {
        penetration_depth$set(segment, penetrationDepth);
    }

    public int getSubShapeId1() {
        return shape1_sub_shape_id$get(segment);
    }

    public void setSubShapeId1(int subShapeId1) {
        shape1_sub_shape_id$set(segment, subShapeId1);
    }

    public int getSubShapeId2() {
        return shape2_sub_shape_id$get(segment);
    }

    public void setSubShapeId2(int subShapeId2) {
        shape2_sub_shape_id$set(segment, subShapeId2);
    }

    // TODO getRelativeContactPointsOn1
    // TODO getRelativeContactPointsOn2
}
