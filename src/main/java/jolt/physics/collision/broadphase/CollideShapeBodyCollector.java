package jolt.physics.collision.broadphase;

import jolt.headers.JPC_CollisionCollector;
import jolt.headers.JPJ_CollideShapeBodyCollector;
import jolt.headers.JPC_CollideShapeBodyCollectorVTable;
import jolt.physics.body.Body;
import jolt.physics.collision.CollisionCollector;
import jolt.physics.collision.TransformedShape;

import java.lang.foreign.*;
import java.util.Collection;

import static jolt.headers.JPJ_CollideShapeBodyCollector.*;
import static jolt.headers.JPC_CollideShapeBodyCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CollideShapeBodyCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = Float.MAX_VALUE;

    //region Jolt-Value
    private CollideShapeBodyCollector(MemorySegment handle) {
        super(handle);
    }

    public static CollideShapeBodyCollector at(MemorySegment segment) {
        return new CollideShapeBodyCollector(segment);
    }

    public static CollideShapeBodyCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollideShapeBodyCollector(JPJ_CollideShapeBodyCollector.ofAddress(addr, alloc));
    }

    public static CollideShapeBodyCollector of(SegmentAllocator alloc) {
        return new CollideShapeBodyCollector(JPJ_CollideShapeBodyCollector.allocate(alloc));
    }
    //endregion Jolt-Value

    public static CollideShapeBodyCollector of(MemorySession arena, CollideShapeBodyCollectorFn impl) {
        var vtable = JPC_CollideShapeBodyCollectorVTable.allocate(arena);
        MemorySegment reset = Reset.allocate((v0) ->
                JPC_CollisionCollector_Reset(v0), arena);
        Reset$set(vtable, reset.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onBody = OnBody.allocate((v0, v1) ->
                impl.onBody(Body.at(v1.address())), arena);
        OnBody$set(vtable, onBody.address());
        MemorySegment addHit = AddHit.allocate((v0, v1) ->
                impl.addHit(v1), arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_CollideShapeBodyCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new CollideShapeBodyCollector(segment);
    }

    public static CollideShapeBodyCollector collectingInto(MemorySession session, Collection<? super Integer> out) {
        return CollideShapeBodyCollector.of(session, out::add);
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
