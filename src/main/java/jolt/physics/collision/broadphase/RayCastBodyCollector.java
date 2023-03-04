package jolt.physics.collision.broadphase;

import jolt.headers.JPJ_RayCastBodyCollector;
import jolt.physics.collision.*;
import jolt.physics.body.Body;
import jolt.headers.JPC_RayCastBodyCollectorVTable;

import java.lang.foreign.*;
import java.util.Collection;

import static jolt.headers.JPJ_RayCastBodyCollector.*;
import static jolt.headers.JPC_RayCastBodyCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class RayCastBodyCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = Math.ulp(1.0f);

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
        MemorySegment reset = Reset.allocate((v0) ->
                JPC_CollisionCollector_Reset(v0), arena);
        Reset$set(vtable, reset.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onBody = OnBody.allocate((v0, v1) ->
                impl.onBody(Body.at(v1.address())), arena);
        OnBody$set(vtable, onBody.address());
        MemorySegment addHit = AddHit.allocate((v0, v1) ->
                impl.addHit(BroadPhaseCastResult.at(v1)), arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_RayCastBodyCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new RayCastBodyCollector(segment);
    }

    public static RayCastBodyCollector collectingInto(MemorySession session, Collection<? super BroadPhaseCastResult> out) {
        return RayCastBodyCollector.of(session, out::add);
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
