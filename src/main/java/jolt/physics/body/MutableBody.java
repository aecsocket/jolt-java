package jolt.physics.body;

import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.CollisionGroup;

import java.lang.foreign.MemoryAddress;

public sealed interface MutableBody extends Body permits BodyImpl {
    static MutableBody at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new BodyImpl.D(addr)
                : new BodyImpl.F(addr);
    }

    void setIsSensor(boolean isSensor);

    void setUseManifoldReduction(boolean useManifoldReduction);

    void setMotionType(MotionType motionType);

    void setCollisionGroup(CollisionGroup group);

    void setAllowSleeping(boolean allowSleeping);

    void setFriction(float friction);

    void setRestitution(float restitution);

    void setLinearVelocity(FVec3 linearVelocity);

    void setLinearVelocityClamped(FVec3 linearVelocity);

    void setAngularVelocity(FVec3 angularVelocity);

    void setAngularVelocityClamped(FVec3 angularVelocity);

    void addForce(FVec3 force);

    void addForce(FVec3 force, FVec3 position);

    void addForce(FVec3 force, DVec3 position);

    void addTorque(FVec3 torque);

    void addImpulse(FVec3 impulse);

    void addImpulse(FVec3 impulse, FVec3 position);

    void addImpulse(FVec3 impulse, DVec3 position);

    void addAngularImpulse(FVec3 angularImpulse);

    void moveKinematic(FVec3 targetPosition, Quat targetRotation, float deltaTime);

    void moveKinematic(DVec3 targetPosition, Quat targetRotation, float deltaTime);

    boolean applyBuoyancyImpulse(
            FVec3 surfacePosition,
            FVec3 surfaceNormal,
            float buoyancy,
            float linearDrag,
            float angularDrag,
            FVec3 fluidVelocity,
            FVec3 gravity,
            float deltaTime
    );

    boolean applyBuoyancyImpulse(
            DVec3 surfacePosition,
            FVec3 surfaceNormal,
            float buoyancy,
            float linearDrag,
            float angularDrag,
            FVec3 fluidVelocity,
            FVec3 gravity,
            float deltaTime
    );

    @Override
    MutableMotionProperties getMotionProperties();

    void setUserData(long userData);
}
