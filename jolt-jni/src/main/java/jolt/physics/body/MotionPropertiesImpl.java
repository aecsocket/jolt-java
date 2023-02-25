package jolt.physics.body;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3f;

@JniInclude("<Jolt/Physics/Body/MotionProperties.h>")
@JniTypeMapping("MotionProperties")
final class MotionPropertiesImpl extends JoltNativeImpl implements MutableMotionProperties {
    MotionPropertiesImpl(long address) { super(address); }

    @Override
    public MotionQuality getMotionQuality() { return MotionQuality.values()[_getMotionQuality(address)]; }
    @JniBindSelf("return (jint) self->GetMotionQuality();")
    private static native int _getMotionQuality(long _a);

    @Override
    public JtVec3f getLinearVelocity(JtVec3f out) {
        _getLinearVelocity(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetLinearVelocity(), out);")
    private static native void _getLinearVelocity(long _a, JtVec3f out);

    @Override
    public void setLinearVelocity(JtVec3f linearVelocity) { _setLinearVelocity(address, linearVelocity.x, linearVelocity.y, linearVelocity.z); }
    @JniBindSelf("self->SetLinearVelocity(Vec3(valueX, valueY, valueZ));")
    private static native void _setLinearVelocity(long _a, float valueX, float valueY, float valueZ);

    @Override
    public void setLinearVelocityClamped(JtVec3f linearVelocity) { _setLinearVelocityClamped(address, linearVelocity.x, linearVelocity.y, linearVelocity.z); }
    @JniBindSelf("self->SetLinearVelocityClamped(Vec3(valueX, valueY, valueZ));")
    private static native void _setLinearVelocityClamped(long _a, float valueX, float valueY, float valueZ);

    @Override
    public JtVec3f getAngularVelocity(JtVec3f out) {
        _getAngularVelocity(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetAngularVelocity(), out);")
    private static native void _getAngularVelocity(long _a, JtVec3f out);

    @Override
    public void setAngularVelocity(JtVec3f angularVelocity) { _setAngularVelocity(address, angularVelocity.x, angularVelocity.y, angularVelocity.z); }
    @JniBindSelf("self->SetAngularVelocity(Vec3(valueX, valueY, valueZ));")
    private static native void _setAngularVelocity(long _a, float valueX, float valueY, float valueZ);

    @Override
    public void setAngularVelocityClamped(JtVec3f angularVelocity) { _setAngularVelocityClamped(address, angularVelocity.x, angularVelocity.y, angularVelocity.z); }
    @JniBindSelf("self->SetAngularVelocityClamped(Vec3(valueX, valueY, valueZ));")
    private static native void _setAngularVelocityClamped(long _a, float valueX, float valueY, float valueZ);

    @Override
    public void moveKinematic(JtVec3f deltaPosition, JtQuat deltaRotation, float deltaTime) {
        _moveKinematic(
                address,
                deltaPosition.x, deltaPosition.y, deltaPosition.z,
                deltaRotation.x, deltaRotation.y, deltaRotation.z, deltaRotation.w,
                deltaTime
        );
    }
    @JniBindSelf("""
            self->MoveKinematic(
                Vec3(deltaPositionX, deltaPositionY, deltaPositionZ),
                Quat(deltaRotationX, deltaRotationY, deltaRotationZ, deltaRotationW),
                deltaTime
            );""")
    private static native void _moveKinematic(
            long _a,
            float deltaPositionX, float deltaPositionY, float deltaPositionZ,
            float deltaRotationX, float deltaRotationY, float deltaRotationZ, float deltaRotationW,
            float deltaTime
    );

    @Override
    public float getMaxLinearVelocity() { return _getMaxLinearVelocity(address); }
    @JniBindSelf("return self->GetMaxLinearVelocity();")
    private static native float _getMaxLinearVelocity(long _a);

    @Override
    public void setMaxLinearVelocity(float linearVelocity) { _setMaxLinearVelocity(address, linearVelocity); }
    @JniBindSelf("self->SetMaxLinearVelocity(value);")
    private static native void _setMaxLinearVelocity(long _a, float value);

    @Override
    public float getMaxAngularVelocity() { return _getMaxAngularVelocity(address); }
    @JniBindSelf("return self->GetMaxAngularVelocity();")
    private static native float _getMaxAngularVelocity(long _a);

    @Override
    public void setMaxAngularVelocity(float angularVelocity) { _setMaxAngularVelocity(address, angularVelocity); }
    @JniBindSelf("self->SetMaxAngularVelocity(value);")
    private static native void _setMaxAngularVelocity(long _a, float value);

    @Override
    public void clampLinearVelocity() { _clampLinearVelocity(address); }
    @JniBindSelf("self->ClampLinearVelocity();")
    private static native void _clampLinearVelocity(long _a);
    
    @Override
    public void clampAngularVelocity() { _clampAngularVelocity(address); }
    @JniBindSelf("self->ClampAngularVelocity();")
    private static native void _clampAngularVelocity(long _a);

    @Override
    public float getLinearDamping() { return _getLinearDamping(address); }
    @JniBindSelf("return self->GetLinearDamping();")
    private static native float _getLinearDamping(long _a);

    @Override
    public void setLinearDamping(float linearDamping) { _setLinearDamping(address, linearDamping); }
    @JniBindSelf("self->SetLinearDamping(value);")
    private static native void _setLinearDamping(long _a, float value);

    @Override
    public float getAngularDamping() { return _getAngularDamping(address); }
    @JniBindSelf("return self->GetAngularDamping();")
    private static native float _getAngularDamping(long _a);

    @Override
    public void setAngularDamping(float angularDamping) { _setAngularDamping(address, angularDamping); }
    @JniBindSelf("self->SetAngularDamping(value);")
    private static native void _setAngularDamping(long _a, float value);

    @Override
    public float getGravityFactor() { return _getGravityFactor(address); }
    @JniBindSelf("return self->GetGravityFactor();")
    private static native float _getGravityFactor(long _a);

    @Override
    public void setGravityFactor(float gravityFactor) { _setGravityFactor(address, gravityFactor); }
    @JniBindSelf("self->SetGravityFactor(value);")
    private static native void _setGravityFactor(long _a, float value);

    @Override
    public void setMassProperties(MassProperties massProperties) {
        JtMat44f inertia = massProperties.inertia;
        _setMassProperties(
                address, massProperties.mass,
                inertia.n00, inertia.n01, inertia.n02, inertia.n03,
                inertia.n10, inertia.n11, inertia.n12, inertia.n13,
                inertia.n20, inertia.n21, inertia.n22, inertia.n23,
                inertia.n30, inertia.n31, inertia.n32
        );
    }
    @JniBindSelf("""
            MassProperties value;
            value.mMass = mass;
            value.mInertia = Mat44(
                Vec4(inertia00, inertia01, inertia02, inertia03),
                Vec4(inertia10, inertia11, inertia12, inertia13),
                Vec4(inertia20, inertia21, inertia22, inertia23),
                Vec4(inertia30, inertia31, inertia32, 1.0f)
            );
            self->SetMassProperties(value);""")
    private static native void _setMassProperties(
            long _a, float mass,
            float inertia00, float inertia01, float inertia02, float inertia03,
            float inertia10, float inertia11, float inertia12, float inertia13,
            float inertia20, float inertia21, float inertia22, float inertia23,
            float inertia30, float inertia31, float inertia32
    );

    @Override
    public float getInverseMass() { return _getInverseMass(address); }
    @JniBindSelf("return self->GetInverseMass();")
    private static native float _getInverseMass(long _a);

    @Override
    public float getInverseMassUnchecked() { return _getInverseMassUnchecked(address); }
    @JniBindSelf("return self->GetInverseMassUnchecked();")
    private static native float _getInverseMassUnchecked(long _a);

    @Override
    public void setInverseMass(float inverseMass) { _setInverseMass(address, inverseMass); }
    @JniBindSelf("self->SetInverseMass(value);")
    private static native void _setInverseMass(long _a, float value);

    @Override
    public JtVec3f getInverseInertiaDiagonal(JtVec3f out) {
        _getInverseInertiaDiagonal(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetInverseInertiaDiagonal(), out);")
    private static native void _getInverseInertiaDiagonal(long _a, JtVec3f out);

    @Override
    public JtQuat getInertiaRotation(JtQuat out) {
        _getInertiaRotation(address, out);
        return out;
    }
    @JniBindSelf("ToJava(env, self->GetInertiaRotation(), out);")
    private static native void _getInertiaRotation(long _a, JtQuat out);

    @Override
    public void setInverseInertia(JtVec3f diagonal, JtQuat rot) {
        _setInverseInertia(
                address,
                diagonal.x, diagonal.y, diagonal.z,
                rot.x, rot.y, rot.z, rot.w
        );
    }
    @JniBindSelf("""
            self->SetInverseInertia(
                Vec3(diagonalX, diagonalY, diagonalZ),
                Quat(rotX, rotY, rotZ, rotW)
            );""")
    private static native void _setInverseInertia(
            long _a,
            float diagonalX, float diagonalY, float diagonalZ,
            float rotX, float rotY, float rotZ, float rotW
    );

    @Override
    public JtMat44f getLocalSpaceInverseInertia(JtMat44f out) {
        _getLocalSpaceInverseInertia(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetLocalSpaceInverseInertia(), out);")
    private static native void _getLocalSpaceInverseInertia(long _a, JtMat44f out);

    @Override
    public JtMat44f getLocalSpaceInverseInertiaUnchecked(JtMat44f out) {
        _getLocalSpaceInverseInertiaUnchecked(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetLocalSpaceInverseInertiaUnchecked(), out);")
    private static native void _getLocalSpaceInverseInertiaUnchecked(long _a, JtMat44f out);

    @Override
    public JtMat44f getInverseInertiaForRotation(JtMat44f rotation, JtMat44f out) {
        _getInverseInertiaForRotation(
                address,
                rotation.n00, rotation.n01, rotation.n02, rotation.n03,
                rotation.n10, rotation.n11, rotation.n12, rotation.n13,
                rotation.n20, rotation.n21, rotation.n22, rotation.n23,
                rotation.n30, rotation.n31, rotation.n32,
                out
        );
        return out;
    }
    @JniBindSelf("""
            ToJavaSp(env,
                self->GetInverseInertiaForRotation(Mat44(
                    Vec4(rotation00, rotation01, rotation02, rotation03),
                    Vec4(rotation10, rotation11, rotation12, rotation13),
                    Vec4(rotation20, rotation21, rotation22, rotation23),
                    Vec3(rotation30, rotation31, rotation32)
                )),
                out
            );""")
    private static native void _getInverseInertiaForRotation(
            long _a,
            float rotation00, float rotation01, float rotation02, float rotation03,
            float rotation10, float rotation11, float rotation12, float rotation13,
            float rotation20, float rotation21, float rotation22, float rotation23,
            float rotation30, float rotation31, float rotation32,
            JtMat44f out
    );

    @Override
    public JtVec3f multiplyWorldSpaceInverseInertiaByVector(JtQuat bodyRotation, JtVec3f v, JtVec3f out) {
        _multiplyWorldSpaceInverseInertiaByVector(
                address,
                bodyRotation.x, bodyRotation.y, bodyRotation.z, bodyRotation.w,
                v.x, v.y, v.z,
                out
        );
        return out;
    }
    @JniBindSelf("""
            ToJavaSp(env,
                self->MultiplyWorldSpaceInverseInertiaByVector(
                    Quat(bodyRotationX, bodyRotationY, bodyRotationZ, bodyRotationW),
                    Vec3(vX, vY, vZ)
                ),
                out
            );""")
    private static native void _multiplyWorldSpaceInverseInertiaByVector(
            long _a,
            float bodyRotationX, float bodyRotationY, float bodyRotationZ, float bodyRotationW,
            float vX, float vY, float vZ,
            JtVec3f out
    );

    @Override
    public JtVec3f getPointVelocityCOM(JtVec3f pointRelativeToCOM, JtVec3f out) {
        _getPointVelocityCOM(
                address,
                pointRelativeToCOM.x, pointRelativeToCOM.y, pointRelativeToCOM.z,
                out
        );
        return out;
    }
    @JniBindSelf("""
            ToJavaSp(env,
                self->GetPointVelocityCOM(Vec3(pointRelativeToCOMX, pointRelativeToCOMY, pointRelativeToCOMZ)),
                out
            );""")
    private static native void _getPointVelocityCOM(
            long _a,
            float pointRelativeToCOMX, float pointRelativeToCOMY, float pointRelativeToCOMZ,
            JtVec3f out
    );

    @Override
    public JtVec3f getAccumulatedForce(JtVec3f out) {
        _getAccumulatedForce(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetAccumulatedForce(), out);")
    private static native void _getAccumulatedForce(long _a, JtVec3f out);

    @Override
    public JtVec3f getAccumulatedTorque(JtVec3f out) {
        _getAccumulatedTorque(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetAccumulatedTorque(), out);")
    private static native void _getAccumulatedTorque(long _a, JtVec3f out);
}
