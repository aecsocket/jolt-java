package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

public sealed class ConvexShape extends Shape permits SphereShape {
    public static ConvexShape at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ConvexShape(address);
    }

    protected ConvexShape(MemoryAddress address) {
        super(address);
    }
}
