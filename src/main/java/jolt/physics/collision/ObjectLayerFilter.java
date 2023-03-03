package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPJ_ObjectLayerFilter;
import jolt.headers.JPC_ObjectLayerFilterVTable;

import java.lang.foreign.*;

import static jolt.headers.JPC_ObjectLayerFilterVTable.*;
import static jolt.headers.JPJ_ObjectLayerFilter.*;

public final class ObjectLayerFilter extends AddressedJoltNative {
    // START Jolt-Pointer
    private ObjectLayerFilter(MemoryAddress handle) {
        super(handle);
    }

    public static ObjectLayerFilter at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ObjectLayerFilter(addr);
    }
    // END Jolt-Pointer

    public static ObjectLayerFilter of(MemorySession arena, ObjectLayerFilterFn impl) {
        var vtable = JPC_ObjectLayerFilterVTable.allocate(arena);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1) ->
                impl.shouldCollide(v1), arena);
        ShouldCollide$set(vtable, shouldCollide.address());

        var segment = JPJ_ObjectLayerFilter.allocate(arena);
        vtable$set(segment, vtable.address());
        return new ObjectLayerFilter(segment.address());
    }

    private static ObjectLayerFilter passthrough;

    public static ObjectLayerFilter passthrough() {
        if (passthrough == null) {
            passthrough = ObjectLayerFilter.of(MemorySession.global(), a -> true);
        }
        return passthrough;
    }
}
