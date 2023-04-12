package jolt.physics.constraint;

import jolt.DeletableJoltNative;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ConstraintSettings extends DeletableJoltNative
        permits TwoBodyConstraintSettings {
    //region Jolt-Pointer-Protected
    protected ConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ConstraintSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    @Override
    protected void deleteInternal() {
        JPC_ConstraintSettings_Release(handle);
    }
}
