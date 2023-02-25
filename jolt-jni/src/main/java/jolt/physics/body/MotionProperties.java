package jolt.physics.body;

import jolt.JoltNative;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3f;

public sealed interface MotionProperties extends JoltNative permits MutableMotionProperties {
    static MotionProperties ref(long address) { return address == 0 ? null : new MotionPropertiesImpl(address); }

    MotionQuality getMotionQuality();

    JtVec3f getLinearVelocity(JtVec3f out);
    default JtVec3f getLinearVelocity() { return getLinearVelocity(new JtVec3f()); }

    JtVec3f getAngularVelocity(JtVec3f out);
    default JtVec3f getAngularVelocity() { return getAngularVelocity(new JtVec3f()); }

    float getMaxLinearVelocity();

    float getMaxAngularVelocity();

    float getLinearDamping();

    float getAngularDamping();

    float getGravityFactor();

    float getInverseMass();

    float getInverseMassUnchecked();

    JtVec3f getInverseInertiaDiagonal(JtVec3f out);
    default JtVec3f getInverseInertiaDiagonal() { return getInverseInertiaDiagonal(new JtVec3f()); }

    JtQuat getInertiaRotation(JtQuat out);
    default JtQuat getInertiaRotation() { return getInertiaRotation(new JtQuat()); }

    JtMat44f getLocalSpaceInverseInertia(JtMat44f out);
    default JtMat44f getLocalSpaceInverseInertia() { return getLocalSpaceInverseInertia(new JtMat44f()); }

    JtMat44f getLocalSpaceInverseInertiaUnchecked(JtMat44f out);
    default JtMat44f getLocalSpaceInverseInertiaUnchecked() { return getLocalSpaceInverseInertiaUnchecked(new JtMat44f()); }

    JtMat44f getInverseInertiaForRotation(JtMat44f rotation, JtMat44f out);
    default JtMat44f getInverseInertiaForRotation(JtMat44f rotation) { return getInverseInertiaForRotation(rotation, new JtMat44f()); }

    JtVec3f multiplyWorldSpaceInverseInertiaByVector(JtQuat bodyRotation, JtVec3f v, JtVec3f out);
    default JtVec3f multiplyWorldSpaceInverseInertiaByVector(JtQuat bodyRotation, JtVec3f v) { return multiplyWorldSpaceInverseInertiaByVector(bodyRotation, v, new JtVec3f()); }

    JtVec3f getPointVelocityCOM(JtVec3f pointRelativeToCOM, JtVec3f out);
    default JtVec3f getPointVelocityCOM(JtVec3f pointRelativeToCOM) { return getPointVelocityCOM(pointRelativeToCOM, new JtVec3f()); }

    JtVec3f getAccumulatedForce(JtVec3f out);
    default JtVec3f getAccumulatedForce() { return getAccumulatedForce(new JtVec3f()); }

    JtVec3f getAccumulatedTorque(JtVec3f out);
    default JtVec3f getAccumulatedTorque() { return getAccumulatedTorque(new JtVec3f()); }
}
