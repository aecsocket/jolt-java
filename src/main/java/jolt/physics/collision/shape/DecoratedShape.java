package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class DecoratedShape extends Shape
        permits ScaledShape, RotatedTranslatedShape, OffsetCenterOfMassShape {
    //region Jolt-Pointer-Protected
    protected DecoratedShape(MemoryAddress handle) {
        super(handle);
    }

    public static DecoratedShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new DecoratedShape(addr);
    }
    //endregion Jolt-Pointer-Protected

    public Shape getInnerShape() {
        return Shape.at(JPC_DecoratedShape_GetInnerShape(handle));
    }
}
