package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CylinderShapeSettings extends ConvexShapeSettings {
    public static CylinderShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new CylinderShapeSettings(address);
    }

    public static CylinderShapeSettings create(float halfHeight, float radius, float convexRadius, @Nullable PhysicsMaterial material) {
        var address = JPC_CylinderShapeSettings_Create(halfHeight, radius, convexRadius, Jolt.ptr(material));
        return new CylinderShapeSettings(address);
    }

    public static CylinderShapeSettings create(float halfHeight, float radius, float convexRadius) {
        return create(halfHeight, radius, convexRadius, null);
    }

    public static CylinderShapeSettings create(float halfHeight, float radius) {
        return create(halfHeight, radius, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    private CylinderShapeSettings(MemoryAddress address) {
        super(address);
    }

    public float getHalfHeight() {
        return JPC_CylinderShapeSettings_GetHalfHeight(address);
    }

    public void setHalfHeight(float halfHeight) {
        JPC_CylinderShapeSettings_SetHalfHeight(address, halfHeight);
    }

    public float getRadius() {
        return JPC_CylinderShapeSettings_GetRadius(address);
    }

    public void setRadius(float radius) {
        JPC_CylinderShapeSettings_SetRadius(address, radius);
    }

    public float getConvexRadius() {
        return JPC_CylinderShapeSettings_GetConvexRadius(address);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_CylinderShapeSettings_SetConvexRadius(address, convexRadius);
    }
}
