package jolt.physics.collision.shape;

import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class OffsetCenterOfMassShape extends DecoratedShape {
    //region Jolt-Pointer
    private OffsetCenterOfMassShape(MemoryAddress handle) {
        super(handle);
    }

    public static OffsetCenterOfMassShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new OffsetCenterOfMassShape(addr);
    }
    //endregion Jolt-Pointer

    public static OffsetCenterOfMassShape of(Shape shape, FVec3 offset) {
        return new OffsetCenterOfMassShape(JPC_OffsetCenterOfMassShape_Create(shape.address(), offset.address()));
    }

    public void getOffset(FVec3 out) {
        JPC_OffsetCenterOfMassShape_GetOffset(handle, out.address());
    }
}
