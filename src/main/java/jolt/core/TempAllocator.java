package jolt.core;

import jolt.DeletableJoltNative;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TempAllocator extends DeletableJoltNative {
    //region Jolt-Pointer
    private TempAllocator(MemoryAddress handle) {
        super(handle);
    }

    public static TempAllocator at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TempAllocator(addr);
    }
    //endregion Jolt-Pointer

    public static TempAllocator of(int size) {
        return new TempAllocator(JPC_TempAllocator_Create(size));
    }

    @Override
    protected void deleteInternal() {
        JPC_TempAllocator_Destroy(handle);
    }
}
