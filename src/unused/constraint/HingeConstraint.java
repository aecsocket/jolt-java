package jolt.physics.constraint;

import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class HingeConstraint extends TwoBodyConstraint {
    //region Jolt-Pointer
    private HingeConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static HingeConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new HingeConstraint(addr);
    }
    //endregion Jolt-Pointer

    public float getCurrentAngle() {
        return JPC_HingeConstraint_GetCurrentAngle(handle);
    }

    public void setMaxFrictionTorque(float frictionTorque) {
        JPC_HingeConstraint_SetMaxFrictionTorque(handle, frictionTorque);
    }

    public float getMaxFrictionTorque() {
        return JPC_HingeConstraint_GetMaxFrictionTorque(handle);
    }

    public MotorSettings getMotorSettings() {
        return MotorSettings.at(JPC_HingeConstraint_GetMotorSettings(handle));
    }

    public void setMotorState(MotorState state) {
        JPC_HingeConstraint_SetMotorState(handle, state.ordinal());
    }

    public MotorState getMotorState() {
        return MotorState.values()[JPC_HingeConstraint_GetMotorState(handle)];
    }

    public void setTargetAngularVelocity(float angularVelocity) {
        JPC_HingeConstraint_SetTargetAngularVelocity(handle, angularVelocity);
    }

    public float getTargetAngularVelocity() {
        return JPC_HingeConstraint_GetTargetAngularVelocity(handle);
    }

    public void setTargetAngle(float angle) {
        JPC_HingeConstraint_SetTargetAngle(handle, angle);
    }

    public float getTargetAngle() {
        return JPC_HingeConstraint_GetTargetAngle(handle);
    }

    public void setLimits(float limitsMin, float limitsMax) {
        JPC_HingeConstraint_SetLimits(handle, limitsMin, limitsMax);
    }

    public float getLimitsMin() {
        return JPC_HingeConstraint_GetLimitsMin(handle);
    }

    public float getLimitsMax() {
        return JPC_HingeConstraint_GetLimitsMax(handle);
    }

    public boolean hasLimits() {
        return JPC_HingeConstraint_HasLimits(handle);
    }

    public void getTotalLambdaPosition(FVec3 out) {
        JPC_HingeConstraint_GetTotalLambdaPosition(handle, out.address());
    }

    // float[2]
    public float[] getTotalLambdaRotation() {
        // TODO
        JPC_HingeConstraint_GetTotalLambdaRotation(handle);
    }

    public float getTotalLambdaRotationLimits() {
        return JPC_HingeConstraint_GetTotalLambdaRotationLimits(handle);
    }

    public float getTotalLambdaMotor() {
        return JPC_HingeConstraint_GetTotalLambdaMotor(handle);
    }
}
