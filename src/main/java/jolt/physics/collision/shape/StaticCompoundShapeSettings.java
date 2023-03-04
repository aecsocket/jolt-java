package jolt.physics.collision.shape;

import jolt.core.TempAllocator;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class StaticCompoundShapeSettings extends CompoundShapeSettings {
    //region Jolt-Pointer
    private StaticCompoundShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static StaticCompoundShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new StaticCompoundShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static StaticCompoundShapeSettings of() {
        return new StaticCompoundShapeSettings(JPC_StaticCompoundShapeSettings_Create());
    }

    public Shape create(TempAllocator tempAllocator) {
        return Shape.at(JPC_StaticCompoundShapeSettings_CreateShape(handle, tempAllocator.address()));
    }
}
