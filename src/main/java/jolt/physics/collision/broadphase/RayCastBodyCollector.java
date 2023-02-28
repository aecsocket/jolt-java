package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_BroadPhaseLayerInterface;
import jolt.headers.JPC_BroadPhaseLayerInterfaceVTable;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_BroadPhaseLayerInterface.*;
import static jolt.headers.JPC_BroadPhaseLayerInterfaceVTable.*;

public final class RayCastBodyCollector extends AddressedJoltNative {
    public static RayCastBodyCollector at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new RayCastBodyCollector(address);
    }

    public static RayCastBodyCollector of(MemorySession session, BroadPhaseLayerInterfaceFunctions impl) {
        var vtable = JPC_BroadPhaseLayerInterfaceVTable.allocate(session);
        MemorySegment getNumBroadPhaseLayers = GetNumBroadPhaseLayers.allocate((v0) ->
                impl.getNumBroadPhaseLayers(), session);
        GetNumBroadPhaseLayers$set(vtable, getNumBroadPhaseLayers.address());
        MemorySegment getBroadPhaseLayer = GetBroadPhaseLayer.allocate((v0, v1) ->
                impl.getBroadPhaseLayer(v1), session);
        GetBroadPhaseLayer$set(vtable, getBroadPhaseLayer.address());

        var segment = JPC_BroadPhaseLayerInterface.allocate(session);
        vtable$set(segment, vtable.address());
        return new RayCastBodyCollector(segment.address());
    }

    private RayCastBodyCollector(MemoryAddress address) {
        super(address);
    }
}
