package jolt.physics.collision.broadphase;

public interface BroadPhaseLayerInterfaceFn {
    int getNumBroadPhaseLayers();

    byte getBroadPhaseLayer(short layer);
}
