package jolt.physics.collision.broadphase;

public interface BroadPhaseLayerFilterFunctions {
    boolean shouldCollide(byte layer);
}
