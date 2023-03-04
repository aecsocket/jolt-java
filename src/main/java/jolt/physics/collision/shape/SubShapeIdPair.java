package jolt.physics.collision.shape;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JPC_SubShapeIDPair.*;

public final class SubShapeIdPair extends SegmentedJoltNative {
    //region Jolt-Value
    private SubShapeIdPair(MemorySegment handle) {
        super(handle);
    }

    public static SubShapeIdPair at(MemorySegment segment) {
        return new SubShapeIdPair(segment);
    }

    public static SubShapeIdPair at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SubShapeIdPair(ofAddress(addr, alloc));
    }

    public static SubShapeIdPair of(SegmentAllocator alloc) {
        return new SubShapeIdPair(allocate(alloc));
    }
    //endregion Jolt-Value

    public int getBodyId1() {
        return first.body_id$get(first$slice(handle));
    }

    public void setBodyId1(int bodyId1) {
        first.body_id$set(first$slice(handle), bodyId1);
    }

    public int getSubShapeId1() {
        return first.sub_shape_id$get(first$slice(handle));
    }

    public void setSubShapeId1(int subShapeId1) {
        first.sub_shape_id$set(first$slice(handle), subShapeId1);
    }

    public int getBodyId2() {
        return second.body_id$get(second$slice(handle));
    }

    public void setBodyId2(int bodyId2) {
        second.body_id$set(second$slice(handle), bodyId2);
    }

    public int getSubShapeId2() {
        return second.sub_shape_id$get(second$slice(handle));
    }

    public void setSubShapeId2(int subShapeId2) {
        second.sub_shape_id$set(second$slice(handle), subShapeId2);
    }
}
