package jolt.physics.collision.shape;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_ContactSettings.allocate;
import static jolt.headers.JPC_ContactSettings.ofAddress;
import static jolt.headers.JoltPhysicsC.C_CHAR;
import static jolt.headers.JoltPhysicsC.C_FLOAT;

public final class HeightFieldMaterialIndices extends SegmentedJoltNative {
    //region Jolt-Value
    private HeightFieldMaterialIndices(MemorySegment handle) {
        super(handle);
    }

    public static HeightFieldMaterialIndices at(MemorySegment segment) {
        return new HeightFieldMaterialIndices(segment);
    }

    public static HeightFieldMaterialIndices at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new HeightFieldMaterialIndices(ofAddress(addr, alloc));
    }

    public static HeightFieldMaterialIndices of(SegmentAllocator alloc) {
        return new HeightFieldMaterialIndices(allocate(alloc));
    }
    //endregion Jolt-Value

    public static HeightFieldMaterialIndices of(SegmentAllocator alloc, byte... indices) {
        return new HeightFieldMaterialIndices(alloc.allocateArray(C_CHAR, indices));
    }
}
