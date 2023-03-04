package jolt.physics.collision;

import jolt.headers.JPC_CollidePointCollectorVTable;
import jolt.headers.JPJ_CollidePointCollector;
import jolt.physics.body.Body;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;
import java.util.Collection;

import static jolt.headers.JPJ_CollidePointCollector.*;
import static jolt.headers.JPC_CollidePointCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class CollidePointCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = Float.MAX_VALUE;

    //region Jolt-Value
    private CollidePointCollector(MemorySegment handle) {
        super(handle);
    }

    public static CollidePointCollector at(MemorySegment segment) {
        return new CollidePointCollector(segment);
    }

    public static CollidePointCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CollidePointCollector(JPJ_CollidePointCollector.ofAddress(addr, alloc));
    }

    public static CollidePointCollector of(SegmentAllocator alloc) {
        return new CollidePointCollector(JPJ_CollidePointCollector.allocate(alloc));
    }
    //endregion Jolt-Value

    public static CollidePointCollector of(MemorySession arena, CollidePointCollectorFn impl) {
        var vtable = JPC_CollidePointCollectorVTable.allocate(arena);
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
                impl.addHit(CollidePointResult.at(v1)), arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_CollidePointCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new CollidePointCollector(segment);
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
        JPC_CollidePointCollector_ForceEarlyOut(handle);
    }

    @Override
    public boolean shouldEarlyOut() {
        return JPC_CollidePointCollector_ShouldEarlyOut(handle);
    }
}
