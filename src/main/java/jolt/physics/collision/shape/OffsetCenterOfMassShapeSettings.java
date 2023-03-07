package jolt.physics.collision.shape;

import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class OffsetCenterOfMassShapeSettings extends DecoratedShapeSettings {
    //region Jolt-Pointer
    private OffsetCenterOfMassShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static OffsetCenterOfMassShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new OffsetCenterOfMassShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static OffsetCenterOfMassShapeSettings of(ShapeSettings shape, FVec3 offset) {
        return new OffsetCenterOfMassShapeSettings(JPC_OffsetCenterOfMassShapeSettings_CreateFromSettings(shape.address(), offset.address()));
    }

    public static OffsetCenterOfMassShapeSettings of(Shape shape, FVec3 offset) {
        return new OffsetCenterOfMassShapeSettings(JPC_OffsetCenterOfMassShapeSettings_CreateFromShape(shape.address(), offset.address()));
    }

    public void getOffset(FVec3 out) {
        JPC_OffsetCenterOfMassShapeSettings_GetOffset(handle, out.address());
    }

    public void setOffset(FVec3 offset) {
        JPC_OffsetCenterOfMassShapeSettings_SetOffset(handle, offset.address());
    }
}
