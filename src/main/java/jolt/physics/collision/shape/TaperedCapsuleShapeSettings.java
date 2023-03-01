package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TaperedCapsuleShapeSettings extends ConvexShapeSettings {
    public static TaperedCapsuleShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new TaperedCapsuleShapeSettings(address);
    }

    public static TaperedCapsuleShapeSettings create(
            float halfHeight,
            float topRadius,
            float bottomRadius,
            @Nullable PhysicsMaterial material
    ) {
        var address = JPC_TaperedCapsuleShapeSettings_Create(halfHeight, topRadius, bottomRadius, Jolt.ptr(material));
        return new TaperedCapsuleShapeSettings(address);
    }

    public static TaperedCapsuleShapeSettings create(float halfHeight, float topRadius, float bottomRadius) {
        return create(halfHeight, topRadius, bottomRadius, null);
    }

    private TaperedCapsuleShapeSettings(MemoryAddress address) {
        super(address);
    }

    public float getHalfHeight() {
        return JPC_TaperedCapsuleShapeSettings_GetHalfHeight(address);
    }

    public void setHalfHeight(float halfHeight) {
        JPC_TaperedCapsuleShapeSettings_SetHalfHeight(address, halfHeight);
    }

    public float getTopRadius() {
        return JPC_TaperedCapsuleShapeSettings_GetTopRadius(address);
    }

    public void setTopRadius(float topRadius) {
        JPC_TaperedCapsuleShapeSettings_SetTopRadius(address, topRadius);
    }

    public float getBottomRadius() {
        return JPC_TaperedCapsuleShapeSettings_GetBottomRadius(address);
    }

    public void setBottomRadius(float bottomRadius) {
        JPC_TaperedCapsuleShapeSettings_SetBottomRadius(address, bottomRadius);
    }
}
