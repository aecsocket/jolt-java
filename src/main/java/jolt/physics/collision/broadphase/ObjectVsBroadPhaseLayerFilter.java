package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable;
import jolt.headers.JPJ_ObjectVsBroadPhaseLayerFilter;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable.*;
import static jolt.headers.JPJ_ObjectVsBroadPhaseLayerFilter.*;

public final class ObjectVsBroadPhaseLayerFilter extends AddressedJoltNative {
    public static ObjectVsBroadPhaseLayerFilter at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ObjectVsBroadPhaseLayerFilter(address);
    }

    public static ObjectVsBroadPhaseLayerFilter of(MemorySession session, ObjectVsBroadPhaseLayerFilterFn impl) {
        var vtable = JPC_ObjectVsBroadPhaseLayerFilterVTable.allocate(session);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) ->
                impl.shouldCollide(v1, v2), session);
        ShouldCollide$set(vtable, shouldCollide.address());

        var segment = JPJ_ObjectVsBroadPhaseLayerFilter.allocate(session);
        vtable$set(segment, vtable.address());
        return new ObjectVsBroadPhaseLayerFilter(segment.address());
    }

    private ObjectVsBroadPhaseLayerFilter(MemoryAddress address) {
        super(address);
    }
}
