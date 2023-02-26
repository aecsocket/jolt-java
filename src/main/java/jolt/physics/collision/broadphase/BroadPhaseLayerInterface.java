package jolt.physics.collision.broadphase;

import jolt.AbstractJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerInterfaceVTable.*;

public final class BroadPhaseLayerInterface extends AbstractJoltNative implements BroadPhaseLayerInterfaceFunctions {
    public static BroadPhaseLayerInterface of(MemorySession memory, BroadPhaseLayerInterfaceFunctions impl) {
        var handle = allocate(memory);
        MemorySegment getNumBroadPhaseLayers = GetNumBroadPhaseLayers.allocate((v0) -> impl.getNumBroadPhaseLayers(), memory);
        MemorySegment getBroadPhaseLayer = GetBroadPhaseLayer.allocate((v0, v1) -> impl.getBroadPhaseLayer(v1), memory);
        GetNumBroadPhaseLayers$set(handle, getNumBroadPhaseLayers.address());
        GetBroadPhaseLayer$set(handle, getBroadPhaseLayer.address());
        return new BroadPhaseLayerInterface(handle.address(), impl);
    }

    private final BroadPhaseLayerInterfaceFunctions impl;

    public BroadPhaseLayerInterface(MemoryAddress address, BroadPhaseLayerInterfaceFunctions impl) {
        super(address);
        this.impl = impl;
    }

    @Override
    protected void deleteInternal() {}

    @Override
    public int getNumBroadPhaseLayers() { return impl.getNumBroadPhaseLayers(); }
    @Override
    public byte getBroadPhaseLayer(short layer) { return impl.getBroadPhaseLayer(layer); }
}
