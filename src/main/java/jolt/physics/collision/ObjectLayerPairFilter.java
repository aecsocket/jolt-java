package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_ObjectLayerPairFilterVTable;
import jolt.headers.JPJ_ObjectLayerPairFilter;

import java.lang.foreign.*;

import static jolt.headers.JPC_ObjectLayerPairFilterVTable.*;
import static jolt.headers.JPJ_ObjectLayerPairFilter.*;

public final class ObjectLayerPairFilter extends AddressedJoltNative {
    // START Jolt-Pointer
    private ObjectLayerPairFilter(MemoryAddress handle) {
        super(handle);
    }

    public static ObjectLayerPairFilter at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ObjectLayerPairFilter(addr);
    }
    // END Jolt-Pointer

    public static ObjectLayerPairFilter of(MemorySession session, ObjectLayerPairFilterFn impl) {
        var vtable = JPC_ObjectLayerPairFilterVTable.allocate(session);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) -> impl.shouldCollide(v1, v2), session);
        ShouldCollide$set(vtable, shouldCollide.address());

        var handle = JPJ_ObjectLayerPairFilter.allocate(session);
        vtable$set(handle, vtable.address());
        return new ObjectLayerPairFilter(handle.address());
    }
    
    private static ObjectLayerPairFilter passthrough;

    public static ObjectLayerPairFilter passthrough() {
        if (passthrough == null) {
            passthrough = ObjectLayerPairFilter.of(MemorySession.global(), (a, b) -> true);
        }
        return passthrough;
    }
}
