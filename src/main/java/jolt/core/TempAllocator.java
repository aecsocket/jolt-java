package jolt.core;

import jolt.DestroyableJoltNative;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TempAllocator extends DestroyableJoltNative {
    // START Jolt-Pointer
    private TempAllocator(MemoryAddress handle) {
        super(handle);
    }

    public static TempAllocator at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TempAllocator(addr);
    }
    // END Jolt-Pointer

    public static TempAllocator of(int size) {
        return new TempAllocator(JPC_TempAllocator_Create(size));
    }

    @Override
    protected void destroyInternal() {
        JPC_TempAllocator_Destroy(handle);
    }
}
