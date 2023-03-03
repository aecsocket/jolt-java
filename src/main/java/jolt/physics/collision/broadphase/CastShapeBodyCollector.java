package jolt.physics.collision.broadphase;

import jolt.headers.JPC_CastShapeBodyCollector;
import jolt.headers.JPC_CastShapeBodyCollectorVTable;
import jolt.physics.body.Body;
import jolt.physics.collision.BroadPhaseCastResult;
import jolt.physics.collision.CollisionCollector;
import jolt.physics.collision.TransformedShape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;
import java.util.Collection;

import static jolt.headers.JPC_CastShapeBodyCollector.*;
import static jolt.headers.JPC_CastShapeBodyCollectorVTable.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CastShapeBodyCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = Math.ulp(1.0f);

    // START Jolt-Value
    private CastShapeBodyCollector(MemorySegment handle) {
        super(handle);
    }

    public static CastShapeBodyCollector at(MemorySegment segment) {
        return new CastShapeBodyCollector(segment);
    }

    public static CastShapeBodyCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CastShapeBodyCollector(JPC_CastShapeBodyCollector.ofAddress(addr, alloc));
    }

    public static CastShapeBodyCollector of(SegmentAllocator alloc) {
        return new CastShapeBodyCollector(JPC_CastShapeBodyCollector.allocate(alloc));
    }
    // END Jolt-Value

    public static CastShapeBodyCollector of(MemorySession arena, CastShapeBodyCollectorFn impl) {
        var vtable = JPC_CastShapeBodyCollectorVTable.allocate(arena);
        MemorySegment reset = Reset.allocate((v0) -> {
            try (var arena2 = MemorySession.openConfined()) {
                early_out_fraction$set(JPC_CastShapeBodyCollector.ofAddress(v0, arena2), INITIAL_EARLY_OUT_FRACTION);
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

        var segment = JPC_CastShapeBodyCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(segment, INITIAL_EARLY_OUT_FRACTION);
        return new CastShapeBodyCollector(segment);
    }

    public static CastShapeBodyCollector collectingInto(MemorySession session, Collection<? super BroadPhaseCastResult> out) {
        return CastShapeBodyCollector.of(session, out::add);
    }


    @Override
    public void reset() {
        try (var session = MemorySession.openConfined()) {
            var reset = Reset$get(session.allocate(C_POINTER, vtable$get(handle)));
            Reset.ofAddress(reset, session).apply(handle.address());
        }
    }

    @Override
    public float getEarlyOutFraction() {
        return early_out_fraction$get(handle);
    }

    @Override
    public TransformedShape getContext(MemorySession arena) {
        return TransformedShape.at(arena, context$get(handle));
    }

    @Override
    public void setContext(TransformedShape context) {
        context$set(handle, context.address());
    }

    @Override
    public void updateEarlyOutFraction(float fraction) {
        JPC_CollideShapeBodyCollector_UpdateEarlyOutFraction(handle, fraction);
    }

    @Override
    public void resetEarlyOutFraction(float fraction) {
        JPC_CollideShapeBodyCollector_ResetEarlyOutFraction(handle, fraction);
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
