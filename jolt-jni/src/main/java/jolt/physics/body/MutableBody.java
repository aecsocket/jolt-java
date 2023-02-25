package jolt.physics.body;

import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.MutableCollisionGroup;

public sealed interface MutableBody extends Body permits BodyImpl {
    static MutableBody ref(long address) { return address == 0 ? null : new BodyImpl(address); }

    void setIsSensor(boolean isSensor);

    void setUseManifoldReduction(boolean useReduction);

    void setMotionType(MotionType motionType);

    @Override
    MutableCollisionGroup getCollisionGroup();

    void setCollisionGroup(CollisionGroup group);

    void setAllowSleeping(boolean allow);

    void setFriction(float friction);

    void setRestitution(float restitution);

    void setLinearVelocity(JtVec3f linearVelocity);

    void setLinearVelocityClamped(JtVec3f linearVelocity);

    void setAngularVelocity(JtVec3f angularVelocity);

    void setAngularVelocityClamped(JtVec3f angularVelocity);

    void addForce(JtVec3f force);

    void addForceSp(JtVec3f force, JtVec3f position);

    void addForceDp(JtVec3f force, JtVec3d position);

    void addTorque(JtVec3f torque);

    void addImpulse(JtVec3f impulse);

    void addImpulseSp(JtVec3f impulse, JtVec3f position);

    void addImpulseDp(JtVec3f impulse, JtVec3d position);

    void addAngularImpulse(JtVec3f angularImpulse);

    void moveKinematicSp(JtVec3f targetPosition, JtQuat targetRotation, float deltaTime);

    void moveKinematicDp(JtVec3d targetPosition, JtQuat targetRotation, float deltaTime);

    boolean applyBuoyancyImpulseSp(JtVec3f surfacePosition, JtVec3f surfaceNormal, float buoyancy, float linearDrag, float angularDrag, JtVec3f fluidVelocity, JtVec3f gravity, float deltaTime);

    boolean applyBuoyancyImpulseDp(JtVec3d surfacePosition, JtVec3f surfaceNormal, float buoyancy, float linearDrag, float angularDrag, JtVec3f fluidVelocity, JtVec3f gravity, float deltaTime);

    @Override
    MutableMotionProperties getMotionProperties();

    void setUserData(long userData);
}
