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

public final class SixDOFConstraint extends TwoBodyConstraint {
    //region Jolt-Pointer
    private SixDOFConstraint(MemoryAddress handle) {
        super(handle);
    }

    public static SixDOFConstraint at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SixDOFConstraint(addr);
    }
    //endregion Jolt-Pointer

    public void setTranslationLimits(FVec3 limitMin, FVec3 limitMax) {
        JPC_SixDOFConstraint_SetTranslationLimits(handle, limitMin.address(), limitMax.address());
    }

    public void setRotationLimits(FVec3 limitMin, FVec3 limitMax) {
        JPC_SixDOFConstraint_SetRotationLimits(handle, limitMin.address(), limitMax.address());
    }

    public float getLimitsMin(Axis axis) {
        return JPC_SixDOFConstraint_GetLimitsMin(handle, axis.ordinal());
    }

    public float getLimitsMax(Axis axis) {
        return JPC_SixDOFConstraint_GetLimitsMax(handle, axis.ordinal());
    }

    public boolean isFixedAxis(Axis axis) {
        return JPC_SixDOFConstraint_IsFixedAxis(handle, axis.ordinal());
    }

    public boolean isFreeAxis(Axis axis) {
        return JPC_SixDOFConstraint_IsFreeAxis(handle, axis.ordinal());
    }

    public void setMaxFriction(Axis axis, float friction) {
        JPC_SixDOFConstraint_SetMaxFriction(handle, axis.ordinal(), friction);
    }

    public float getMaxFriction(Axis axis) {
        return JPC_SixDOFConstraint_GetMaxFriction(handle, axis.ordinal());
    }

    public void getRotationInConstraintSpace(Quat out) {
        JPC_SixDOFConstraint_GetRotationInConstraintSpace(handle, out.address());
    }

    public MotorSettings getMotorSettings(Axis axis) {
        return MotorSettings.at(JPC_SixDOFConstraint_GetMotorSettings(handle, axis.ordinal()));
    }

    public void setMotorState(Axis axis, MotorState state) {
        JPC_SixDOFConstraint_SetMotorState(handle, axis.ordinal(), state.ordinal());
    }

    public MotorState getMotorState(Axis axis) {
        return MotorState.values()[JPC_SixDOFConstraint_GetMotorState(handle, axis.ordinal())];
    }

    public void getTargetVelocityCS(FVec3 out) {
        JPC_SixDOFConstraint_GetTargetVelocityCS(handle, out.address());
    }

    public void setTargetVelocityCS(FVec3 velocity) {
        JPC_SixDOFConstraint_SetTargetVelocityCS(handle, velocity.address());
    }

    public void setTargetAngularVelocityCS(FVec3 angularVelocity) {
        JPC_SixDOFConstraint_SetTargetAngularVelocityCS(handle, angularVelocity.address());
    }

    public void getTargetAngularVelocityCS(FVec3 out) {
        JPC_SixDOFConstraint_GetTargetAngularVelocityCS(handle, out.address());
    }

    public void getTargetPositionCS(FVec3 out) {
        JPC_SixDOFConstraint_GetTargetPositionCS(handle, out.address());
    }

    public void setTargetPositionCS(FVec3 position) {
        JPC_SixDOFConstraint_SetTargetPositionCS(handle, position.address());
    }

    public void setTargetOrientationBS(Quat orientation) {
        JPC_SixDOFConstraint_SetTargetOrientationBS(handle, orientation.address());
    }

    public void getTotalLambdaPosition(FVec3 out) {
        JPC_SixDOFConstraint_GetTotalLambdaPosition(handle, out.address());
    }

    public void getTotalLambdaRotation(FVec3 out) {
        JPC_SixDOFConstraint_GetTotalLambdaRotation(handle, out.address());
    }

    public void getTotalLambdaMotorTranslation(FVec3 out) {
        JPC_SixDOFConstraint_GetTotalLambdaMotorTranslation(handle, out.address());
    }

    public void getTotalLambdaMotorRotation(FVec3 out) {
        JPC_SixDOFConstraint_GetTotalLambdaMotorRotation(handle, out.address());
    }
}
