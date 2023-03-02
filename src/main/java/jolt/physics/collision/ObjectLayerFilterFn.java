package jolt.physics.collision;

public interface ObjectLayerFilterFn {
    boolean shouldCollide(short layer);
}
