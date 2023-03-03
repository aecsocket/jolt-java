package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPJ_BroadPhaseLayerInterface;
import jolt.headers.JPC_BroadPhaseLayerInterfaceVTable;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerInterfaceVTable.*;
import static jolt.headers.JPJ_BroadPhaseLayerInterface.*;

public final class BroadPhaseLayerInterface extends AddressedJoltNative {
    // START Jolt-Pointer
    private BroadPhaseLayerInterface(MemoryAddress handle) {
        super(handle);
    }

    public static BroadPhaseLayerInterface at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BroadPhaseLayerInterface(addr);
    }
    // END Jolt-Pointer

    public static BroadPhaseLayerInterface of(MemorySession arena, BroadPhaseLayerInterfaceFn impl) {
        var vtable = JPC_BroadPhaseLayerInterfaceVTable.allocate(arena);
        MemorySegment getNumBroadPhaseLayers = GetNumBroadPhaseLayers.allocate((v0) ->
                impl.getNumBroadPhaseLayers(), arena);
        GetNumBroadPhaseLayers$set(vtable, getNumBroadPhaseLayers.address());
        MemorySegment getBroadPhaseLayer = GetBroadPhaseLayer.allocate((v0, v1) ->
                impl.getBroadPhaseLayer(v1), arena);
        GetBroadPhaseLayer$set(vtable, getBroadPhaseLayer.address());

        var segment = JPJ_BroadPhaseLayerInterface.allocate(arena);
        vtable$set(segment, vtable.address());
        return new BroadPhaseLayerInterface(segment.address());
    }
}
