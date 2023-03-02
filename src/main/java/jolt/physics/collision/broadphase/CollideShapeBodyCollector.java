package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_CollideShapeBodyCollector;
import jolt.headers.JPC_CollideShapeBodyCollectorVTable;
import jolt.physics.body.Body;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JPC_CollideShapeBodyCollector.early_out_fraction$set;
import static jolt.headers.JPC_CollideShapeBodyCollector.vtable$set;
import static jolt.headers.JPC_CollideShapeBodyCollectorVTable.*;

public final class CollideShapeBodyCollector extends AddressedJoltNative {
    private static final float INITIAL_EARLY_OUT_FRACTION = Float.MAX_VALUE;

    public static CollideShapeBodyCollector at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new CollideShapeBodyCollector(address);
    }

    public static CollideShapeBodyCollector of(MemorySession session, CollideShapeBodyCollectorFn impl) {
        var vtable = JPC_CollideShapeBodyCollectorVTable.allocate(session);
        MemorySegment reset = Reset.allocate((v0) -> {
            try (var s = MemorySession.openConfined()) {
                early_out_fraction$set(JPC_CollideShapeBodyCollector.ofAddress(v0, s), INITIAL_EARLY_OUT_FRACTION);
            }
        }, session);
        Reset$set(vtable, reset.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onBody = OnBody.allocate((v0, v1) ->
                impl.onBody(Body.at(v1.address())), session);
        OnBody$set(vtable, onBody.address());
        MemorySegment addHit = AddHit.allocate((v0, v1) ->
                impl.addHit(v1), session);
        AddHit$set(vtable, addHit.address());

        var segment = JPC_CollideShapeBodyCollector.allocate(session);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(segment, INITIAL_EARLY_OUT_FRACTION);
        return new CollideShapeBodyCollector(segment.address());
    }

    public static CollideShapeBodyCollector into(MemorySession session, Collection<Integer> out) {
        return CollideShapeBodyCollector.of(session, out::add);
    }

    private CollideShapeBodyCollector(MemoryAddress address) {
        super(address);
    }
}
