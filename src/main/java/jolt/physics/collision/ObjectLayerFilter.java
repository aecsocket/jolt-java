package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPJ_ObjectLayerFilter;
import jolt.headers.JPC_ObjectLayerFilterVTable;
import jolt.physics.collision.broadphase.BroadPhaseLayerFilter;

import java.lang.foreign.*;

import static jolt.headers.JPC_ObjectLayerFilterVTable.*;
import static jolt.headers.JPJ_ObjectLayerFilter.*;

public final class ObjectLayerFilter extends AddressedJoltNative {
    public static ObjectLayerFilter at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ObjectLayerFilter(address);
    }

    public static ObjectLayerFilter of(MemorySession session, ObjectLayerFilterFunctions impl) {
        var vtable = JPC_ObjectLayerFilterVTable.allocate(session);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1) ->
                impl.shouldCollide(v1), session);
        ShouldCollide$set(vtable, shouldCollide.address());

        var segment = JPJ_ObjectLayerFilter.allocate(session);
        vtable$set(segment, vtable.address());
        return new ObjectLayerFilter(segment.address());
    }

    private static ObjectLayerFilter passthrough;

    public static ObjectLayerFilter passthrough() {
        if (passthrough == null) {
            passthrough = ObjectLayerFilter.of(MemorySession.global(), x -> true);
        }
        return passthrough;
    }

    private ObjectLayerFilter(MemoryAddress address) {
        super(address);
    }
}
