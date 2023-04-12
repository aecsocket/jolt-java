package jolt.physics.constraint;

import jolt.DeletableJoltNative;

import java.lang.foreign.MemoryAddress;

public sealed class TwoBodyConstraintSettings extends ConstraintSettings
        permits FixedConstraintSettings {
    //region Jolt-Pointer-Protected
    protected TwoBodyConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static TwoBodyConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TwoBodyConstraintSettings(addr);
    }
    //endregion Jolt-Pointer-Protected
}
