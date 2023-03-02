package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPJ_BroadPhaseLayerFilter;
import jolt.headers.JPC_BroadPhaseLayerFilterVTable;

import java.lang.foreign.*;

import static jolt.headers.JPC_BroadPhaseLayerFilterVTable.*;
import static jolt.headers.JPJ_BroadPhaseLayerFilter.*;

public final class BroadPhaseLayerFilter extends AddressedJoltNative {
    private static BroadPhaseLayerFilter passthrough;

    public static BroadPhaseLayerFilter at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new BroadPhaseLayerFilter(address);
    }

    public static BroadPhaseLayerFilter of(MemorySession session, BroadPhaseLayerFilterFn impl) {
        var vtable = JPC_BroadPhaseLayerFilterVTable.allocate(session);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1) ->
                impl.shouldCollide(v1), session);
        ShouldCollide$set(vtable, shouldCollide.address());

        var segment = JPJ_BroadPhaseLayerFilter.allocate(session);
        vtable$set(segment, vtable.address());
        return new BroadPhaseLayerFilter(segment.address());
    }

    public static BroadPhaseLayerFilter passthrough() {
        if (passthrough == null) {
            passthrough = BroadPhaseLayerFilter.of(MemorySession.global(), x -> true);
        }
        return passthrough;
    }

    private BroadPhaseLayerFilter(MemoryAddress address) {
        super(address);
    }
}
