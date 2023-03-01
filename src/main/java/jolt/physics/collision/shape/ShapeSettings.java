package jolt.physics.collision.shape;

import jolt.AddressedJoltNative;
import jolt.DestroyableJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ShapeSettings extends DestroyableJoltNative permits ConvexShapeSettings {
    public static ShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ShapeSettings(address);
    }

    protected ShapeSettings(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void destroyInternal() {
        JPC_ShapeSettings_Release(address);
    }

    public long getUserData() {
        return JPC_ShapeSettings_GetUserData(address);
    }

    public void setUserData(long userData) {
        JPC_ShapeSettings_SetUserData(address, userData);
    }

    public Shape create() {
        var result = JPC_ShapeSettings_CreateShape(address);
        if (result == null)
            throw new RuntimeException("Could not create shape");
        return Shape.at(result);
    }
}
