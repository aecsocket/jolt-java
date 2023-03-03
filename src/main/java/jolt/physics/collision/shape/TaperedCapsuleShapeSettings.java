package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TaperedCapsuleShapeSettings extends ConvexShapeSettings {
    // START Jolt-Pointer
    private TaperedCapsuleShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static TaperedCapsuleShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TaperedCapsuleShapeSettings(addr);
    }
    // END Jolt-Pointer

    public static TaperedCapsuleShapeSettings of(
            float halfHeight,
            float topRadius,
            float bottomRadius,
            @Nullable PhysicsMaterial material
    ) {
        return new TaperedCapsuleShapeSettings(JPC_TaperedCapsuleShapeSettings_Create(halfHeight,
                topRadius,
                bottomRadius,
                Jolt.ptr(material))
        );
    }

    public static TaperedCapsuleShapeSettings of(float halfHeight, float topRadius, float bottomRadius) {
        return of(halfHeight, topRadius, bottomRadius, null);
    }

    public float getHalfHeight() {
        return JPC_TaperedCapsuleShapeSettings_GetHalfHeight(handle);
    }

    public void setHalfHeight(float halfHeight) {
        JPC_TaperedCapsuleShapeSettings_SetHalfHeight(handle, halfHeight);
    }

    public float getTopRadius() {
        return JPC_TaperedCapsuleShapeSettings_GetTopRadius(handle);
    }

    public void setTopRadius(float topRadius) {
        JPC_TaperedCapsuleShapeSettings_SetTopRadius(handle, topRadius);
    }

    public float getBottomRadius() {
        return JPC_TaperedCapsuleShapeSettings_GetBottomRadius(handle);
    }

    public void setBottomRadius(float bottomRadius) {
        JPC_TaperedCapsuleShapeSettings_SetBottomRadius(handle, bottomRadius);
    }
}
