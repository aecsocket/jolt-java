package jolt.physics.body;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import jolt.Preconditions;
import jolt.geometry.AABox;
import jolt.math.*;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.MutableCollisionGroup;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

@JniInclude("<Jolt/Physics/Body/Body.h>")
@JniTypeMapping("Body")
final class BodyImpl extends JoltNativeImpl implements MutableBody {
    BodyImpl(long address) { super(address); }
    
    private void ensureNotStatic() {
        Preconditions.ensure(!isStatic(), "Body is static");
    }

    private void ensureDynamic() {
        Preconditions.ensure(isDynamic(), "Body is not dynamic");
    }

    @Override
    public int getId() { return _getId(address); }
    @JniBindSelf("return (jint) self->GetID().GetIndexAndSequenceNumber();")
    private static native int _getId(long _a);

    @Override
    public boolean isActive() { return _isActive(address); }
    @JniBindSelf("return self->IsActive();")
    private static native boolean _isActive(long _a);

    @Override
    public boolean isStatic() { return _isStatic(address); }
    @JniBindSelf("return self->IsStatic();")
    private static native boolean _isStatic(long _a);

    @Override
    public boolean isKinematic() { return _isKinematic(address); }
    @JniBindSelf("return self->IsKinematic();")
    private static native boolean _isKinematic(long _a);

    @Override
    public boolean isDynamic() { return _isDynamic(address); }
    @JniBindSelf("return self->IsDynamic();")
    private static native boolean _isDynamic(long _a);

    @Override
    public boolean canBeKinematicOrDynamic() { return _canBeKinematicOrDynamic(address); }
    @JniBindSelf("return self->CanBeKinematicOrDynamic();")
    private static native boolean _canBeKinematicOrDynamic(long _a);

    @Override
    public boolean isSensor() { return _isSensor(address); }
    @JniBindSelf("self->IsSensor();")
    private static native boolean _isSensor(long _a);

    @Override
    public void setIsSensor(boolean isSensor) { _setIsSensor(address, isSensor); }
    @JniBindSelf("self->SetIsSensor(value);")
    private static native void _setIsSensor(long _a, boolean value);

    @Override
    public boolean getUseManifoldReduction() { return _getUseManifoldReduction(address); }
    @JniBindSelf("return self->GetUseManifoldReduction();")
    private static native boolean _getUseManifoldReduction(long _a);

    @Override
    public void setUseManifoldReduction(boolean useReduction) { _setUseManifoldReduction(address, useReduction); }
    @JniBindSelf("self->SetUseManifoldReduction(value);")
    private static native void _setUseManifoldReduction(long _a, boolean value);

    @Override
    public boolean getUseManifoldReductionWithBody(Body body) { return _getUseManifoldReductionWithBody(address, body.getAddress()); }
    @JniBindSelf("return self->GetUseManifoldReductionWithBody(*((Body*) body));")
    private static native boolean _getUseManifoldReductionWithBody(long _a, long body);

    @Override
    public MotionType getMotionType() { return MotionType.values()[_getMotionType(address)]; }
    @JniBindSelf("return (jint) self->GetMotionType();")
    private static native int _getMotionType(long _a);

    @Override
    public void setMotionType(MotionType motionType) { _setMotionType(address, motionType.ordinal()); }
    @JniBindSelf("self->SetMotionType((EMotionType) value);")
    private static native void _setMotionType(long _a, int value);

    @Override
    public byte getBroadPhaseLayer() { return _getBroadPhaseLayer(address); }
    @JniBindSelf("return (BroadPhaseLayer::Type) self->GetBroadPhaseLayer();")
    private static native byte _getBroadPhaseLayer(long _a);

    @Override
    public short getObjectLayer() { return _getObjectLayer(address); }
    @JniBindSelf("return self->GetObjectLayer();")
    private static native short _getObjectLayer(long _a);

    @Override
    public MutableCollisionGroup getCollisionGroup() { return MutableCollisionGroup.ref(_getCollisionGroup(address)); }
    @JniBindSelf("return (jlong) &self->GetCollisionGroup();")
    private static native long _getCollisionGroup(long _a);

    @Override
    public void setCollisionGroup(CollisionGroup group) { _setCollisionGroup(address, group.getAddress()); }
    @JniBindSelf("self->SetCollisionGroup(*((CollisionGroup*) value));")
    private static native void _setCollisionGroup(long _a, long value);

    @Override
    public boolean getAllowSleeping() { return _getAllowSleeping(address); }
    @JniBindSelf("return self->GetAllowSleeping();")
    private static native boolean _getAllowSleeping(long _a);

    @Override
    public void setAllowSleeping(boolean allow) { _setAllowSleeping(address, allow); }
    @JniBindSelf("self->SetAllowSleeping(value);")
    private static native void _setAllowSleeping(long _a, boolean value);

    @Override
    public float getFriction() { return _getFriction(address); }
    @JniBindSelf("return self->GetFriction();")
    private static native float _getFriction(long _a);

    @Override
    public void setFriction(float friction) {
        _setFriction(address, Preconditions.gtOrEq("friction", friction, 0f));
    }
    @JniBindSelf("self->SetFriction(value);")
    private static native void _setFriction(long _a, float value);

    @Override
    public float getRestitution() { return _getRestitution(address); }
    @JniBindSelf("return self->GetRestitution();")
    private static native float _getRestitution(long _a);

    @Override
    public void setRestitution(float restitution) {
        _setRestitution(address, Preconditions.between("restitution", restitution, 0f, 1f));
    }
    @JniBindSelf("self->SetRestitution(value);")
    private static native void _setRestitution(long _a, float value);

    @Override
    public JtVec3f getLinearVelocity(JtVec3f out) {
        _getLinearVelocity(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetLinearVelocity(), out);")
    private static native void _getLinearVelocity(long _a, JtVec3f out);

    @Override
    public void setLinearVelocity(JtVec3f linearVelocity) {
        ensureNotStatic();
        _setLinearVelocity(address, linearVelocity.x, linearVelocity.y, linearVelocity.z);
    }
    @JniBindSelf("self->SetLinearVelocity(Vec3(valueX, valueY, valueZ));")
    private static native void _setLinearVelocity(long _a, float valueX, float valueY, float valueZ);

    @Override
    public void setLinearVelocityClamped(JtVec3f linearVelocity) {
        ensureNotStatic();
        _setLinearVelocityClamped(address, linearVelocity.x, linearVelocity.y, linearVelocity.z);
    }
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
    public void setAngularVelocity(JtVec3f AngularVelocity) {
        ensureNotStatic();
        _setAngularVelocity(address, AngularVelocity.x, AngularVelocity.y, AngularVelocity.z);
    }
    @JniBindSelf("self->SetAngularVelocity(Vec3(valueX, valueY, valueZ));")
    private static native void _setAngularVelocity(long _a, float valueX, float valueY, float valueZ);

    @Override
    public void setAngularVelocityClamped(JtVec3f AngularVelocity) {
        ensureNotStatic();
        _setAngularVelocityClamped(address, AngularVelocity.x, AngularVelocity.y, AngularVelocity.z);
    }
    @JniBindSelf("self->SetAngularVelocityClamped(Vec3(valueX, valueY, valueZ));")
    private static native void _setAngularVelocityClamped(long _a, float valueX, float valueY, float valueZ);

    @Override
    public JtVec3f getPointVelocityCOM(JtVec3f pointRelativeToCOM, JtVec3f out) {
        _getPointVelocityCOM(address, pointRelativeToCOM.x, pointRelativeToCOM.y, pointRelativeToCOM.z, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetPointVelocityCOM(Vec3(pointRelativeToCOMX, pointRelativeToCOMY, pointRelativeToCOMZ)), out);")
    private static native void _getPointVelocityCOM(long _a, float pointRelativeToCOMX, float pointRelativeToCOMY, float pointRelativeToCOMZ, JtVec3f out);

    @Override
    public JtVec3f getPointVelocitySp(JtVec3f point, JtVec3f out) {
        _getPointVelocitySp(address, point.x, point.y, point.z, out);
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetPointVelocity(Vec3(pointX, pointY, pointZ)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPointVelocitySp(long _a, float pointX, float pointY, float pointZ, JtVec3f out);

    @Override
    public JtVec3f getPointVelocityDp(JtVec3d point, JtVec3f out) {
        _getPointVelocityDp(address, point.x, point.y, point.z, out);
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetPointVelocity(DVec3(pointX, pointY, pointZ)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPointVelocityDp(long _a, double pointX, double pointY, double pointZ, JtVec3f out);

    @Override
    public void addForce(JtVec3f force) {
        ensureDynamic();
        _addForce(address, force.x, force.y, force.z);
    }
    @JniBindSelf("self->AddForce(Vec3(forceX, forceY, forceZ));")
    private static native void _addForce(long _a, float forceX, float forceY, float forceZ);

    @Override
    public void addForceSp(JtVec3f force, JtVec3f position) {
        ensureDynamic();
        _addForceSp(
                address,
                force.x, force.y, force.z,
                position.x, position.y, position.z
        );
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            self->AddForce(Vec3(forceX, forceY, forceZ), Vec3(positionX, positionY, positionZ));
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _addForceSp(
            long _a,
            float forceX, float forceY, float forceZ,
            float positionX, float positionY, float positionZ
    );

    @Override
    public void addForceDp(JtVec3f force, JtVec3d position) {
        ensureDynamic();
        _addForceDp(
                address,
                force.x, force.y, force.z,
                position.x, position.y, position.z
        );
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            self->AddForce(Vec3(forceX, forceY, forceZ), DVec3(positionX, positionY, positionZ));
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _addForceDp(
            long _a,
            float forceX, float forceY, float forceZ,
            double positionX, double positionY, double positionZ
    );

    @Override
    public void addTorque(JtVec3f torque) {
        ensureDynamic();
        _addTorque(address, torque.x, torque.y, torque.z);
    }
    @JniBindSelf("self->AddTorque(Vec3(torqueX, torqueY, torqueZ));")
    private static native void _addTorque(long _a, float torqueX, float torqueY, float torqueZ);

    @Override
    public JtVec3f getAccumulatedForce(JtVec3f out) {
        ensureDynamic();
        _getAccumulatedForce(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetAccumulatedForce(), out);")
    private static native void _getAccumulatedForce(long _a, JtVec3f out);

    @Override
    public JtVec3f getAccumulatedTorque(JtVec3f out) {
        ensureDynamic();
        _getAccumulatedTorque(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetAccumulatedTorque(), out);")
    private static native void _getAccumulatedTorque(long _a, JtVec3f out);

    @Override
    public JtMat44f getInverseInertia(JtMat44f out) {
        _getInverseInertia(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetInverseInertia(), out);")
    private static native void _getInverseInertia(long _a, JtMat44f out);

    @Override
    public void addImpulse(JtVec3f impulse) {
        ensureDynamic();
        _addImpulse(address, impulse.x, impulse.y, impulse.z);
    }
    @JniBindSelf("self->AddImpulse(Vec3(impulseX, impulseY, impulseZ));")
    private static native void _addImpulse(long _a, float impulseX, float impulseY, float impulseZ);

    @Override
    public void addImpulseSp(JtVec3f impulse, JtVec3f position) {
        ensureDynamic();
        _addImpulseSp(
                address,
                impulse.x, impulse.y, impulse.z,
                position.x, position.y, position.z
        );
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            self->AddImpulse(Vec3(impulseX, impulseY, impulseZ), Vec3(positionX, positionY, positionZ));
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _addImpulseSp(
            long _a,
            float impulseX, float impulseY, float impulseZ,
            float positionX, float positionY, float positionZ
    );

    @Override
    public void addImpulseDp(JtVec3f impulse, JtVec3d position) {
        ensureDynamic();
        _addImpulseDp(
                address,
                impulse.x, impulse.y, impulse.z,
                position.x, position.y, position.z
        );
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            self->AddImpulse(Vec3(impulseX, impulseY, impulseZ), DVec3(positionX, positionY, positionZ));
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _addImpulseDp(
            long _a,
            float impulseX, float impulseY, float impulseZ,
            double positionX, double positionY, double positionZ
    );

    @Override
    public void addAngularImpulse(JtVec3f angularImpulse) {
        ensureDynamic();
        _addAngularImpulse(address, angularImpulse.x, angularImpulse.y, angularImpulse.z);
    }
    @JniBindSelf("self->AddAngularImpulse(Vec3(angularImpulseX, angularImpulseY, angularImpulseZ));")
    private static native void _addAngularImpulse(long _a, float angularImpulseX, float angularImpulseY, float angularImpulseZ);

    @Override
    public void moveKinematicSp(JtVec3f targetPosition, JtQuat targetRotation, float deltaTime) {
        ensureNotStatic();
        _moveKinematicSp(
                address,
                targetPosition.x, targetPosition.y, targetPosition.z,
                targetRotation.x, targetRotation.y, targetRotation.z, targetRotation.w,
                deltaTime
        );
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            self->MoveKinematic(Vec3(targetPositionX, targetPositionY, targetPositionZ), Quat(targetRotationX, targetRotationY, targetRotationZ), deltaTime);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _moveKinematicSp(
            long _a,
            float targetPositionX, float targetPositionY, float targetPositionZ,
            float targetRotationX, float targetRotationY, float targetRotationZ, float targetRotationW,
            float deltaTime
    );

    @Override
    public void moveKinematicDp(JtVec3d targetPosition, JtQuat targetRotation, float deltaTime) {
        ensureNotStatic();
        _moveKinematicDp(
                address,
                targetPosition.x, targetPosition.y, targetPosition.z,
                targetRotation.x, targetRotation.y, targetRotation.z, targetRotation.w,
                deltaTime
        );
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            self->MoveKinematic(DVec3(targetPositionX, targetPositionY, targetPositionZ), Quat(targetRotationX, targetRotationY, targetRotationZ), deltaTime);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _moveKinematicDp(
            long _a,
            double targetPositionX, double targetPositionY, double targetPositionZ,
            float targetRotationX, float targetRotationY, float targetRotationZ, float targetRotationW,
            float deltaTime
    );

    @Override
    public boolean applyBuoyancyImpulseSp(JtVec3f surfacePosition, JtVec3f surfaceNormal, float buoyancy, float linearDrag, float angularDrag, JtVec3f fluidVelocity, JtVec3f gravity, float deltaTime) {
        return _applyBuoyancyImpulseSp(
                address,
                surfacePosition.x, surfacePosition.y, surfacePosition.z,
                surfaceNormal.x, surfaceNormal.y, surfaceNormal.z,
                buoyancy, linearDrag, angularDrag,
                fluidVelocity.x, fluidVelocity.y, fluidVelocity.z,
                gravity.x, gravity.y, gravity.z,
                deltaTime
        );
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            return self->ApplyBuoyancyImpulse(
                Vec3(surfacePositionX, surfacePositionY, surfacePositionZ),
                Vec3(surfaceNormalX, surfaceNormalY, surfaceNormalZ),
                buoyancy, linearDrag, angularDrag,
                Vec3(fluidVelocityX, fluidVelocityY, fluidVelocityZ),
                Vec3(gravityX, gravityY, gravityZ),
                deltaTime
            );
            #else
            THROW_WRONG_PRECISION
            return JNI_FALSE
            #endif""")
    private static native boolean _applyBuoyancyImpulseSp(
            long _a,
            float surfacePositionX, float surfacePositionY, float surfacePositionZ,
            float surfaceNormalX, float surfaceNormalY, float surfaceNormalZ,
            float buoyancy, float linearDrag, float angularDrag,
            float fluidVelocityX, float fluidVelocityY, float fluidVelocityZ,
            float gravityX, float gravityY, float gravityZ,
            float deltaTime
    );

    @Override
    public boolean applyBuoyancyImpulseDp(JtVec3d surfacePosition, JtVec3f surfaceNormal, float buoyancy, float linearDrag, float angularDrag, JtVec3f fluidVelocity, JtVec3f gravity, float deltaTime) {
        return _applyBuoyancyImpulseDp(
                address,
                surfacePosition.x, surfacePosition.y, surfacePosition.z,
                surfaceNormal.x, surfaceNormal.y, surfaceNormal.z,
                buoyancy, linearDrag, angularDrag,
                fluidVelocity.x, fluidVelocity.y, fluidVelocity.z,
                gravity.x, gravity.y, gravity.z,
                deltaTime
        );
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            return self->ApplyBuoyancyImpulse(
                DVec3(surfacePositionX, surfacePositionY, surfacePositionZ),
                Vec3(surfaceNormalX, surfaceNormalY, surfaceNormalZ),
                buoyancy, linearDrag, angularDrag,
                Vec3(fluidVelocityX, fluidVelocityY, fluidVelocityZ),
                Vec3(gravityX, gravityY, gravityZ),
                deltaTime
            );
            #else
            THROW_WRONG_PRECISION
            return JNI_FALSE
            #endif""")
    private static native boolean _applyBuoyancyImpulseDp(
            long _a,
            double surfacePositionX, double surfacePositionY, double surfacePositionZ,
            float surfaceNormalX, float surfaceNormalY, float surfaceNormalZ,
            float buoyancy, float linearDrag, float angularDrag,
            float fluidVelocityX, float fluidVelocityY, float fluidVelocityZ,
            float gravityX, float gravityY, float gravityZ,
            float deltaTime
    );

    @Override
    public boolean isInBroadPhase() { return _isInBroadPhase(address); }
    @JniBindSelf("return self->IsInBroadPhase();")
    private static native boolean _isInBroadPhase(long _a);

    @Override
    public boolean isCollisionCacheInvalid() { return _isCollisionCacheInvalid(address); }
    @JniBindSelf("return self->IsCollisionCacheInvalid();")
    private static native boolean _isCollisionCacheInvalid(long _a);

    @Override
    public Shape getShape() { return Shape.ref(_getShape(address)); }
    @JniBindSelf("return (jlong) self->GetShape();")
    private static native long _getShape(long _a);

    @Override
    public JtVec3f getPositionSp(JtVec3f out) {
        _getPositionSp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetPosition(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionSp(long _a, JtVec3f out);

    @Override
    public JtVec3d getPositionDp(JtVec3d out) {
        _getPositionDp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetPosition(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionDp(long _a, JtVec3d out);

    @Override
    public JtQuat getRotation(JtQuat out) {
        _getRotation(address, out);
        return out;
    }
    @JniBindSelf("ToJava(env, self->GetRotation(), out);")
    private static native void _getRotation(long _a, JtQuat out);

    @Override
    public JtMat44f getWorldTransformSp(JtMat44f out) {
        _getWorldTransformSp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetWorldTransform(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getWorldTransformSp(long _a, JtMat44f out);

    @Override
    public JtMat44d getWorldTransformDp(JtMat44d out) {
        _getWorldTransformDp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetWorldTransform(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getWorldTransformDp(long _a, JtMat44d out);

    @Override
    public JtVec3f getCenterOfMassPositionSp(JtVec3f out) {
        _getCenterOfMassPositionSp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetCenterOfMassPosition(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassPositionSp(long _a, JtVec3f out);

    @Override
    public JtVec3d getCenterOfMassPositionDp(JtVec3d out) {
        _getCenterOfMassPositionDp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetCenterOfMassPosition(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassPositionDp(long _a, JtVec3d out);

    @Override
    public JtMat44f getCenterOfMassTransformSp(JtMat44f out) {
        _getCenterOfMassTransformSp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetCenterOfMassTransform(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassTransformSp(long _a, JtMat44f out);

    @Override
    public JtMat44d getCenterOfMassTransformDp(JtMat44d out) {
        _getCenterOfMassTransformDp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetCenterOfMassTransform(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassTransformDp(long _a, JtMat44d out);

    @Override
    public JtMat44f getInverseCenterOfMassTransformSp(JtMat44f out) {
        _getInverseCenterOfMassTransformSp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetInverseCenterOfMassTransform(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getInverseCenterOfMassTransformSp(long _a, JtMat44f out);

    @Override
    public JtMat44d getInverseCenterOfMassTransformDp(JtMat44d out) {
        _getInverseCenterOfMassTransformDp(address, out);
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetInverseCenterOfMassTransform(), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getInverseCenterOfMassTransformDp(long _a, JtMat44d out);

    @Override
    public AABox getWorldSpaceBounds(AABox out) {
        _getWorldSpaceBounds(address, out);
        return out;
    }
    @JniBindSelf("ToJava(env, self->GetWorldSpaceBounds(), out);")
    private static native void _getWorldSpaceBounds(long _a, AABox out);

    @Override
    public MutableMotionProperties getMotionProperties() {
        ensureNotStatic();
        return MutableMotionProperties.ref(_getMotionProperties(address));
    }
    // unchecked since we check `isStatic()` above
    @JniBindSelf("return (jlong) self->GetMotionPropertiesUnchecked();")
    private static native long _getMotionProperties(long _a);

    @Override
    public long getUserData() { return _getUserData(address); }
    @JniBindSelf("return self->GetUserData();")
    private static native long _getUserData(long _a);

    @Override
    public void setUserData(long userData) { _setUserData(address, userData); }
    @JniBindSelf("self->SetUserData(value);")
    private static native void _setUserData(long _a, long value);

    @Override
    public JtVec3f getWorldSpaceSurfaceNormalSp(int subShapeId, JtVec3f position, JtVec3f out) {
        _getWorldSpaceSurfaceNormalSp(
                address, subShapeId,
                position.x, position.y, position.z,
                out
        );
        return out;
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetWorldSpaceSurfaceNormal(SubShapeIDOf(subShapeId), Vec3(positionX, positionY, positionZ)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getWorldSpaceSurfaceNormalSp(
            long _a, int subShapeId,
            float positionX, float positionY, float positionZ,
            JtVec3f out
    );

    @Override
    public JtVec3f getWorldSpaceSurfaceNormalDp(int subShapeId, JtVec3d position, JtVec3f out) {
        _getWorldSpaceSurfaceNormalDp(
                address, subShapeId,
                position.x, position.y, position.z,
                out
        );
        return out;
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetWorldSpaceSurfaceNormal(SubShapeIDOf(subShapeId), DVec3(positionX, positionY, positionZ)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getWorldSpaceSurfaceNormalDp(
            long _a, int subShapeId,
            double positionX, double positionY, double positionZ,
            JtVec3f out
    );

    // TODO
    @Override
    public TransformedShape getTransformedShape() { return new TransformedShape() {}; }

    // TODO we need BCS to be a value type
    @Override
    public BodyCreationSettings getBodyCreationSettings() { throw unimplemented(); }
}
