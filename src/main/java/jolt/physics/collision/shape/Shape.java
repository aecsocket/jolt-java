package jolt.physics.collision.shape;

import jolt.AddressedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

public sealed class Shape extends AddressedJoltNative permits ConvexShape {
    public static final float DEFAULT_CONVEX_RADIUS = 0.05f;

    public static Shape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new Shape(address);
    }

    protected Shape(MemoryAddress address) {
        super(address);
    }
}
