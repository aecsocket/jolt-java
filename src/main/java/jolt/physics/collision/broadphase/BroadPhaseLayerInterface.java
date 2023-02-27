package jolt.physics.collision.broadphase;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_BroadPhaseLayerInterfaceVTable;
import jolt.headers.JPJ_BroadPhaseLayerInterface;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerInterfaceVTable.*;

public final class BroadPhaseLayerInterface extends AbstractJoltNative implements BroadPhaseLayerInterfaceFunctions {
    public static BroadPhaseLayerInterface of(MemorySession memory, BroadPhaseLayerInterfaceFunctions impl) {
        var vtable = JPC_BroadPhaseLayerInterfaceVTable.allocate(memory);
        MemorySegment getNumBroadPhaseLayers = GetNumBroadPhaseLayers.allocate((v0) -> impl.getNumBroadPhaseLayers(), memory);
        MemorySegment getBroadPhaseLayer = GetBroadPhaseLayer.allocate((v0, v1) -> impl.getBroadPhaseLayer(v1), memory);
        JPC_BroadPhaseLayerInterfaceVTable.GetNumBroadPhaseLayers$set(vtable, getNumBroadPhaseLayers.address());
        JPC_BroadPhaseLayerInterfaceVTable.GetBroadPhaseLayer$set(vtable, getBroadPhaseLayer.address());

        var handle = JPJ_BroadPhaseLayerInterface.allocate(memory);
        JPJ_BroadPhaseLayerInterface.vtable$set(handle, vtable.address());
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
