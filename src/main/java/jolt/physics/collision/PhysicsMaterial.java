package jolt.physics.collision;

import jolt.AddressedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

public final class PhysicsMaterial extends AddressedJoltNative {
    public static PhysicsMaterial at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new PhysicsMaterial(address);
    }

    private PhysicsMaterial(MemoryAddress address) {
        super(address);
    }
}
