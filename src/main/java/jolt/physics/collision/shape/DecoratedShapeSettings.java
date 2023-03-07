package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class DecoratedShapeSettings extends ShapeSettings
        permits ScaledShapeSettings, RotatedTranslatedShapeSettings, OffsetCenterOfMassShapeSettings {
    //region Jolt-Pointer-Protected
    protected DecoratedShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static DecoratedShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new DecoratedShapeSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    public @Nullable ShapeSettings getInnerShape() {
        return ShapeSettings.at(JPC_DecoratedShapeSettings_GetInnerShape(handle));
    }

    public void setInnerShape(@Nullable ShapeSettings innerShape) {
        JPC_DecoratedShapeSettings_SetInnerShape(handle, Jolt.ptr(innerShape));
    }

    public @Nullable Shape getInnerShapePtr() {
        return Shape.at(JPC_DecoratedShapeSettings_GetInnerShapePtr(handle));
    }

    public void setInnerShapePtr(@Nullable Shape innerShapePtr) {
        JPC_DecoratedShapeSettings_SetInnerShapePtr(handle, Jolt.ptr(innerShapePtr));
    }
}
