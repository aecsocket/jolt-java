package jolt.physics.body;

import jolt.math.JtQuat;
import jolt.math.JtVec3f;

public sealed interface MutableMotionProperties extends MotionProperties permits MotionPropertiesImpl {
    static MutableMotionProperties ref(long address) { return address == 0 ? null : new MotionPropertiesImpl(address); }

    void setLinearVelocity(JtVec3f linearVelocity);

    void setLinearVelocityClamped(JtVec3f linearVelocity);

    void setAngularVelocity(JtVec3f angularVelocity);

    void setAngularVelocityClamped(JtVec3f angularVelocity);

    void moveKinematic(JtVec3f deltaPosition, JtQuat deltaRotation, float deltaTime);

    void setMaxLinearVelocity(float linearVelocity);

    void setMaxAngularVelocity(float angularVelocity);

    void clampLinearVelocity();

    void clampAngularVelocity();

    void setLinearDamping(float linearDamping);

    void setAngularDamping(float angularDamping);

    void setGravityFactor(float gravityFactor);

    void setMassProperties(MassProperties massProperties);

    void setInverseMass(float inverseMass);

    void setInverseInertia(JtVec3f diagonal, JtQuat rot);
}
