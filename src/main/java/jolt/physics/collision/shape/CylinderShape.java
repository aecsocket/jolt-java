package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CylinderShape extends ConvexShape {
    public static CylinderShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new CylinderShape(address);
    }

    public static CylinderShape create(float halfHeight, float radius, float convexRadius, @Nullable PhysicsMaterial material) {
        var address = JPC_CylinderShape_Create(halfHeight, radius, convexRadius, Jolt.ptr(material));
        return new CylinderShape(address);
    }

    public static CylinderShape create(float halfHeight, float radius, float convexRadius) {
        return create(halfHeight, radius, convexRadius, null);
    }

    private CylinderShape(MemoryAddress address) {
        super(address);
    }

    public float getRadius() {
        return JPC_CylinderShape_GetRadius(address);
    }

    public float getHalfHeight() {
        return JPC_CylinderShape_GetHalfHeight(address);
    }
}
