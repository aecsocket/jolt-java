package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_RayCastBodyCollector;
import jolt.physics.collision.BroadPhaseCastResult;
import jolt.physics.body.Body;
import jolt.headers.JPC_RayCastBodyCollectorVTable;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JPC_RayCastBodyCollector.*;
import static jolt.headers.JPC_RayCastBodyCollectorVTable.*;

public final class RayCastBodyCollector extends AddressedJoltNative {
    private static final float INITIAL_EARLY_OUT_FRACTION = Math.ulp(1.0f);

    public static RayCastBodyCollector at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new RayCastBodyCollector(address);
    }

    public static RayCastBodyCollector of(MemorySession session, RayCastBodyCollectorFn impl) {
        var vtable = JPC_RayCastBodyCollectorVTable.allocate(session);
        MemorySegment reset = Reset.allocate((v0) -> {
            try (var s = MemorySession.openConfined()) {
                early_out_fraction$set(JPC_RayCastBodyCollector.ofAddress(v0, s), INITIAL_EARLY_OUT_FRACTION);
            }
        }, session);
        Reset$set(vtable, reset.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onBody = OnBody.allocate((v0, v1) ->
                impl.onBody(Body.at(v1.address())), session);
        OnBody$set(vtable, onBody.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment addHit = AddHit.allocate((v0, v1) -> {
            impl.addHit(BroadPhaseCastResult.at(v1));
        }, session);
        AddHit$set(vtable, addHit.address());

        var segment = JPC_RayCastBodyCollector.allocate(session);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(segment, INITIAL_EARLY_OUT_FRACTION);
        return new RayCastBodyCollector(segment.address());
    }

    public static RayCastBodyCollector into(MemorySession session, Collection<BroadPhaseCastResult> out) {
        return RayCastBodyCollector.of(session, out::add);
    }

    private RayCastBodyCollector(MemoryAddress address) {
        super(address);
    }
}
