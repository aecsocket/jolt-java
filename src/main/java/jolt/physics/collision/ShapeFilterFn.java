package jolt.physics.collision;

public interface ShapeFilterFn {
    boolean shouldCollide(int subShapeId);

    boolean shouldCollide(int subShapeId1, int subShapeId2);
}
