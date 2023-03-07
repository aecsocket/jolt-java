package jolt.physics.collision.shape;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_ContactSettings.*;
import static jolt.headers.JoltPhysicsC.*;

public final class HeightFieldSamples extends SegmentedJoltNative {
    //region Jolt-Value
    private HeightFieldSamples(MemorySegment handle) {
        super(handle);
    }

    public static HeightFieldSamples at(MemorySegment segment) {
        return new HeightFieldSamples(segment);
    }

    public static HeightFieldSamples at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new HeightFieldSamples(ofAddress(addr, alloc));
    }

    public static HeightFieldSamples of(SegmentAllocator alloc) {
        return new HeightFieldSamples(allocate(alloc));
    }
    //endregion Jolt-Value

    public static HeightFieldSamples of(SegmentAllocator alloc, float... samples) {
        return new HeightFieldSamples(alloc.allocateArray(C_FLOAT, samples));
    }
}
