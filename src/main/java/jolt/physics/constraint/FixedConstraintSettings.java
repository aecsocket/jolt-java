package jolt.physics.constraint;

import jolt.headers.JPC_FixedConstraintSettings;
import jolt.physics.body.MutableBody;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class FixedConstraintSettings extends TwoBodyConstraintSettings {
    //region Jolt-Pointer
    private FixedConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static FixedConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new FixedConstraintSettings(addr);
    }
    //endregion Jolt-Pointer

    public static FixedConstraintSettings of() {
        return new FixedConstraintSettings(JPC_FixedConstraintSettings_Create());
    }

    public ConstraintSpace getSpace() {
        return ConstraintSpace.values()[JPC_FixedConstraintSettings_GetSpace(handle)];
    }

    public void setSpace(ConstraintSpace space) {
        JPC_FixedConstraintSettings_SetSpace(handle, (byte) space.ordinal());
    }

    public boolean getAutoDetectPoint() {
        return JPC_FixedConstraintSettings_GetAutoDetectPoint(handle);
    }

    public void setAutoDetectPoint(boolean autoDetectPoint) {
        JPC_FixedConstraintSettings_SetAutoDetectPoint(handle, autoDetectPoint);
    }

    public TwoBodyConstraint create(MutableBody body1, MutableBody body2) {
        return TwoBodyConstraint.at(JPC_FixedConstraintSettings_CreateConstraint(handle, body1.address(), body2.address()));
    }
}
