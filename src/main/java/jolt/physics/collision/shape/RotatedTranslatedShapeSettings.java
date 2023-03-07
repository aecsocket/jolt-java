package jolt.physics.collision.shape;

import jolt.math.FVec3;
import jolt.math.Quat;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class RotatedTranslatedShapeSettings extends DecoratedShapeSettings {
    //region Jolt-Pointer
    private RotatedTranslatedShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static RotatedTranslatedShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new RotatedTranslatedShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static RotatedTranslatedShapeSettings of(ShapeSettings shape, FVec3 position, Quat rotation) {
        return new RotatedTranslatedShapeSettings(JPC_RotatedTranslatedShapeSettings_CreateFromSettings(shape.address(), position.address(), rotation.address()));
    }

    public static RotatedTranslatedShapeSettings of(Shape shape, FVec3 position, Quat rotation) {
        return new RotatedTranslatedShapeSettings(JPC_RotatedTranslatedShapeSettings_CreateFromShape(shape.address(), position.address(), rotation.address()));
    }

    public void getPosition(FVec3 out) {
        JPC_RotatedTranslatedShapeSettings_GetPosition(handle, out.address());
    }

    public void setPosition(FVec3 position) {
        JPC_RotatedTranslatedShapeSettings_SetPosition(handle, position.address());
    }

    public void getRotation(Quat out) {
        JPC_RotatedTranslatedShapeSettings_GetRotation(handle, out.address());
    }

    public void setRotation(Quat rotation) {
        JPC_RotatedTranslatedShapeSettings_SetRotation(handle, rotation.address());
    }
}
