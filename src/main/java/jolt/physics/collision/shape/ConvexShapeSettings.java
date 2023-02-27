package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

public sealed class ConvexShapeSettings extends ShapeSettings permits BoxShapeSettings {
    public static ConvexShapeSettings at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ConvexShapeSettings(address);
    }

    protected ConvexShapeSettings(MemoryAddress address) {
        super(address);
    }
}
