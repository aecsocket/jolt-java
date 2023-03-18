package jolt.physics.collision;

import jolt.headers.JPC_CastRayCollectorVTable;
import jolt.headers.JPJ_CastRayCollector;
import jolt.physics.body.Body;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPJ_CastRayCollector.*;
import static jolt.headers.JPC_CastRayCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CastRayCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = 1.0f + Math.ulp(1.0f);

    //region Jolt-Value
    private CastRayCollector(MemorySegment handle) {
        super(handle);
    }

    public static CastRayCollector at(MemorySegment segment) {
        return new CastRayCollector(segment);
    }

    public static CastRayCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CastRayCollector(JPJ_CastRayCollector.ofAddress(addr, alloc));
    }

    public static CastRayCollector of(SegmentAllocator alloc) {
        return new CastRayCollector(JPJ_CastRayCollector.allocate(alloc));
    }
    //endregion Jolt-Value

    public static CastRayCollector of(MemorySession arena, CastRayCollectorFn impl) {
        var vtable = JPC_CastRayCollectorVTable.allocate(arena);
        @SuppressWarnings("DataFlowIssue")
        MemorySegment reset = Reset.allocate((v0) -> {
            try (var arena2 = MemorySession.openConfined()) {
                at(arena2, v0).resetEarlyOutFraction(INITIAL_EARLY_OUT_FRACTION);
            }
        }, arena);
        Reset$set(vtable, reset.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onBody = OnBody.allocate((v0, v1) ->
                impl.onBody(Body.at(v1.address())), arena);
        OnBody$set(vtable, onBody.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment addHit = AddHit.allocate((v0, v1) -> {
            try (var arena2 = MemorySession.openConfined()) {
                impl.addHit(RayCastResult.at(arena2, v1.address()));
            }
        }, arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_CastRayCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new CastRayCollector(segment);
    }

    @Override
    public float getEarlyOutFraction() {
        return early_out_fraction$get(collector$slice(handle));
    }

    @Override
    public TransformedShape getContext(MemorySession arena) {
        return TransformedShape.at(arena, context$get(collector$slice(handle)));
    }

    @Override
    public void setContext(TransformedShape context) {
        context$set(collector$slice(handle), context.address());
    }

    @Override
    public void forceEarlyOut() {
        JPC_CastRayCollector_ForceEarlyOut(handle);
    }

    @Override
    public boolean shouldEarlyOut() {
        return JPC_CastRayCollector_ShouldEarlyOut(handle);
    }
}
