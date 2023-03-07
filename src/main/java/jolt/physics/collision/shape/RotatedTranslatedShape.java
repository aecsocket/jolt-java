package jolt.physics.collision.shape;

import jolt.math.FVec3;
import jolt.math.Quat;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class RotatedTranslatedShape extends DecoratedShape {
    //region Jolt-Pointer
    private RotatedTranslatedShape(MemoryAddress handle) {
        super(handle);
    }

    public static RotatedTranslatedShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new RotatedTranslatedShape(addr);
    }
    //endregion Jolt-Pointer

    public static RotatedTranslatedShape of(Shape shape, FVec3 position, Quat rotation) {
        return new RotatedTranslatedShape(JPC_RotatedTranslatedShape_Create(shape.address(), position.address(), rotation.address()));
    }

    public void getPosition(FVec3 out) {
        JPC_RotatedTranslatedShape_GetPosition(handle, out.address());
    }

    public void getRotation(Quat out) {
        JPC_RotatedTranslatedShape_GetRotation(handle, out.address());
    }
}
