package jolt.physics.collision.broadphase;

public interface BroadPhaseLayerInterfaceFunctions {
    int getNumBroadPhaseLayers();

    byte getBroadPhaseLayer(short layer);
}
