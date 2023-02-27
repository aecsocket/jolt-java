package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.Vec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_CollideShapeResult.*;

public final class CollideShapeResult extends SegmentedJoltNative {
    public static CollideShapeResult at(MemorySegment memory) {
        return memory.address() == MemoryAddress.NULL ? null : new CollideShapeResult(memory);
    }

    public static CollideShapeResult at(MemorySession session, MemoryAddress address) {
        return at(ofAddress(address, session));
    }

    private CollideShapeResult(MemorySegment segment) {
        super(segment);
    }

    @Override
    protected void deleteInternal() { throw cannotDelete(); }

    public Vec3 getContactPointOn1() {
        return readVec3(shape1_contact_point$slice(segment).address());
    }

    public Vec3 getContactPointOn2() {
        return readVec3(shape2_contact_point$slice(segment).address());
    }

    public Vec3 getPenetrationAxis() {
        return readVec3(penetration_axis$slice(segment).address());
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

    public int getBodyId2() {
        return body2_id$get(segment);
    }

    // TODO getShape1Face
    // TODO getShape2Face
}
