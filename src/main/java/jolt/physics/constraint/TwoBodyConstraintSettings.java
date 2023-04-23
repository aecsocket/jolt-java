package jolt.physics.constraint;

import jolt.physics.body.MutableBody;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class TwoBodyConstraintSettings extends ConstraintSettings
        permits FixedConstraintSettings, DistanceConstraintSettings, PointConstraintSettings, HingeConstraintSettings, ConeConstraintSettings, SliderConstraintSettings,
        SwingTwistConstraintSettings, SixDOFConstraintSettings /* TODO, PathConstraintSettings, GearConstraintSettings, RackAndPinionConstraintSettings, PulleyConstraintSettings */ {
    //region Jolt-Pointer-Protected
    protected TwoBodyConstraintSettings(MemoryAddress handle) {
        super(handle);
    }

    public static TwoBodyConstraintSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TwoBodyConstraintSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    public TwoBodyConstraint create(MutableBody body1, MutableBody body2) {
        return TwoBodyConstraint.at(JPC_TwoBodyConstraintSettings_CreateConstraint(handle, body1.address(), body2.address()));
    }
}
