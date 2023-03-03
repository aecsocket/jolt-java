package jolt.physics.collision.shape;

import jolt.DestroyableJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ShapeSettings extends DestroyableJoltNative
        permits ConvexShapeSettings {
    // START Jolt-Pointer-Protected
    protected ShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ShapeSettings(addr);
    }
    // END Jolt-Pointer-Protected

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

    public Shape create() {
        var result = JPC_ShapeSettings_CreateShape(handle);
        if (result == null)
            throw new RuntimeException("Could not create shape");
        return Shape.at(result);
    }
}
