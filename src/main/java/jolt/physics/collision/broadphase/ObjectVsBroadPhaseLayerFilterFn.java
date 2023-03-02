package jolt.physics.collision.broadphase;

public interface ObjectVsBroadPhaseLayerFilterFn {
    boolean shouldCollide(short layer1, byte layer2);
}
