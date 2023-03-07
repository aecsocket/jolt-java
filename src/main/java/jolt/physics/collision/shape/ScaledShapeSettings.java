package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class ScaledShapeSettings extends DecoratedShapeSettings {
    //region Jolt-Pointer
    private ScaledShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ScaledShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ScaledShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static ScaledShapeSettings of(ShapeSettings shape, FVec3 scale) {
        return new ScaledShapeSettings(JPC_ScaledShapeSettings_CreateFromSettings(shape.address(), scale.address()));
    }

    public static ScaledShapeSettings of(Shape shape, FVec3 scale) {
        return new ScaledShapeSettings(JPC_ScaledShapeSettings_CreateFromShape(shape.address(), scale.address()));
    }

    public void getScale(FVec3 out) {
        JPC_ScaledShapeSettings_GetScale(handle, out.address());
    }

    public void setScale(FVec3 scale) {
        JPC_ScaledShapeSettings_SetScale(handle, scale.address());
    }
}
