package jolt.physics.collision.broadphase;

import jolt.headers.JPJ_CastShapeBodyCollector;
import jolt.headers.JPC_CastShapeBodyCollectorVTable;
import jolt.physics.body.Body;
import jolt.physics.collision.BroadPhaseCastResult;
import jolt.physics.collision.CollisionCollector;
import jolt.physics.collision.TransformedShape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPJ_CastShapeBodyCollector.*;
import static jolt.headers.JPC_CastShapeBodyCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CastShapeBodyCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = 1.0f + Math.ulp(1.0f);

    //region Jolt-Value
    private CastShapeBodyCollector(MemorySegment handle) {
        super(handle);
    }

    public static CastShapeBodyCollector at(MemorySegment segment) {
        return new CastShapeBodyCollector(segment);
    }

    public static CastShapeBodyCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CastShapeBodyCollector(JPJ_CastShapeBodyCollector.ofAddress(addr, alloc));
    }

    public static CastShapeBodyCollector of(SegmentAllocator alloc) {
        return new CastShapeBodyCollector(JPJ_CastShapeBodyCollector.allocate(alloc));
    }
    //endregion Jolt-Value

    public static CastShapeBodyCollector of(MemorySession arena, CastShapeBodyCollectorFn impl) {
        var vtable = JPC_CastShapeBodyCollectorVTable.allocate(arena);
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
        MemorySegment addHit = AddHit.allocate((v0, v1) ->
                impl.addHit(BroadPhaseCastResult.at(v1)), arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_CastShapeBodyCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new CastShapeBodyCollector(segment);
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
        JPC_CollideShapeBodyCollector_ForceEarlyOut(handle);
    }

    @Override
    public boolean shouldEarlyOut() {
        return JPC_CollideShapeBodyCollector_ShouldEarlyOut(handle);
    }
}
