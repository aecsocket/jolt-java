package jolt.physics.collision.shape;

import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class ScaledShape extends DecoratedShape {
    //region Jolt-Pointer
    private ScaledShape(MemoryAddress handle) {
        super(handle);
    }

    public static ScaledShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ScaledShape(addr);
    }
    //endregion Jolt-Pointer

    public static ScaledShape of(Shape shape, FVec3 scale) {
        return new ScaledShape(JPC_ScaledShape_Create(shape.address(), scale.address()));
    }

    public void getScale(FVec3 out) {
        JPC_ScaledShape_GetScale(handle, out.address());
    }
}
