package jolt.physics.collision;

import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_CollideShapeResult.ofAddress;
import static jolt.headers.JPC_ContactManifold.*;

public abstract sealed class ContactSettings extends SegmentedJoltNative
        permits ContactSettings.F, ContactSettings.D {
    public static ContactSettings at(MemorySegment segment) {
        return Jolt.doublePrecision() ? new D(segment) : new F(segment);
    }

    public static ContactSettings at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(Jolt.doublePrecision()
                ? jolt.headers_d.JPC_ContactSettings.ofAddress(ptr.address(), session)
                : jolt.headers_f.JPC_ContactSettings.ofAddress(ptr.address(), session));
    }

    private ContactSettings(MemorySegment segment) {
        super(segment);
    }

    // TODO THIS!!
    public FVec3 getBaseOffsetF() {
        Jolt.assertSinglePrecision();
        return FVec3.at(base_offset$slice(segment));
    }

    public void setBaseOffsetF(FVec3 baseOffset) {
        Jolt.assertSinglePrecision();
        baseOffset.write(base_offset$slice(segment));
    }

    public DVec3 getBaseOffsetD() {
        Jolt.assertDoublePrecision();
        return DVec3.read(base_offset$slice(segment).address());
    }

    public void setBaseOffsetD(DVec3 baseOffset) {
        Jolt.assertDoublePrecision();
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
