package jolt.physics.body;

import jolt.Jolt;
import jolt.JoltNative;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.math.Quat;

import java.lang.foreign.MemoryAddress;

// TODO
public interface MotionProperties extends JoltNative {
    static MotionProperties at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MotionPropertiesImpl(addr);
    }

    MotionQuality getMotionQuality();

    void getLinearVelocity(FVec3 out);

    void getAngularVelocity(FVec3 out);

    float getMaxLinearVelocity();

    float getMaxAngularVelocity();

    float getLinearDamping();

    float getAngularDamping();

    float getGravityFactor();

    float getInverseMass();

    void getInverseInertiaDiagonal(FVec3 out);

    void getInertiaRotation(Quat out);

    void getLocalSpaceInverseInertia(FMat44 out);

    void getInverseInertiaForRotation(FMat44 rotation, FMat44 out);

    void multiplyWorldSpaceInverseInertiaByVector(Quat bodyRotation, FVec3 v, FVec3 out);

    void getPointVelocityCOM(FVec3 pointRelativeToCOM, FVec3 out);

    void getAccumulatedForce(FVec3 out);

    void getAccumulatedTorque(FVec3 out);
}
