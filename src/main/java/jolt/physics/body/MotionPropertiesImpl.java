package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.math.*;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;
import static jolt.headers_f.JoltPhysicsC.C_FLOAT;

final class MotionPropertiesImpl extends AddressedJoltNative implements MutableMotionProperties {
    MotionPropertiesImpl(MemoryAddress handle) {
        super(handle);
    }

    @Override
    public MotionQuality getMotionQuality() {
        return MotionQuality.values()[JPC_MotionProperties_GetMotionQuality(handle)];
    }

    @Override
    public void getLinearVelocity(FVec3 out) {
        JPC_MotionProperties_GetLinearVelocity(handle, out.address());
    }

    @Override
    public void setLinearVelocity(FVec3 linearVelocity) {
        JPC_MotionProperties_SetLinearVelocity(handle, linearVelocity.address());
    }

    @Override
    public void setLinearVelocityClamped(FVec3 linearVelocity) {
        JPC_MotionProperties_SetLinearVelocityClamped(handle, linearVelocity.address());
    }

    @Override
    public void getAngularVelocity(FVec3 out) {
        JPC_MotionProperties_GetAngularVelocity(handle, out.address());
    }

    @Override
    public void setAngularVelocity(FVec3 angularVelocity) {
        JPC_MotionProperties_SetAngularVelocity(handle, angularVelocity.address());
    }

    @Override
    public void setAngularVelocityClamped(FVec3 angularVelocity) {
        JPC_MotionProperties_SetAngularVelocityClamped(handle, angularVelocity.address());
    }

    @Override
    public void moveKinematic(FVec3 deltaPosition, Quat deltaRotation, float deltaTime) {
        JPC_MotionProperties_MoveKinematic(handle, deltaPosition.address(), deltaRotation.address(), deltaTime);
    }

    @Override
    public float getMaxLinearVelocity() {
        return JPC_MotionProperties_GetMaxLinearVelocity(handle);
    }

    @Override
    public void setMaxLinearVelocity(float linearVelocity) {
        JPC_MotionProperties_SetMaxLinearVelocity(handle, linearVelocity);
    }

    @Override
    public float getMaxAngularVelocity() {
        return JPC_MotionProperties_GetMaxAngularVelocity(handle);
    }

    @Override
    public void setMaxAngularVelocity(float angularVelocity) {
        JPC_MotionProperties_SetMaxAngularVelocity(handle, angularVelocity);
    }

    @Override
    public void clampLinearVelocity() {
        JPC_MotionProperties_ClampLinearVelocity(handle);
    }

    @Override
    public void clampAngularVelocity() {
        JPC_MotionProperties_ClampAngularVelocity(handle);
    }

    @Override
    public float getLinearDamping() {
        return JPC_MotionProperties_GetLinearDamping(handle);
    }

    @Override
    public void setLinearDamping(float linearDamping) {
        JPC_MotionProperties_SetLinearDamping(handle, linearDamping);
    }

    @Override
    public float getAngularDamping() {
        return JPC_MotionProperties_GetAngularDamping(handle);
    }

    @Override
    public void setAngularDamping(float angularDamping) {
        JPC_MotionProperties_SetAngularDamping(handle, angularDamping);
    }

    @Override
    public float getGravityFactor() {
        return JPC_MotionProperties_GetGravityFactor(handle);
    }

    @Override
    public void setGravityFactor(float gravityFactor) {
        JPC_MotionProperties_SetGravityFactor(handle, gravityFactor);
    }

    @Override
    public float getInverseMass() {
        return JPC_MotionProperties_GetInverseMass(handle);
    }

    @Override
    public void setInverseMass(float inverseMass) {
        JPC_MotionProperties_SetInverseMass(handle, inverseMass);
    }

    @Override
    public void getInverseInertiaDiagonal(FVec3 out) {
        JPC_MotionProperties_GetInverseInertiaDiagonal(handle, out.address());
    }

    @Override
    public void getInertiaRotation(Quat out) {
        JPC_MotionProperties_GetInertiaRotation(handle, out.address());
    }

    @Override
    public void setInverseInertia(FVec3 diagonal, Quat rot) {
        JPC_MotionProperties_SetInverseInertia(handle, diagonal.address(), rot.address());
    }

    @Override
    public void getLocalSpaceInverseInertia(FMat44 out) {
        JPC_MotionProperties_GetLocalSpaceInverseInertia(handle, out.address());
    }

    @Override
    public void getInverseInertiaForRotation(FMat44 rotation, FMat44 out) {
        JPC_MotionProperties_GetInverseInertiaForRotation(handle, rotation.address(), out.address());
    }

    @Override
    public void multiplyWorldSpaceInverseInertiaByVector(Quat bodyRotation, FVec3 v, FVec3 out) {
        JPC_MotionProperties_MultiplyWorldSpaceInverseInertiaByVector(handle, bodyRotation.address(), v.address(), out.address());
    }

    @Override
    public void getPointVelocityCOM(FVec3 pointRelativeToCOM, FVec3 out) {
        JPC_MotionProperties_GetPointVelocityCOM(handle, pointRelativeToCOM.address(), out.address());
    }

    @Override
    public void getAccumulatedForce(FVec3 out) {
        JPC_MotionProperties_GetAccumulatedForce(handle, out.address());
    }

    @Override
    public void getAccumulatedTorque(FVec3 out) {
        JPC_MotionProperties_GetAccumulatedTorque(handle, out.address());
    }
}
