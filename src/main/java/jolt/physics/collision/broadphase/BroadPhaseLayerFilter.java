package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPJ_BroadPhaseLayerFilter;
import jolt.headers.JPC_BroadPhaseLayerFilterVTable;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerFilterVTable.*;
import static jolt.headers.JPJ_BroadPhaseLayerFilter.*;

public final class BroadPhaseLayerFilter extends AddressedJoltNative {
    // START Jolt-Pointer
    private BroadPhaseLayerFilter(MemoryAddress handle) {
        super(handle);
    }

    public static BroadPhaseLayerFilter at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BroadPhaseLayerFilter(addr);
    }
    // END Jolt-Pointer

    public static BroadPhaseLayerFilter of(MemorySession arena, BroadPhaseLayerFilterFn impl) {
        var vtable = JPC_BroadPhaseLayerFilterVTable.allocate(arena);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1) ->
                impl.shouldCollide(v1), arena);
        ShouldCollide$set(vtable, shouldCollide.address());

        var segment = JPJ_BroadPhaseLayerFilter.allocate(arena);
        vtable$set(segment, vtable.address());
        return new BroadPhaseLayerFilter(segment.address());
    }

    private static BroadPhaseLayerFilter passthrough;

    public static BroadPhaseLayerFilter passthrough() {
        if (passthrough == null) {
            passthrough = BroadPhaseLayerFilter.of(MemorySession.global(), a -> true);
        }
        return passthrough;
    }
}
