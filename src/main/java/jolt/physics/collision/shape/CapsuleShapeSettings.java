package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CapsuleShapeSettings extends ConvexShapeSettings {
    public static CapsuleShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new CapsuleShapeSettings(address);
    }

    public static CapsuleShapeSettings create(float halfHeight, float radius, @Nullable PhysicsMaterial material) {
        var address = JPC_CapsuleShapeSettings_Create(halfHeight, radius, Jolt.ptr(material));
        return new CapsuleShapeSettings(address);
    }

    public static CapsuleShapeSettings create(float halfHeight, float radius) {
        return create(halfHeight, radius, null);
    }

    private CapsuleShapeSettings(MemoryAddress address) {
        super(address);
    }

    public float getHalfHeight() {
        return JPC_CapsuleShapeSettings_GetHalfHeight(address);
    }

    public void setHalfHeight(float halfHeight) {
        JPC_CapsuleShapeSettings_SetHalfHeight(address, halfHeight);
    }

    public float getRadius() {
        return JPC_CapsuleShapeSettings_GetRadius(address);
    }

    public void setRadius(float radius) {
        JPC_CapsuleShapeSettings_SetRadius(address, radius);
    }
}
