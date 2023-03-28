package jolt.physics.constraint;

import jolt.math.FMat44;
import jolt.physics.body.MutableBody;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class TwoBodyConstraint extends Constraint
        permits FixedConstraint, DistanceConstraint, PointConstraint, HingeConstraint, ConeConstraint, SliderConstraint,
        SwingTwistConstraint, SixDOFConstraint /* TODO, PathConstraint, GearConstraint, RackAndPinionConstraint, PulleyConstraint*/ {
    //region Jolt-Pointer-Protected
    protected TwoBodyConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static TwoBodyConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TwoBodyConstraint(addr);
    }
    //endregion Jolt-Pointer-Protected

    public MutableBody getBody1() {
        return MutableBody.at(JPC_TwoBodyConstraint_GetBody1(handle));
    }

    public MutableBody getBody2() {
        return MutableBody.at(JPC_TwoBodyConstraint_GetBody2(handle));
    }

    public void getConstraintToBody1Matrix(FMat44 out) {
        JPC_TwoBodyConstraint_GetConstraintToBody1Matrix(handle, out.address());
    }

    public void getConstraintToBody2Matrix(FMat44 out) {
        JPC_TwoBodyConstraint_GetConstraintToBody2Matrix(handle, out.address());
    }
}
