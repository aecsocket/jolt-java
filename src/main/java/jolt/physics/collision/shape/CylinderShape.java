package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CylinderShape extends ConvexShape {
    // START Jolt-Pointer
    private CylinderShape(MemoryAddress handle) {
        super(handle);
    }

    public static CylinderShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CylinderShape(addr);
    }
    // END Jolt-Pointer

    public static CylinderShape of(float halfHeight, float radius, float convexRadius, @Nullable PhysicsMaterial material) {
        return new CylinderShape(JPC_CylinderShape_Create(halfHeight, radius, convexRadius, Jolt.ptr(material)));
    }

    public static CylinderShape of(float halfHeight, float radius, float convexRadius) {
        return of(halfHeight, radius, convexRadius, null);
    }

    public float getRadius() {
        return JPC_CylinderShape_GetRadius(handle);
    }

    public float getHalfHeight() {
        return JPC_CylinderShape_GetHalfHeight(handle);
    }
}
