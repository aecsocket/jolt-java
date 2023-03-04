package jolt.physics.collision.shape;

import jolt.math.FVec3;
import jolt.math.Quat;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class CompoundShapeSettings extends ShapeSettings
        permits StaticCompoundShapeSettings, MutableCompoundShapeSettings {
    //region Jolt-Pointer-Protected
    protected CompoundShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static CompoundShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CompoundShapeSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    public void addShape(FVec3 position, Quat rotation, ShapeSettings settings, int userData) {
        JPC_CompoundShapeSettings_AddShapeSettings(handle,
                position.address(),
                rotation.address(),
                settings.address(),
                userData
        );
    }

    public void addShape(FVec3 position, Quat rotation, Shape settings, int userData) {
        JPC_CompoundShapeSettings_AddShape(handle,
                position.address(),
                rotation.address(),
                settings.address(),
                userData
        );
    }
}
