package jolt.physics.body;

import jolt.math.FVec3;
import jolt.math.Quat;

import java.lang.foreign.MemoryAddress;

public sealed interface MutableMotionProperties extends MotionProperties permits MotionPropertiesImpl {
    static MutableMotionProperties at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MotionPropertiesImpl(addr);
    }

    void setLinearVelocity(FVec3 linearVelocity);

    void setLinearVelocityClamped(FVec3 linearVelocity);

    void setAngularVelocity(FVec3 angularVelocity);

    void setAngularVelocityClamped(FVec3 angularVelocity);

    void moveKinematic(FVec3 deltaPosition, Quat deltaRotation, float deltaTime);

    void setMaxLinearVelocity(float linearVelocity);

    void setMaxAngularVelocity(float angularVelocity);

    void clampLinearVelocity();

    void clampAngularVelocity();

    void setLinearDamping(float linearDamping);

    void setAngularDamping(float angularDamping);

    void setGravityFactor(float gravityFactor);

    void setInverseMass(float inverseMass);

    void setInverseInertia(FVec3 diagonal, Quat rot);
}
