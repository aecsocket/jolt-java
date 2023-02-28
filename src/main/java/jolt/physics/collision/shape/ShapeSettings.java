package jolt.physics.collision.shape;

import jolt.AddressedJoltNative;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ShapeSettings extends AddressedJoltNative permits ConvexShapeSettings {
    public static ShapeSettings at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ShapeSettings(address);
    }

    protected ShapeSettings(MemoryAddress address) {
        super(address);
    }

    public Shape create() {
        var result = JPC_ShapeSettings_CreateShape(address);
        if (result == null)
            throw new RuntimeException("Could not create shape");
        return Shape.at(result);
    }
}
