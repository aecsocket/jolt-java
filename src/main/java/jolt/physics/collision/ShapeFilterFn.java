package jolt.physics.collision;

public interface ShapeFilterFn {
    boolean shouldPairCollide(int subShapeId);

    boolean shouldPairCollide(int subShapeId1, int subShapeId2);
}
