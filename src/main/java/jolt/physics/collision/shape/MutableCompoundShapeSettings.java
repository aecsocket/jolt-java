package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class MutableCompoundShapeSettings extends CompoundShapeSettings {
    //region Jolt-Pointer
    private MutableCompoundShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static MutableCompoundShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MutableCompoundShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static MutableCompoundShapeSettings of() {
        return new MutableCompoundShapeSettings(JPC_MutableCompoundShapeSettings_Create());
    }
}
