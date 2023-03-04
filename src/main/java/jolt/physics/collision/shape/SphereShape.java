package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class SphereShape extends ConvexShape {
    //region Jolt-Pointer
    private SphereShape(MemoryAddress handle) {
        super(handle);
    }

    public static SphereShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SphereShape(addr);
    }
    //endregion Jolt-Pointer

    public static SphereShape of(float radius, @Nullable PhysicsMaterial material) {
        return new SphereShape(JPC_SphereShape_Create(radius, Jolt.ptr(material)));
    }

    public static SphereShape of(float radius) {
        return of(radius, null);
    }

    public float getRadius() {
        return JPC_SphereShape_GetRadius(handle);
    }
}
