package jolt.physics.collision.shape;

import jolt.AddressedJoltNative;

import java.lang.foreign.MemoryAddress;

public sealed class Shape extends AddressedJoltNative permits ConvexShape {
    public static Shape at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new Shape(address);
    }

    protected Shape(MemoryAddress address) {
        super(address);
    }
}
