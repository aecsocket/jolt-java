package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.*;

import static jolt.headers.JPC_CollideShapeResult.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CollideShapeResult extends SegmentedJoltNative {
    // START Jolt-Value
    private CollideShapeResult(MemorySegment handle) {
        super(handle);
    }

    public static CollideShapeResult at(MemorySegment segment) {
        return new CollideShapeResult(segment);
    }

    public static CollideShapeResult at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollideShapeResult(ofAddress(addr, alloc));
    }

    public static CollideShapeResult of(SegmentAllocator alloc) {
        return new CollideShapeResult(allocate(alloc));
    }
    // END Jolt-Value

    public FVec3 getContactPointOn1() {
        return FVec3.at(shape1_contact_point$slice(handle));
    }

    public FVec3 getContactPointOn2() {
        return FVec3.at(shape2_contact_point$slice(handle));
    }

    public FVec3 getPenetrationAxis() {
        return FVec3.at(penetration_axis$slice(handle));
    }

    public float getPenetrationDepth() {
        return penetration_depth$get(handle);
    }

    public void setPenetrationDepth(float penetrationDepth) {
        penetration_depth$set(handle, penetrationDepth);
    }

    public int getSubShapeId1() {
        return shape1_sub_shape_id$get(handle);
    }

    public void setSubShapeId1(int subShapeId1) {
        shape1_sub_shape_id$set(handle, subShapeId1);
    }

    public int getSubShapeId2() {
        return shape2_sub_shape_id$get(handle);
    }

    public void setSubShapeId2(int subShapeId2) {
        shape2_sub_shape_id$set(handle, subShapeId2);
    }

    public int getBodyId2() {
        return body2_id$get(handle);
    }

    public void setBodyId2(int bodyId2) {
        body2_id$set(handle, bodyId2);
    }

    // TODO getShape1Face
    // TODO getShape2Face

    public float getEarlyOutFraction() {
        return JPC_CollideShapeResult_GetEarlyOutFraction(address());
    }

    public void reversed(CollideShapeResult out) {
        JPC_CollideShapeResult_Reversed(address(), out.address());
    }
}
