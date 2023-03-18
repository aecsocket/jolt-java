package jolt.physics.collision.broadphase;

import jolt.headers.JPJ_RayCastBodyCollector;
import jolt.physics.collision.*;
import jolt.physics.body.Body;
import jolt.headers.JPC_RayCastBodyCollectorVTable;

import java.lang.foreign.*;

import static jolt.headers.JPJ_RayCastBodyCollector.*;
import static jolt.headers.JPC_RayCastBodyCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class RayCastBodyCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = 1.0f + Math.ulp(1.0f);

    //region Jolt-Value
    private RayCastBodyCollector(MemorySegment handle) {
        super(handle);
    }

    public static RayCastBodyCollector at(MemorySegment segment) {
        return new RayCastBodyCollector(segment);
    }

    public static RayCastBodyCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new RayCastBodyCollector(JPJ_RayCastBodyCollector.ofAddress(addr, alloc));
    }

    public static RayCastBodyCollector of(SegmentAllocator alloc) {
        return new RayCastBodyCollector(JPJ_RayCastBodyCollector.allocate(alloc));
    }
    //endregion Jolt-Value

    public static RayCastBodyCollector of(MemorySession arena, RayCastBodyCollectorFn impl) {
        var vtable = JPC_RayCastBodyCollectorVTable.allocate(arena);
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
                impl.addHit(BroadPhaseCastResult.at(arena2, v1.address()));
            }
        }, arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_RayCastBodyCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new RayCastBodyCollector(segment);
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
