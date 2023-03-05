package jolt.core;

import jolt.AddressedJoltNative;

import java.lang.foreign.MemoryAddress;

public final class SharedMutex extends AddressedJoltNative {
    //region Jolt-Pointer
    private SharedMutex(MemoryAddress handle) {
        super(handle);
    }

    public static SharedMutex at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SharedMutex(addr);
    }
    //endregion Jolt-Pointer
}
