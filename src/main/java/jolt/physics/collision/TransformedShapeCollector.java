package jolt.physics.collision;

import jolt.headers.JPC_TransformedShapeCollectorVTable;
import jolt.headers.JPJ_TransformedShapeCollector;
import jolt.physics.body.Body;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;
import java.util.Collection;

import static jolt.headers.JPJ_TransformedShapeCollector.*;
import static jolt.headers.JPC_TransformedShapeCollectorVTable.*;
import static jolt.headers.JPC_CollisionCollector.*;
import static jolt.headers.JoltPhysicsC.*;

public final class TransformedShapeCollector extends CollisionCollector {
    private static final float INITIAL_EARLY_OUT_FRACTION = Float.MAX_VALUE;

    //region Jolt-Value
    private TransformedShapeCollector(MemorySegment handle) {
        super(handle);
    }

    public static TransformedShapeCollector at(MemorySegment segment) {
        return new TransformedShapeCollector(segment);
    }

    public static TransformedShapeCollector at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TransformedShapeCollector(JPJ_TransformedShapeCollector.ofAddress(addr, alloc));
    }

    public static TransformedShapeCollector of(SegmentAllocator alloc) {
        return new TransformedShapeCollector(JPJ_TransformedShapeCollector.allocate(alloc));
    }
    //endregion Jolt-Value

    public static TransformedShapeCollector of(MemorySession arena, TransformedShapeCollectorFn impl) {
        var vtable = JPC_TransformedShapeCollectorVTable.allocate(arena);
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
                impl.addHit(TransformedShape.at(arena2, v1.address()));
            }
        }, arena);
        AddHit$set(vtable, addHit.address());

        var segment = JPJ_TransformedShapeCollector.allocate(arena);
        vtable$set(segment, vtable.address());
        early_out_fraction$set(collector$slice(segment), INITIAL_EARLY_OUT_FRACTION);
        return new TransformedShapeCollector(segment);
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
        JPC_TransformedShapeCollector_ForceEarlyOut(handle);
    }

    @Override
    public boolean shouldEarlyOut() {
        return JPC_TransformedShapeCollector_ShouldEarlyOut(handle);
    }
}
