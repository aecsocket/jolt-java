package jolt.physics.constraint;

import jolt.DestroyableJoltNative;
import jolt.geometry.AABox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ConstraintSettings extends DestroyableJoltNative
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
    protected void destroyInternal() {
        JPC_ConstraintSettings_Release(handle);
    }

    public boolean getEnabled() {
        return JPC_ConstraintSettings_GetEnabled(handle);
    }

    public void setEnabled(boolean enabled) {
        JPC_ConstraintSettings_SetEnabled(handle, enabled);
    }

    public int getNumVelocityStepsOverride() {
        return JPC_ConstraintSettings_GetNumVelocityStepsOverride(handle);
    }

    public void setNumVelocityStepsOverride(int numVelocityStepsOverride) {
        JPC_ConstraintSettings_SetNumVelocityStepsOverride(handle, numVelocityStepsOverride);
    }

    public int getNumPositionStepsOverride() {
        return JPC_ConstraintSettings_GetNumPositionStepsOverride(handle);
    }

    public void setNumPositionsStepsOverride(int numPositionStepsOverride) {
        JPC_ConstraintSettings_SetNumPositionStepsOverride(handle, numPositionStepsOverride);
    }
}
