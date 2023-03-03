package jolt.physics.collision;

import jolt.AddressedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

public final class PhysicsMaterial extends AddressedJoltNative {
    // START Jolt-Pointer
    private PhysicsMaterial(MemoryAddress handle) {
        super(handle);
    }

    public static PhysicsMaterial at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new PhysicsMaterial(addr);
    }
    // END Jolt-Pointer
}
