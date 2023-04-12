package jolt.physics.constraint;

import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class SliderConstraint extends TwoBodyConstraint {
    //region Jolt-Pointer
    private SliderConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static SliderConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SliderConstraint(addr);
    }
    //endregion Jolt-Pointer

    public float getCurrentPosition() {
        return JPC_SliderConstraint_GetCurrentPosition(handle);
    }

    public void setMaxFrictionForce(float frictionForce) {
        JPC_SliderConstraint_SetMaxFrictionForce(handle, frictionForce);
    }

    public float getMaxFrictionForce() {
        return JPC_SliderConstraint_GetMaxFrictionForce(handle);
    }

    public MotorSettings getMotorSettings(MemorySession alloc) {
        return MotorSettings.at(alloc, JPC_SliderConstraint_GetMotorSettings(handle));
    }

    public void setMotorState(MotorState state) {
        JPC_SliderConstraint_SetMotorState(handle, (byte) state.ordinal());
    }

    public MotorState getMotorState() {
        return MotorState.values()[JPC_SliderConstraint_GetMotorState(handle)];
    }

    public void setTargetVelocity(float velocity) {
        JPC_SliderConstraint_SetTargetVelocity(handle, velocity);
    }

    public float getTargetVelocity() {
        return JPC_SliderConstraint_GetTargetVelocity(handle);
    }

    public void setTargetPosition(float position) {
        JPC_SliderConstraint_SetTargetPosition(handle, position);
    }

    public float getTargetPosition() {
        return JPC_SliderConstraint_GetTargetPosition(handle);
    }

    public void setLimits(float limitsMin, float limitsMax) {
        JPC_SliderConstraint_SetLimits(handle, limitsMin, limitsMax);
    }

    public float getLimitsMin() {
        return JPC_SliderConstraint_GetLimitsMin(handle);
    }

    public float getLimitsMax() {
        return JPC_SliderConstraint_GetLimitsMax(handle);
    }

    public boolean hasLimits() {
        return JPC_SliderConstraint_HasLimits(handle);
    }

    public void setFrequency(float frequency) {
        JPC_SliderConstraint_SetFrequency(handle, frequency);
    }

    public float getFrequency() {
        return JPC_SliderConstraint_GetFrequency(handle);
    }

    public void setDamping(float damping) {
        JPC_SliderConstraint_SetDamping(handle, damping);
    }

    public float getDamping() {
        return JPC_SliderConstraint_GetDamping(handle);
    }

    // float[2]
    public float[] getTotalLambdaPosition() {
        try (var arena = MemorySession.openConfined()) {
            var x = arena.allocate(C_FLOAT, 0);
            var y = arena.allocate(C_FLOAT, 0);
            JPC_SliderConstraint_GetTotalLambdaPosition(handle, x, y);
            return new float[]{ x.get(C_FLOAT, 0), y.get(C_FLOAT, 0) };
        }
    }

    public float getTotalLambdaPositionLimits() {
        return JPC_SliderConstraint_GetTotalLambdaPositionLimits(handle);
    }

    public void getTotalLambdaRotation(FVec3 out) {
        JPC_SliderConstraint_GetTotalLambdaRotation(handle, out.address());
    }

    public float getTotalLambdaMotor() {
        return JPC_SliderConstraint_GetTotalLambdaMotor(handle);
    }
}
