package jolt.physics.collision.broadphase;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable;
import jolt.headers.JPJ_ObjectVsBroadPhaseLayerFilter;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable.*;
import static jolt.headers.JPJ_ObjectVsBroadPhaseLayerFilter.*;

public final class ObjectVsBroadPhaseLayerFilter extends AbstractJoltNative {
    public static ObjectVsBroadPhaseLayerFilter at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ObjectVsBroadPhaseLayerFilter(address);
    }

    public static ObjectVsBroadPhaseLayerFilter of(MemorySession memory, ObjectVsBroadPhaseLayerFilterFunctions impl) {
        var vtable = JPC_ObjectVsBroadPhaseLayerFilterVTable.allocate(memory);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) ->
                impl.shouldCollide(v1, v2), memory);
        ShouldCollide$set(vtable, shouldCollide.address());

        var handle = JPJ_ObjectVsBroadPhaseLayerFilter.allocate(memory);
        vtable$set(handle, vtable.address());
        return new ObjectVsBroadPhaseLayerFilter(handle.address());
    }

    private ObjectVsBroadPhaseLayerFilter(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() {}
}
