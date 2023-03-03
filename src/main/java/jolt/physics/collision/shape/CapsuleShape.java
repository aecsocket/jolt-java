package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CapsuleShape extends ConvexShape {
    // START Jolt-Pointer
    private CapsuleShape(MemoryAddress handle) {
        super(handle);
    }

    public static CapsuleShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CapsuleShape(addr);
    }
    // END Jolt-Pointer

    public static CapsuleShape of(float halfHeight, float radius, @Nullable PhysicsMaterial material) {
        return new CapsuleShape(JPC_CapsuleShape_Create(halfHeight, radius, Jolt.ptr(material)));
    }

    public static CapsuleShape of(float halfHeight, float radius) {
        return of(halfHeight, radius, null);
    }

    public float getRadius() {
        return JPC_CapsuleShape_GetRadius(handle);
    }

    public float getHalfHeight() {
        return JPC_CapsuleShape_GetHalfHeightOfCylinder(handle);
    }
}
