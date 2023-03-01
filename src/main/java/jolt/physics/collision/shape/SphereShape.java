package jolt.physics.collision.shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class SphereShape extends ConvexShape {
    public static SphereShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new SphereShape(address);
    }

    public static SphereShape create(float radius) {
        var address = JPC_SphereShape_Create(radius);
        return new SphereShape(address);
    }

    private SphereShape(MemoryAddress address) {
        super(address);
    }
}
