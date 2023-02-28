package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ContactManifold.*;

public final class ContactSettings extends SegmentedJoltNative {
    public static ContactSettings at(MemorySegment segment) {
        return segment.address() == MemoryAddress.NULL ? null : new ContactSettings(segment);
    }

    public static ContactSettings at(MemorySession session, MemoryAddress address) {
        return at(ofAddress(address, session));
    }

    private ContactSettings(MemorySegment segment) {
        super(segment);
    }

    public FVec3 getBaseOffsetSp() {
        return FVec3.read(base_offset$slice(segment).address());
    }

    public DVec3 getBaseOffsetDp() {
        return DVec3.read(base_offset$slice(segment).address());
    }

    public FVec3 getWorldSpaceNormal() {
        return FVec3.read(normal$slice(segment).address());
    }

    public float getPenetrationDepth() {
        return penetration_depth$get(segment);
    }

    public int getSubShapeId1() {
        return shape1_sub_shape_id$get(segment);
    }

    public int getSubShapeId2() {
        return shape2_sub_shape_id$get(segment);
    }

    // TODO getRelativeContactPointsOn1
    // TODO getRelativeContactPointsOn2
}
