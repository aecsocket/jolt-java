package jolt.physics.body;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import jolt.UnimplementedMethodException;
import jolt.math.*;
import jolt.physics.Activation;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;
import jolt.physics.constraint.TwoBodyConstraint;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@JniInclude("<Jolt/Physics/Body/BodyInterface.h>")
@JniTypeMapping("BodyInterface")
public final class BodyInterface extends JoltNativeImpl {
    private BodyInterface(long address) { super(address); }
    public static BodyInterface ref(long address) { return address == 0 ? null : new BodyInterface(address); }

    private RuntimeException outOfBodies() { return new RuntimeException("Out of bodies"); }

    private RuntimeException invalidId() { return new RuntimeException("Invalid body ID"); }

    public MutableBody createBody(BodyCreationSettings settings) {
        long result = _createBody(address, settings.getAddress());
        if (result == 0) throw outOfBodies();
        return MutableBody.ref(result);
    }
    @JniBindSelf("return (jlong) self->CreateBody(*((BodyCreationSettings*) settings));")
    private static native long _createBody(long _a, long settings);

    public MutableBody createBodyWithId(int bodyId, BodyCreationSettings settings) {
        long result = _createBodyWithId(address, bodyId, settings.getAddress());
        if (result == 0) throw invalidId();
        return MutableBody.ref(result);
    }
    @JniBindSelf("return (jlong) self->CreateBodyWithID(BodyID(bodyId), *((BodyCreationSettings*) settings));")
    private static native long _createBodyWithId(long _a, int bodyId, long settings);

    public MutableBody createBodyWithoutId(BodyCreationSettings settings) { return MutableBody.ref(_createBodyWithoutId(address, settings.getAddress())); }
    @JniBindSelf("return (jlong) self->CreateBodyWithoutID(*((BodyCreationSettings*) settings));")
    private static native long _createBodyWithoutId(long _a, long settings);

    public void destroyBodyWithoutId(MutableBody body) { _destroyBodyWithoutId(address, body.getAddress()); }
    @JniBindSelf("self->DestroyBodyWithoutID((Body*) body);")
    private static native void _destroyBodyWithoutId(long _a, long body);

    public boolean assignBodyId(MutableBody body) { return _assignBodyId(address, body.getAddress()); }
    @JniBindSelf("return self->AssignBodyID((Body*) body);")
    private static native boolean _assignBodyId(long _a, long body);

    public boolean assignBodyId(MutableBody body, int bodyId) { return _assignBodyID(address, body.getAddress(), bodyId); }
    @JniBindSelf("return self->AssignBodyID((Body*) body, BodyID(bodyId));")
    private static native boolean _assignBodyID(long _a, long body, int bodyId);

    public @Nullable MutableBody unassignBodyId(int bodyId) { return MutableBody.ref(_unassignBodyId(address, bodyId)); }
    @JniBindSelf("return (jlong) self->UnassignBodyID(BodyID(bodyId));")
    private static native long _unassignBodyId(long _a, int bodyId);

    public MutableBody[] unassignBodyIds(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }
    public List<MutableBody> unassignBodyIds(Collection<Integer> bodyIds) { return Arrays.asList(unassignBodyIds(BodyIDs.ints(bodyIds))); }

    public void destroyBody(int bodyId) { _destroyBody(address, bodyId); }
    @JniBindSelf("self->DestroyBody(BodyID(bodyId));")
    private static native void _destroyBody(long _a, int bodyId);

    public void destroyBodies(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }
    public void destroyBodies(Collection<Integer> bodyIds) { destroyBodies(BodyIDs.ints(bodyIds)); }

    public void addBody(int bodyId, Activation activationMode) { _addBody(address, bodyId, activationMode.ordinal()); }
    @JniBindSelf("self->AddBody(BodyID(bodyId), (EActivation) activationMode);")
    private static native void _addBody(long _a, int bodyId, int activationMode);

    public void removeBody(int bodyId) { _removeBody(address, bodyId); }
    @JniBindSelf("self->RemoveBody(BodyID(bodyId));")
    private static native void _removeBody(long _a, int bodyId);

    public boolean isAdded(int bodyId) { return _isAdded(address, bodyId); }
    @JniBindSelf("return self->IsAdded(BodyID(bodyId));")
    private static native boolean _isAdded(long _a, int bodyId);

    public int createAndAddBody(BodyCreationSettings settings, Activation activationMode) { return _createAndAddBody(address, settings.getAddress(), activationMode.ordinal()); }
    @JniBindSelf("return self->CreateAndAddBody(*((BodyCreationSettings*) settings), (EActivation) activationMode).GetIndexAndSequenceNumber();")
    private static native int _createAndAddBody(long _a, long settings, int activationMode);

    public void activateBody(int bodyId) { _activateBody(address, bodyId); }
    @JniBindSelf("self->ActivateBody(BodyID(bodyId));")
    private static native void _activateBody(long _a, int bodyId);

    public void activateBodies(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }
    public void activateBodies(Collection<Integer> bodyIds) { activateBodies(BodyIDs.ints(bodyIds)); }

    public void deactivateBody(int bodyId) { _deactivateBody(address, bodyId); }
    @JniBindSelf("self->DeactivateBody(BodyID(bodyId));")
    private static native void _deactivateBody(long _a, int bodyId);

    public void deactivateBodies(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }
    public void deactivateBodies(Collection<Integer> bodyIds) { deactivateBodies(BodyIDs.ints(bodyIds)); }

    public boolean isActive(int bodyId) { return _isActive(address, bodyId); }
    @JniBindSelf("return self->IsActive(BodyID(bodyId));")
    private static native boolean _isActive(long _a, int bodyId);

    public void activateConstraint(TwoBodyConstraint constraint) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setShape(int bodyId, Shape shape, boolean updateMassProperties, Activation activationMode) { _setShape(address, bodyId, shape.getAddress(), updateMassProperties, activationMode.ordinal()); }
    @JniBindSelf("self->SetShape(BodyID(bodyId), (Shape*) shape, updateMassProperties, (EActivation) activationMode);")
    private static native void _setShape(long _a, int bodyId, long shape, boolean updateMassProperties, int activationMode);

    public void notifyShapeChanged(int bodyId, JtVec3f previousCenterOfMass, boolean updateMassProperties, Activation activationMode) {
        _notifyShapeChanged(
                address, bodyId,
                previousCenterOfMass.x, previousCenterOfMass.y, previousCenterOfMass.z,
                updateMassProperties, activationMode.ordinal()
        );
    }
    @JniBindSelf("""
            self->NotifyShapeChanged(
                BodyID(bodyId),
                Vec3(previousCenterOfMassX, previousCenterOfMassY, previousCenterOfMassZ),
                updateMassProperties, (EActivation) activationMode
            );""")
    private static native void _notifyShapeChanged(
            long _a, int bodyId,
            float previousCenterOfMassX, float previousCenterOfMassY, float previousCenterOfMassZ,
            boolean updateMassProperties, int activationMode
    );

    public short getObjectLayer(int bodyId) { return _getObjectLayer(address, bodyId); }
    @JniBindSelf("return self->GetObjectLayer(BodyID(bodyId));")
    private static native short _getObjectLayer(long _a, int bodyId);

    public void setObjectLayer(int bodyId, short layer) { _setObjectLayer(address, bodyId, layer); }
    @JniBindSelf("self->SetObjectLayer(BodyID(bodyId), (ObjectLayer) layer);")
    private static native void _setObjectLayer(long _a, int bodyId, short layer);

    public void setPositionAndRotationSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode) {
        _setPositionAndRotationSp(
                address, bodyId,
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                activationMode.ordinal()
        );
    }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            self->SetPositionAndRotation(
                BodyID(bodyId),
                Vec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EActivation) activationMode
            );
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _setPositionAndRotationSp(
            long _a, int bodyId,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int activationMode
    );

    public void setPositionAndRotationDp(int bodyId, JtVec3d position, JtQuat rotation, Activation activationMode) {
        _setPositionAndRotationDp(
                address, bodyId,
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                activationMode.ordinal()
        );
    }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            self->SetPositionAndRotation(
                BodyID(bodyId),
                DVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EActivation) activationMode
            );
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _setPositionAndRotationDp(
            long _a, int bodyId,
            double positionX, double positionY, double positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int activationMode
    );

    public void setPositionAndRotationWhenChangedSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setPositionAndRotationWhenChangedDp(int bodyId, JtVec3d position, JtQuat rotation, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void getPositionAndRotationSp(int bodyId, JtVec3f outPosition, JtQuat outRotation) { _getPositionAndRotationSp(address, bodyId, outPosition, outRotation); }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            Vec3 position;
            Quat rotation;
            self->GetPositionAndRotation(BodyID(bodyId), position, rotation);
            ToJavaSp(env, position, outPosition);
            ToJava(env, rotation, outRotation);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionAndRotationSp(long _a, int bodyId, JtVec3f outPosition, JtQuat outRotation);

    public void getPositionAndRotationDp(int bodyId, JtVec3d outPosition, JtQuat outRotation) { _getPositionAndRotationDp(address, bodyId, outPosition, outRotation); }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            DVec3 position;
            Quat rotation;
            self->GetPositionAndRotation(BodyID(bodyId), position, rotation);
            ToJavaDp(env, position, outPosition);
            ToJava(env, rotation, outRotation);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionAndRotationDp(long _a, int bodyId, JtVec3d outPosition, JtQuat outRotation);

    public void setPositionSp(int bodyId, JtVec3f position, Activation activationMode) { _setPositionSp(address, bodyId, position.x, position.y, position.z, activationMode.ordinal()); }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            self->SetPosition(BodyID(bodyId), Vec3(positionX, positionY, positionZ), (EActivation) activationMode);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _setPositionSp(long _a, int bodyId, float positionX, float positionY, float positionZ, int activationMode);

    public void setPositionDp(int bodyId, JtVec3d position, Activation activationMode) { _setPositionDp(address, bodyId, position.x, position.y, position.z, activationMode.ordinal()); }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            self->SetPosition(BodyID(bodyId), DVec3(positionX, positionY, positionZ), (EActivation) activationMode);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _setPositionDp(long _a, int bodyId, double positionX, double positionY, double positionZ, int activationMode);

    public JtVec3f getPositionSp(int bodyId, JtVec3f out) {
        _getPositionSp(address, bodyId, out);
        return out;
    }
    public JtVec3f getPositionSp(int bodyId) { return getPositionSp(bodyId, new JtVec3f()); }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetPosition(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionSp(long _a, int bodyId, JtVec3f out);

    public JtVec3d getPositionDp(int bodyId, JtVec3d out) {
        _getPositionDp(address, bodyId, out);
        return out;
    }
    public JtVec3d getPositionDp(int bodyId) { return getPositionDp(bodyId, new JtVec3d()); }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetPosition(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionDp(long _a, int bodyId, JtVec3d out);

    public JtVec3f getCenterOfMassPositionSp(int bodyId, JtVec3f out) {
        _getCenterOfMassPositionSp(address, bodyId, out);
        return out;
    }
    public JtVec3f getCenterOfMassPositionSp(int bodyId) { return getCenterOfMassPositionSp(bodyId, new JtVec3f()); }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetCenterOfMassPosition(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassPositionSp(long _a, int bodyId, JtVec3f out);

    public JtVec3d getCenterOfMassPositionDp(int bodyId, JtVec3d out) {
        _getCenterOfMassPositionDp(address, bodyId, out);
        return out;
    }
    public JtVec3d getCenterOfMassPositionDp(int bodyId) { return getCenterOfMassPositionDp(bodyId, new JtVec3d()); }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetCenterOfMassPosition(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassPositionDp(long _a, int bodyId, JtVec3d out);

    public void setRotation(int bodyId, JtQuat rotation, Activation activationMode) { _setRotation(address, bodyId, rotation.x, rotation.y, rotation.z, rotation.w, activationMode.ordinal()); }
    @JniBindSelf("self->SetRotation(BodyID(bodyId), Quat(rotationX, rotationY, rotationZ, rotationW), (EActivation) activationMode);")
    private static native void _setRotation(long _a, int bodyId, float rotationX, float rotationY, float rotationZ, float rotationW, int activationMode);

    public JtQuat getRotation(int bodyId, JtQuat out) {
        _getRotation(address, bodyId, out);
        return out;
    }
    public JtQuat getRotation(int bodyId) { return getRotation(bodyId, new JtQuat()); }
    @JniBindSelf("ToJava(env, self->GetRotation(BodyID(bodyId)), out);")
    private static native void _getRotation(long _a, int bodyId, JtQuat out);

    public JtMat44f getWorldTransformSp(int bodyId, JtMat44f out) {
        _getWorldTransformSp(address, bodyId, out);
        return out;
    }
    public JtMat44f getWorldTransformSp(int bodyId) { return getWorldTransformSp(bodyId, new JtMat44f()); }
    @JniBindSelf("""        
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetWorldTransform(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getWorldTransformSp(long _a, int bodyId, JtMat44f out);

    public JtMat44d getWorldTransformDp(int bodyId, JtMat44d out) {
        _getWorldTransformDp(address, bodyId, out);
        return out;
    }
    public JtMat44d getWorldTransformDp(int bodyId) { return getWorldTransformDp(bodyId, new JtMat44d()); }
    @JniBindSelf("""        
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetWorldTransform(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getWorldTransformDp(long _a, int bodyId, JtMat44d out);

    public JtMat44f getCenterOfMassTransformSp(int bodyId, JtMat44f out) {
        _getCenterOfMassTransformSp(address, bodyId, out);
        return out;
    }
    public JtMat44f getCenterOfMassTransformSp(int bodyId) { return getCenterOfMassTransformSp(bodyId, new JtMat44f()); }
    @JniBindSelf("""        
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->GetCenterOfMassTransform(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassTransformSp(long _a, int bodyId, JtMat44f out);

    public JtMat44d getCenterOfMassTransformDp(int bodyId, JtMat44d out) {
        _getCenterOfMassTransformDp(address, bodyId, out);
        return out;
    }
    public JtMat44d getCenterOfMassTransformDp(int bodyId) { return getCenterOfMassTransformDp(bodyId, new JtMat44d()); }
    @JniBindSelf("""        
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->GetCenterOfMassTransform(BodyID(bodyId)), out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getCenterOfMassTransformDp(long _a, int bodyId, JtMat44d out);

    public void moveKinematicSp(int bodyId, JtVec3f targetPosition, JtQuat targetRotation, float deltaTime) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void moveKinematicDp(int bodyId, JtVec3d targetPosition, JtQuat targetRotation, float deltaTime) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setLinearAndAngularVelocity(int bodyId, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void getLinearAndAngularVelocity(int bodyId, JtVec3f outLinearVelocity, JtVec3f outAngularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setLinearVelocity(int bodyId, JtVec3f linearVelocity) { _setLinearVelocity(address, bodyId, linearVelocity.x, linearVelocity.y, linearVelocity.z); }
    @JniBindSelf("self->SetLinearVelocity(BodyID(bodyId), Vec3(linearVelocityX, linearVelocityY, linearVelocityZ));")
    private static native void _setLinearVelocity(long _a, int bodyId, float linearVelocityX, float linearVelocityY, float linearVelocityZ);

    public JtVec3f getLinearVelocity(int bodyId, JtVec3f out) {
        _getLinearVelocity(address, bodyId, out);
        return out;
    }
    public JtVec3f getLinearVelocity(int bodyId) { return getLinearVelocity(bodyId, new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->GetLinearVelocity(BodyID(bodyId)), out);")
    private static native void _getLinearVelocity(long _a, int bodyId, JtVec3f out);

    public void addLinearVelocity(int bodyId, JtVec3f linearVelocity) { _addLinearVelocity(address, bodyId, linearVelocity.x, linearVelocity.y, linearVelocity.z); }
    @JniBindSelf("self->AddLinearVelocity(BodyID(bodyId), Vec3(linearVelocityX, linearVelocityY, linearVelocityZ));")
    private static native void _addLinearVelocity(long _a, int bodyId, float linearVelocityX, float linearVelocityY, float linearVelocityZ);

    public void addLinearAndAngularVelocity(int bodyId, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        _addLinearAndAngularVelocity(
                address, bodyId,
                linearVelocity.x, linearVelocity.y, linearVelocity.z,
                angularVelocity.x, angularVelocity.y, angularVelocity.z
        );
    }
    @JniBindSelf("""
            self->AddLinearAndAngularVelocity(
                BodyID(bodyId),
                Vec3(linearVelocityX, linearVelocityY, linearVelocityZ),
                Vec3(angularVelocityX, angularVelocityY, angularVelocityZ)
            );""")
    private static native void _addLinearAndAngularVelocity(
            long _a, int bodyId,
            float linearVelocityX, float linearVelocityY, float linearVelocityZ,
            float angularVelocityX, float angularVelocityY, float angularVelocityZ
    );

    public void setAngularVelocity(int bodyId, JtVec3f linearVelocity) { _setAngularVelocity(address, bodyId, linearVelocity.x, linearVelocity.y, linearVelocity.z); }
    @JniBindSelf("self->SetAngularVelocity(BodyID(bodyId), Vec3(linearVelocityX, linearVelocityY, linearVelocityZ));")
    private static native void _setAngularVelocity(long _a, int bodyId, float linearVelocityX, float linearVelocityY, float linearVelocityZ);

    public JtVec3f getAngularVelocity(int bodyId, JtVec3f out) {
        _getAngularVelocity(address, bodyId, out);
        return out;
    }
    public JtVec3f getAngularVelocity(int bodyId) { return getAngularVelocity(bodyId, new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->GetAngularVelocity(BodyID(bodyId)), out);")
    private static native void _getAngularVelocity(long _a, int bodyId, JtVec3f out);

    public JtVec3f getPointVelocitySp(int bodyId, JtVec3f point, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    public JtVec3f getPointVelocityDp(int bodyId, JtVec3d point, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setPositionRotationAndVelocitySp(int bodyId, JtVec3f position, JtQuat rotation, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setPositionRotationAndVelocityDp(int bodyId, JtVec3d position, JtQuat rotation, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addForce(int bodyId, JtVec3f force) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addForceSp(int bodyId, JtVec3f force, JtVec3f point) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addForceDp(int bodyId, JtVec3f force, JtVec3d point) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addTorque(int bodyId, JtVec3f torque) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addForceAndTorque(int bodyId, JtVec3f force, JtVec3f torque) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addImpulse(int bodyId, JtVec3f impulse) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addImpulseSp(int bodyId, JtVec3f impulse, JtVec3f point) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addImpulseDp(int bodyId, JtVec3f impulse, JtVec3d point) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void addAngularImpulse(int bodyId, JtVec3f angularImpulse) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setMotionType(int bodyId, MotionType motionType, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    public MotionType getMotionType(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setMotionQuality(int bodyId, MotionQuality motionQuality) {
        throw new UnimplementedMethodException(); // TODO
    }

    public MotionQuality getMotionQuality(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public JtMat44f getInverseInertia(int bodyId, JtMat44f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setRestitution(int bodyId, float restitution) {
        throw new UnimplementedMethodException(); // TODO
    }

    public float getRestitution(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setFriction(int bodyId, float friction) {
        throw new UnimplementedMethodException(); // TODO
    }

    public float getFriction(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void setGravityFactor(int bodyId, float gravityFactor) {
        throw new UnimplementedMethodException(); // TODO
    }

    public float getGravityFactor(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public TransformedShape getTransformedShape(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public long getUserData(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public PhysicsMaterial getMaterial(int bodyId, int subShapeId) {
        throw new UnimplementedMethodException(); // TODO
    }

    public void invalidateContactCache(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }
}
