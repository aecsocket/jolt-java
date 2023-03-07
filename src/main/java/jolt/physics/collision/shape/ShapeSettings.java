package jolt.physics.collision.shape;

import jolt.DestroyableJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ShapeSettings extends DestroyableJoltNative
        permits ConvexShapeSettings, CompoundShapeSettings, DecoratedShapeSettings, MeshShapeSettings, HeightFieldShapeSettings {
    //region Jolt-Pointer-Protected
    protected ShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ShapeSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    @Override
    protected void destroyInternal() {
        JPC_ShapeSettings_Release(handle);
    }

    public long getUserData() {
        return JPC_ShapeSettings_GetUserData(handle);
    }

    public void setUserData(long userData) {
        JPC_ShapeSettings_SetUserData(handle, userData);
    }

    public ShapeResult create(SegmentAllocator alloc) {
        return ShapeResult.at(JPC_ShapeSettings_CreateShape(alloc, handle));
    }
}
