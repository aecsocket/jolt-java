package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPJ_BroadPhaseLayerInterface;
import jolt.headers.JPC_BroadPhaseLayerInterfaceVTable;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerInterfaceVTable.*;
import static jolt.headers.JPJ_BroadPhaseLayerInterface.*;

public final class BroadPhaseLayerInterface extends AddressedJoltNative {
    public static BroadPhaseLayerInterface at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new BroadPhaseLayerInterface(address);
    }

    public static BroadPhaseLayerInterface of(MemorySession session, BroadPhaseLayerInterfaceFunctions impl) {
        var vtable = JPC_BroadPhaseLayerInterfaceVTable.allocate(session);
        MemorySegment getNumBroadPhaseLayers = GetNumBroadPhaseLayers.allocate((v0) ->
                impl.getNumBroadPhaseLayers(), session);
        GetNumBroadPhaseLayers$set(vtable, getNumBroadPhaseLayers.address());
        MemorySegment getBroadPhaseLayer = GetBroadPhaseLayer.allocate((v0, v1) ->
                impl.getBroadPhaseLayer(v1), session);
        GetBroadPhaseLayer$set(vtable, getBroadPhaseLayer.address());

        var segment = JPJ_BroadPhaseLayerInterface.allocate(session);
        vtable$set(segment, vtable.address());
        return new BroadPhaseLayerInterface(segment.address());
    }

    private BroadPhaseLayerInterface(MemoryAddress address) {
        super(address);
    }
}
