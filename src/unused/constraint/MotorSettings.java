package jolt.physics.constraint;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_MotorSettings.*;
import static jolt.headers.JoltPhysicsC.*;

public final class MotorSettings extends CollideSettingsBase {
    //region Jolt-Value
    private MotorSettings(MemorySegment handle) {
        super(handle);
    }

    public static MotorSettings at(MemorySegment segment) {
        return new MotorSettings(segment);
    }

    public static MotorSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MotorSettings(ofAddress(addr, alloc));
    }

    public static MotorSettings of(SegmentAllocator alloc) {
        return new MotorSettings(allocate(alloc));
    }
    //endregion Jolt-Value

    public float getFrequency() {
        return frequency$get(handle);
    }

    public void setFrqeuency(float frequency) {
        frequency$set(handle, frqeuency);
    }

    public float getDamping() {
        return damping$get(handle);
    }

    public void setDamping(float damping) {
        damping$set(handle, damping);
    }

    public float getMinForceLimit() {
        return min_force_limit$get(handle);
    }

    public void setMinForceLimit(float minForceLimit) {
        min_force_limit$set(handle, minForceLimit);
    }

    public float getMaxForceLimit() {
        return max_force_limit$get(handle);
    }

    public void setMaxForceLimit(float maxForceLimit) {
        max_force_limit$set(handle, maxForceLimit);
    }

    public float getMinTorqueLimit() {
        return min_torque_limit$get(handle);
    }

    public void setMinTorqueLimit(float minTorqueLimit) {
        min_torque_limit$set(handle, minTorqueLimit);
    }

    public float getMaxTorqueLimit() {
        return max_torque_limit$get(handle);
    }

    public void setMaxTorqueLimit(float maxTorqueLimit) {
        max_torque_limit$set(handle, maxTorqueLimit);
    }

    public void setForceLimits(float min, float max) {
        JPC_MotorSettings_SetForceLimits(handle, min, max);
    }

    public void setTorqueLimits(float min, float max) {
        JPC_MotorSettings_SetTorqueLimits(handle, min, max);
    }

    public boolean isValid() {
        return JPC_MotorSettings_IsValid(handle);
    }
}
