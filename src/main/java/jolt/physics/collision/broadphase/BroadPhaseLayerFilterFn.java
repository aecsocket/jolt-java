package jolt.physics.collision.broadphase;

public interface BroadPhaseLayerFilterFn {
    boolean shouldCollide(byte layer);
}
