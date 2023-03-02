package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.headers.JPC_CollideShapeResult;
import jolt.math.FVec3;
import jolt.physics.collision.shape.SubShapeIdPair;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_CollideShapeResult.*;

public final class CollideShapeResult extends SegmentedJoltNative {
    public static CollideShapeResult at(MemorySegment segment) {
        return new CollideShapeResult(segment);
    }

    public static CollideShapeResult at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    public static CollideShapeResult allocate(MemorySession session) {
        return new CollideShapeResult(JPC_CollideShapeResult.allocate(session));
    }

    private CollideShapeResult(MemorySegment segment) {
        super(segment);
    }

    public FVec3 getContactPointOn1() {
        return FVec3.at(shape1_contact_point$slice(segment));
    }

    public FVec3 getContactPointOn2() {
        return FVec3.at(shape2_contact_point$slice(segment));
    }

    public FVec3 getPenetrationAxis() {
        return FVec3.at(penetration_axis$slice(segment));
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

    public int getBodyId2() {
        return body2_id$get(segment);
    }

    public void setBodyId2(int bodyId2) {
        body2_id$set(segment, bodyId2);
    }

    // TODO getShape1Face
    // TODO getShape2Face

    public float getEarlyOutFraction() {
        return -getPenetrationDepth();
    }

    public CollideShapeResult reversed(MemorySession session) {
        var result = allocate(session);
        result.getContactPointOn2().set(getContactPointOn1());
        result.getContactPointOn1().set(getContactPointOn2());
        try (var s = MemorySession.openConfined()) {
            var penetrationAxis = getPenetrationAxis();
            result.getPenetrationAxis().set(FVec3.create(s, -penetrationAxis.getX(), -penetrationAxis.getY(), -penetrationAxis.getZ()));
        }
        result.setPenetrationDepth(getPenetrationDepth());
        result.setSubShapeId2(getSubShapeId1());
        result.setSubShapeId1(getSubShapeId2());
        result.setBodyId2(getBodyId2());
        // TODO setShape2Face
        // TODO setShape1Face
        return result;
    }
}
