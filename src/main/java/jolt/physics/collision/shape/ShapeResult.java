package jolt.physics.collision.shape;

import jolt.core.Result;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_ShapeResult.*;
import static jolt.headers.JoltPhysicsC.*;

public final class ShapeResult extends Result<Shape> {
    //region Jolt-Value
    private ShapeResult(MemorySegment handle) {
        super(handle);
    }

    public static ShapeResult at(MemorySegment segment) {
        return new ShapeResult(segment);
    }

    public static ShapeResult at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ShapeResult(ofAddress(addr, alloc));
    }

    public static ShapeResult of(SegmentAllocator alloc) {
        return new ShapeResult(allocate(alloc));
    }
    //endregion Jolt-Value

    @Override
    public boolean hasError() {
        return result$get(handle) == MemoryAddress.NULL;
    }

    @Override
    public @Nullable Shape or() {
        return Shape.at(result$get(handle));
    }

    @Override
    public String getError() {
        return error$slice(handle).getUtf8String(0);
    }
}
