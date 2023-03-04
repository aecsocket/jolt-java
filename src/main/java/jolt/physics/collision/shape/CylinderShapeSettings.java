package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CylinderShapeSettings extends ConvexShapeSettings {
    //region Jolt-Pointer
    private CylinderShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static CylinderShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CylinderShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static CylinderShapeSettings of(float halfHeight, float radius, float convexRadius, @Nullable PhysicsMaterial material) {
        return new CylinderShapeSettings(JPC_CylinderShapeSettings_Create(
                halfHeight,
                radius,
                convexRadius,
                Jolt.ptr(material)
        ));
    }

    public static CylinderShapeSettings of(float halfHeight, float radius, float convexRadius) {
        return of(halfHeight, radius, convexRadius, null);
    }

    public static CylinderShapeSettings of(float halfHeight, float radius) {
        return of(halfHeight, radius, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    public float getHalfHeight() {
        return JPC_CylinderShapeSettings_GetHalfHeight(handle);
    }

    public void setHalfHeight(float halfHeight) {
        JPC_CylinderShapeSettings_SetHalfHeight(handle, halfHeight);
    }

    public float getRadius() {
        return JPC_CylinderShapeSettings_GetRadius(handle);
    }

    public void setRadius(float radius) {
        JPC_CylinderShapeSettings_SetRadius(handle, radius);
    }

    public float getConvexRadius() {
        return JPC_CylinderShapeSettings_GetConvexRadius(handle);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_CylinderShapeSettings_SetConvexRadius(handle, convexRadius);
    }
}
