package jolt.physics.collision.broadphase;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_BroadPhaseLayerInterfaceVTable;
import jolt.headers.JPJ_BroadPhaseLayerInterface;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerInterfaceVTable.*;
import static jolt.headers.JPJ_BroadPhaseLayerInterface.*;

public final class BroadPhaseLayerInterface extends AbstractJoltNative {
    public static BroadPhaseLayerInterface at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BroadPhaseLayerInterface(address);
    }

    public static BroadPhaseLayerInterface of(MemorySession memory, BroadPhaseLayerInterfaceFunctions impl) {
        var vtable = JPC_BroadPhaseLayerInterfaceVTable.allocate(memory);
        MemorySegment getNumBroadPhaseLayers = GetNumBroadPhaseLayers.allocate((v0) ->
                impl.getNumBroadPhaseLayers(), memory);
        GetNumBroadPhaseLayers$set(vtable, getNumBroadPhaseLayers.address());
        MemorySegment getBroadPhaseLayer = GetBroadPhaseLayer.allocate((v0, v1) ->
                impl.getBroadPhaseLayer(v1), memory);
        GetBroadPhaseLayer$set(vtable, getBroadPhaseLayer.address());

        var handle = JPJ_BroadPhaseLayerInterface.allocate(memory);
        vtable$set(handle, vtable.address());
        return new BroadPhaseLayerInterface(handle.address());
    }

    private BroadPhaseLayerInterface(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() {}
}
