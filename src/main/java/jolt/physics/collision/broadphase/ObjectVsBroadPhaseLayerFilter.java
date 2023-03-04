package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.SegmentedJoltNative;
import jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable;
import jolt.headers.JPJ_ObjectVsBroadPhaseLayerFilter;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable.*;
import static jolt.headers.JPJ_ObjectVsBroadPhaseLayerFilter.*;

public final class ObjectVsBroadPhaseLayerFilter extends AddressedJoltNative {
    //region Jolt-Pointer
    private ObjectVsBroadPhaseLayerFilter(MemoryAddress handle) {
        super(handle);
    }

    public static ObjectVsBroadPhaseLayerFilter at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ObjectVsBroadPhaseLayerFilter(addr);
    }
    //endregion Jolt-Pointer

    public static ObjectVsBroadPhaseLayerFilter of(MemorySession arena, ObjectVsBroadPhaseLayerFilterFn impl) {
        var vtable = JPC_ObjectVsBroadPhaseLayerFilterVTable.allocate(arena);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) ->
                impl.shouldCollide(v1, v2), arena);
        ShouldCollide$set(vtable, shouldCollide.address());

        var segment = JPJ_ObjectVsBroadPhaseLayerFilter.allocate(arena);
        vtable$set(segment, vtable.address());
        return new ObjectVsBroadPhaseLayerFilter(segment.address());
    }

    private static ObjectVsBroadPhaseLayerFilter passthrough;

    public static ObjectVsBroadPhaseLayerFilter passthrough() {
        if (passthrough == null) {
            passthrough = ObjectVsBroadPhaseLayerFilter.of(MemorySession.global(), (a, b) -> true);
        }
        return passthrough;
    }
}
