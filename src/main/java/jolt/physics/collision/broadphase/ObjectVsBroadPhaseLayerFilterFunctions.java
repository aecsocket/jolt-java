package jolt.physics.collision.broadphase;

public interface ObjectVsBroadPhaseLayerFilterFunctions {
    boolean shouldCollide(short layer1, byte layer2);
}
