package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CapsuleShapeSettings extends ConvexShapeSettings {
    //region Jolt-Pointer
    private CapsuleShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static CapsuleShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CapsuleShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static CapsuleShapeSettings of(float halfHeight, float radius, @Nullable PhysicsMaterial material) {
        return new CapsuleShapeSettings(JPC_CapsuleShapeSettings_Create(halfHeight, radius, Jolt.ptr(material)));
    }

    public static CapsuleShapeSettings of(float halfHeight, float radius) {
        return of(halfHeight, radius, null);
    }

    public float getHalfHeight() {
        return JPC_CapsuleShapeSettings_GetHalfHeight(handle);
    }

    public void setHalfHeight(float halfHeight) {
        JPC_CapsuleShapeSettings_SetHalfHeight(handle, halfHeight);
    }

    public float getRadius() {
        return JPC_CapsuleShapeSettings_GetRadius(handle);
    }

    public void setRadius(float radius) {
        JPC_CapsuleShapeSettings_SetRadius(handle, radius);
    }
}
