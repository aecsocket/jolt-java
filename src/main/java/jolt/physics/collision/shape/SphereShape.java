package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class SphereShape extends ConvexShape {
    public static SphereShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new SphereShape(address);
    }

    public static SphereShape create(float radius, @Nullable PhysicsMaterial material) {
        var address = JPC_SphereShape_Create(radius, Jolt.ptr(material));
        return new SphereShape(address);
    }

    public static SphereShape create(float radius) {
        return create(radius, null);
    }

    private SphereShape(MemoryAddress address) {
        super(address);
    }

    public float getRadius() {
        return JPC_SphereShape_GetRadius(address);
    }
}
