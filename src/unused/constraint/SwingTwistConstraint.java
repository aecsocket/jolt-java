package jolt.physics.constraint;

import jolt.DestroyableJoltNative;
import jolt.geometry.AABox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.body.MutableBody;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class SwingTwistConstraint extends TwoBodyConstraint {
    //region Jolt-Pointer
    private SwingTwistConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static SwingTwistConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SwingTwistConstraint(addr);
    }
    //endregion Jolt-Pointer

    public void getLocalSpacePosition1(FVec3 out) {
        JPC_SwingTwistConstraint_GetLocalSpacePosition1(handle, out.address());
    }

    public void getLocalSpacePosition2(FVec3 out) {
        JPC_SwingTwistConstraint_GetLocalSpacePosition2(handle, out.address());
    }

    public void getConstraintToBody1(Quat out) {
        JPC_SwingTwistConstraint_GetConstraintToBody1(handle, out.address());
    }

    public void getConstraintToBody2(Quat out) {
        JPC_SwingTwistConstraint_GetConstraintToBody2(handle, out.address());
    }

    public float getNormalHalfConeAngle() {
        return JPC_SwingTwistConstraint_GetNormalHalfConeAngle(handle);
    }

    public void setNormalHalfConeAngle(float angle) {
        JPC_SwingTwistConstraint_SetNormalHalfConeAngle(handle, angle);
    }

    public float getPlaneHalfConeAngle() {
        return JPC_SwingTwistConstraint_GetPlaneHalfConeAngle(handle);
    }

    public void setPlaneHalfConeAngle(float angle) {
        JPC_SwingTwistConstraint_SetPlaneHalfConeAngle(handle, angle);
    }

    public float getTwistMinAngle() {
        return JPC_SwingTwistConstraint_GetTwistMinAngle(handle);
    }

    public void setTwistMinAngle(float twistMinAngle) {
        JPC_SwingTwistConstraint_SetTwistMinAngle(handle, twistMinAngle);
    }

    public float getTwistMaxAngle() {
        return JPC_SwingTwistConstraint_GetTwistMaxAngle(handle);
    }

    public void setTwistMaxAngle(float twistMaxAngle) {
        JPC_SwingTwistConstraint_SetTwistMaxAngle(handle, twistMaxAngle);
    }

    public MotorSettings getSwingMotorSettings() {
        return MotorSettings.at(JPC_SwingTwistConstraint_GetSwingMotorSettings(handle));
    }

    public MotorSettings getTwistMotorSettings() {
        return MotorSettings.at(JPC_SwingTwistConstraint_GetTwistMotorSettings(handle));
    }

    public void setSwingMotorState(MotorState state) {
        JPC_SwingTwistConstraint_SetSwingMotorState(handle, state.ordinal());
    }

    public MotorState getSwingMotorState() {
        return MotorState.values()[JPC_SwingTwistConstraint_GetSwingMotorState(handle)];
    }

    public void setTwistMotorState(MotorState state) {
        JPC_SwingTwistConstraint_SetTwistMotorState(handle, state.ordinal());
    }

    public MotorState getTwistMotorState() {
        return MotorState.values()[JPC_SwingTwistConstraint_GetTwistMotorState(handle)];
    }

    public void setTargetAngularVelocityCS(FVec3 angularVelocity) {
        JPC_SwingTwistConstraint_SetTargetAngularVelocityCS(handle, angularVelocity.address());
    }

    public void getTargetAngularVelocityCS(FVec3 out) {
        JPC_SwingTwistConstraint_GetTargetAngularVelocityCS(handle, out.address());
    }

    public void setTargetOrientationCS(Quat orientation) {
        JPC_SwingTwistConstraint_SetTargetOrientationCS(handle, orientation.address());
    }

    public void getTargetOrientationCS(Quat out) {
        JPC_SwingTwistConstraint_GetTargetOrientationCS(handle, out.address());
    }

    public void setTargetOrientationBS(Quat orientation) {
        JPC_SwingTwistConstraint_SetTargetOrientationBS(handle, orientation.address());
    }

    public void getRotationInConstraintSpace(Quat out) {
        JPC_SwingTwistConstraint_GetRotationInConstraintSpace(handle, out.address());
    }

    public void getTotalLambdaPosition(FVec3 out) {
        JPC_SwingTwistConstraint_GetTotalLambdaPosition(handle, out.address());
    }

    public float getTotalLambdaTwist() {
        return JPC_SwingTwistConstraint_GetTotalLambdaTwist(handle);
    }

    public float getTotalLambdaSwingY() {
        return JPC_SwingTwistConstraint_GetTotalLambdaSwingY(handle);
    }

    public float getTotalLambdaSwingZ() {
        return JPC_SwingTwistConstraint_GetTotalLambdaSwingZ(handle);
    }

    public void getTotalLambdaMotor(FVec3 out) {
        JPC_SwingTwistConstraint_GetTotalLambdaMotor(handle, out.address());
    }
}
