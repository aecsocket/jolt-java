package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.physics.collision.BroadPhaseCastResult;
import jolt.physics.body.Body;
import jolt.headers.JPJ_RayCastBodyCollector;
import jolt.headers.JPC_RayCastBodyCollectorVTable;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPJ_RayCastBodyCollector.*;
import static jolt.headers.JPC_RayCastBodyCollectorVTable.*;

public final class RayCastBodyCollector extends AddressedJoltNative {
    public static RayCastBodyCollector at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new RayCastBodyCollector(address);
    }

    public static RayCastBodyCollector of(MemorySession session, RayCastBodyCollectorFunctions impl) {
        var vtable = JPC_RayCastBodyCollectorVTable.allocate(session);
        MemorySegment onBody = OnBody.allocate((v0, v1) ->
                impl.onBody(Body.at(v1.address())), session);
        OnBody$set(vtable, onBody.address());
        MemorySegment addHit = AddHit.allocate((v0, v1) -> {
            try (var s = MemorySession.openConfined()) {
                impl.addHit(BroadPhaseCastResult.at(v1));
            }
        }, session);

        var segment = JPJ_RayCastBodyCollector.allocate(session);
        vtable$set(segment, vtable.address());
        return new RayCastBodyCollector(segment.address());
    }

    private RayCastBodyCollector(MemoryAddress address) {
        super(address);
    }
}
