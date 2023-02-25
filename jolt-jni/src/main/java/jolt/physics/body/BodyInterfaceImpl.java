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

@JniInclude("<Jolt/Physics/Body/BodyInterface.h>")
@JniTypeMapping("BodyInterface")
final class BodyInterfaceImpl extends JoltNativeImpl implements MutableBodyInterface {
    BodyInterfaceImpl(long address) { super(address); }

    private RuntimeException outOfBodies() { return new RuntimeException("Out of bodies"); }

    private RuntimeException invalidId() { return new RuntimeException("Invalid body ID"); }

    @Override
    public MutableBody createBody(BodyCreationSettings settings) {
        long result = _createBody(address, settings.getAddress());
        if (result == 0) throw outOfBodies();
        return MutableBody.ref(result);
    }
    @JniBindSelf("return (jlong) self->CreateBody(*((BodyCreationSettings*) settings);")
    private static native long _createBody(long _a, long settings);

    @Override
    public MutableBody createBodyWithId(int bodyId, BodyCreationSettings settings) {
        long result = _createBodyWithId(address, bodyId, settings.getAddress());
        if (result == 0) throw invalidId();
        return MutableBody.ref(result);
    }
    @JniBindSelf("return (jlong) self->CreateBodyWithID(BodyID(bodyId), *((BodyCreationSettings*) settings));")
    private static native long _createBodyWithId(long _a, int bodyId, long settings);

    @Override
    public MutableBody createBodyWithoutId(BodyCreationSettings settings) { return MutableBody.ref(_createBodyWithoutId(address, settings.getAddress())); }
    @JniBindSelf("return (jlong) self->CreateBodyWithoutID(*((BodyCreationSettings*) settings);")
    private static native long _createBodyWithoutId(long _a, long settings);

    @Override
    public void destroyBodyWithoutId(MutableBody body) { _destroyBodyWithoutId(address, body.getAddress()); }
    @JniBindSelf("self->DestroyBodyWithoutID((Body*) body);")
    private static native void _destroyBodyWithoutId(long _a, long body);

    @Override
    public boolean assignBodyId(MutableBody body) { return _assignBodyId(address, body.getAddress()); }
    @JniBindSelf("return self->AssignBodyID((Body*) body);")
    private static native boolean _assignBodyId(long _a, long body);

    @Override
    public boolean assignBodyId(MutableBody body, int bodyId) { return _assignBodyID(address, body.getAddress(), bodyId); }
    @JniBindSelf("return self->AssignBodyID((Body*) body, BodyID(bodyId));")
    private static native boolean _assignBodyID(long _a, long body, int bodyId);

    @Override
    public @Nullable MutableBody unassignBodyId(int bodyId) { return MutableBody.ref(_unassignBodyId(address, bodyId)); }
    @JniBindSelf("return (jlong) self->UnassignBodyID(BodyID(bodyId));")
    private static native long _unassignBodyId(long _a, int bodyId);

    @Override
    public MutableBody[] unassignBodyIds(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void destroyBody(int bodyId) { _destroyBody(address, bodyId); }
    @JniBindSelf("self->DestroyBody(BodyID(bodyId));")
    private static native void _destroyBody(long _a, int bodyId);

    @Override
    public void destroyBodies(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addBody(int bodyId, Activation activationMode) { _addBody(address, bodyId, activationMode.ordinal()); }
    @JniBindSelf("self->AddBody(BodyID(bodyId), (EActivation) activationMode);")
    private static native void _addBody(long _a, int bodyId, int activationMode);

    @Override
    public void removeBody(int bodyId) { _removeBody(address, bodyId); }
    @JniBindSelf("self->RemoveBody(BodyID(bodyId));")
    private static native void _removeBody(long _a, int bodyId);

    @Override
    public boolean isAdded(int bodyId) { return _isAdded(address, bodyId); }
    @JniBindSelf("return self->IsAdded(BodyID(bodyId));")
    private static native boolean _isAdded(long _a, int bodyId);

    @Override
    public int createAndAddBody(BodyCreationSettings settings, Activation activationMode) { return _createAndAddBody(address, settings.getAddress(), activationMode.ordinal()); }
    @JniBindSelf("return self->CreateAndAddBody(*((BodyCreationSettings*) settings), (EActivation) activationMode).GetIndexAndSequenceNumber();")
    private static native int _createAndAddBody(long _a, long settings, int activationMode);

    @Override
    public void activateBody(int bodyId) { _activateBody(address, bodyId); }
    @JniBindSelf("self->ActivateBody(BodyID(bodyId));")
    private static native void _activateBody(long _a, int bodyId);

    @Override
    public void activateBodies(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void deactivateBody(int bodyId) { _deactivateBody(address, bodyId); }
    @JniBindSelf("self->DeactivateBody(BodyID(bodyId));")
    private static native void _deactivateBody(long _a, int bodyId);

    @Override
    public void deactivateBodies(int... bodyIds) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public boolean isActive(int bodyId) { return _isActive(address, bodyId); }
    @JniBindSelf("return self->IsActive(BodyID(bodyId));")
    private static native boolean _isActive(long _a, int bodyId);

    @Override
    public void activateConstraint(TwoBodyConstraint constraint) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setShape(int bodyId, Shape shape, boolean updateMassProperties, Activation activationMode) { _setShape(address, bodyId, shape.getAddress(), updateMassProperties, activationMode.ordinal()); }
    @JniBindSelf("self->SetShape(BodyID(bodyId), (Shape*) shape, updateMassProperties, (EActivation) activationMode);")
    private static native void _setShape(long _a, int bodyId, long shape, boolean updateMassProperties, int activationMode);

    @Override
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

    @Override
    public short getObjectLayer(int bodyId) { return _getObjectLayer(address, bodyId); }
    @JniBindSelf("return self->GetObjectLayer(BodyID(bodyId));")
    private static native short _getObjectLayer(long _a, int bodyId);

    @Override
    public void setObjectLayer(int bodyId, short layer) { _setObjectLayer(address, bodyId, layer); }
    @JniBindSelf("self->SetObjectLayer(BodyID(bodyId), (ObjectLayer) layer);")
    private static native void _setObjectLayer(long _a, int bodyId, short layer);

    @Override
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

    @Override
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

    @Override
    public void setPositionAndRotationWhenChangedSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setPositionAndRotationWhenChangedDp(int bodyId, JtVec3d position, JtQuat rotation, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
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
            #endif
            """)
    private static native void _getPositionAndRotationSp(long _a, int bodyId, JtVec3f outPosition, JtQuat outRotation);

    @Override
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
            #endif
            """)
    private static native void _getPositionAndRotationDp(long _a, int bodyId, JtVec3d outPosition, JtQuat outRotation);

    @Override
    public void setPositionSp(int bodyId, JtVec3f position, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setPositionDp(int bodyId, JtVec3d position, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3f getPositionSp(int bodyId, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3d getPositionDp(int bodyId, JtVec3d out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3f getCenterOfMassPositionSp(int bodyId, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3d getCenterOfMassPositionDp(int bodyId, JtVec3d out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setRotation(int bodyId, JtQuat rotation, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtQuat getRotation(int bodyId, JtQuat out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtMat44f getWorldTransformSp(int bodyId, JtMat44f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtMat44d getWorldTransformDp(int bodyId, JtMat44d out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtMat44f getCenterOfMassTransformSp(int bodyId, JtMat44f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtMat44d getCenterOfMassTransformDp(int bodyId, JtMat44d out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void moveKinematicSp(int bodyId, JtVec3f targetPosition, JtQuat targetRotation, float deltaTime) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void moveKinematicDp(int bodyId, JtVec3d targetPosition, JtQuat targetRotation, float deltaTime) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setLinearAndAngularVelocity(int bodyId, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void getLinearAndAngularVelocity(int bodyId, JtVec3f outLinearVelocity, JtVec3f outAngularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setLinearVelocity(int bodyId, JtVec3f linearVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3f getLinearVelocity(int bodyId, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addLinearVelocity(int bodyId, JtVec3f linearVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addLinearAndAngularVelocity(int bodyId, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setAngularVelocity(int bodyId, JtVec3f linearVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3f getAngularVelocity(int bodyId, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3f getPointVelocitySp(int bodyId, JtVec3f point, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtVec3f getPointVelocityDp(int bodyId, JtVec3d point, JtVec3f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setPositionRotationAndVelocitySp(int bodyId, JtVec3f position, JtQuat rotation, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setPositionRotationAndVelocityDp(int bodyId, JtVec3d position, JtQuat rotation, JtVec3f linearVelocity, JtVec3f angularVelocity) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addForce(int bodyId, JtVec3f force) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addForceSp(int bodyId, JtVec3f force, JtVec3f point) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addForceDp(int bodyId, JtVec3f force, JtVec3d point) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addTorque(int bodyId, JtVec3f torque) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addForceAndTorque(int bodyId, JtVec3f force, JtVec3f torque) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addImpulse(int bodyId, JtVec3f impulse) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addImpulseSp(int bodyId, JtVec3f impulse, JtVec3f point) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addImpulseDp(int bodyId, JtVec3f impulse, JtVec3d point) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void addAngularImpulse(int bodyId, JtVec3f angularImpulse) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setMotionType(int bodyId, MotionType motionType, Activation activationMode) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public MotionType getMotionType(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setMotionQuality(int bodyId, MotionQuality motionQuality) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public MotionQuality getMotionQuality(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public JtMat44f getInverseInertia(int bodyId, JtMat44f out) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setRestitution(int bodyId, float restitution) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public float getRestitution(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setFriction(int bodyId, float friction) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public float getFriction(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setGravityFactor(int bodyId, float gravityFactor) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public float getGravityFactor(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public TransformedShape getTransformedShape(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public long getUserData(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public PhysicsMaterial getMaterial(int bodyId, int subShapeId) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void invalidateContactCache(int bodyId) {
        throw new UnimplementedMethodException(); // TODO
    }

    //    // add/remove
//
//    public BodyImpl createBody(BodyCreationSettings settings) {
//        long body = _createBody(address, settings.getAddress());
//        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
//        return BodyImpl.ref(body);
//    }
//    @JniBindSelf("return (jlong) self->CreateBody(*((BodyCreationSettings*) settings));")
//    private static native long _createBody(long _a, long settings);
//
//    public void destroyBody(int bodyId) { _destroyBody(address, bodyId); }
//    @JniBindSelf("self->DestroyBody(BodyID(bodyId));")
//    private static native void _destroyBody(long _a, int bodyId);
//
//    public void addBody(int bodyId, Activation activationMode) { _addBody(address, bodyId, activationMode.ordinal()); }
//    @JniBindSelf("return self->AddBody(BodyID(bodyId), (EActivation) activationMode);")
//    private static native void _addBody(long _a, int bodyId, long activationMode);
//
//    public void removeBody(int bodyId) { _removeBody(address, bodyId); }
//    @JniBindSelf("self->RemoveBody(BodyID(bodyId));")
//    private static native void _removeBody(long _a, int bodyId);
//
//    public boolean isAdded(int bodyId) { return _isAdded(address, bodyId); }
//    @JniBindSelf("return self->IsAdded(BodyID(bodyId));")
//    private static native boolean _isAdded(long _a, int bodyId);
//
//    public int createAndAddBody(BodyCreationSettings settings, Activation activationMode) {
//        int res = _createAndAddBody(address, settings.getAddress(), activationMode.ordinal());
//        if (!BodyIds.isValid(res)) throw new IllegalStateException(OUT_OF_BODIES);
//        return res;
//    }
//    @JniBindSelf("""
//            return (jint) self->CreateAndAddBody(*((BodyCreationSettings*) settings), (EActivation) activationMode)
//                .GetIndexAndSequenceNumber();""")
//    private static native int _createAndAddBody(long _a, long settings, int activationMode);
//
//    // activation
//
//    public boolean isActive(int bodyId) { return _isActive(address, bodyId); }
//    @JniBindSelf("return self->IsActive(BodyID(bodyId));")
//    private static native boolean _isActive(long _a, int bodyId);
//
//    public void activateBody(int bodyId) { _activateBody(address, bodyId); }
//    @JniBindSelf("self->ActivateBody(BodyID(bodyId));")
//    private static native void _activateBody(long _a, int bodyId);
//
//    public void deactivateBody(int bodyId) { _deactivateBody(address, bodyId); }
//    @JniBindSelf("self->DeactivateBody(BodyID(bodyId));")
//    private static native void _deactivateBody(long _a, int bodyId);
//
//    // transform
//
//    public void getPositionAndRotationSp(int bodyId, JtVec3f outPosition, JtQuat outRotation) { _getPositionAndRotationSp(address, bodyId, outPosition, outRotation); }
//    @JniBindSelf("""
//            RVec3 position;
//            Quat rotation;
//            self->GetPositionAndRotation(BodyID(bodyId), position, rotation);
//            ToJava(env, position, outPosition);
//            ToJava(env, rotation, outRotation);""")
//    private static native void _getPositionAndRotationSp(long _a, int bodyId, JtVec3f outPosition, JtQuat outRotation);
//
//    public void getPositionAndRotationDp(int bodyId, JtVec3d outPosition, JtQuat outRotation) { _getPositionAndRotationDp(address, bodyId, outPosition, outRotation); }
//    @JniBindSelf("""
//            RVec3 position;
//            Quat rotation;
//            self->GetPositionAndRotation(BodyID(bodyId), position, rotation);
//            ToJava(env, position, outPosition);
//            ToJava(env, rotation, outRotation);""")
//    private static native void _getPositionAndRotationDp(long _a, int bodyId, JtVec3d outPosition, JtQuat outRotation);
//
//    public void setPositionAndRotationSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode) {
//        _setPositionAndRotationSp(
//                address, bodyId,
//                position.x, position.y, position.z,
//                rotation.x, rotation.y, rotation.z, rotation.w,
//                activationMode.ordinal()
//        );
//    }
//    @JniBindSelf("""
//            self->SetPositionAndRotation(
//                BodyID(bodyId),
//                RVec3(positionX, positionY, positionZ),
//                Quat(rotationX, rotationY, rotationZ, rotationW),
//                (EActivation) activationMode
//            );""")
//    private static native void _setPositionAndRotationSp(
//            long _a, int bodyId,
//            float positionX, float positionY, float positionZ,
//            float rotationX, float rotationY, float rotationZ, float rotationW,
//            int activationMode
//    );
//
//    public void setPositionAndRotationDp(int bodyId, JtVec3d position, JtQuat rotation, Activation activationMode) {
//        _setPositionAndRotationDp(
//                address, bodyId,
//                position.x, position.y, position.z,
//                rotation.x, rotation.y, rotation.z, rotation.w,
//                activationMode.ordinal()
//        );
//    }
//    @JniBindSelf("""
//            self->SetPositionAndRotation(
//                BodyID(bodyId),
//                RVec3(positionX, positionY, positionZ),
//                Quat(rotationX, rotationY, rotationZ, rotationW),
//                (EActivation) activationMode
//            );""")
//    private static native void _setPositionAndRotationDp(
//            long _a, int bodyId,
//            double positionX, double positionY, double positionZ,
//            float rotationX, float rotationY, float rotationZ, float rotationW,
//            int activationMode
//    );
//
//    // position
//
//    public JtVec3f getPositionSp(int bodyId, JtVec3f out) {
//        _getPositionSp(address, bodyId, out);
//        return out;
//    }
//    public JtVec3f getPositionSp(int bodyId) { return getPositionSp(bodyId, new JtVec3f()); }
//    @JniBindSelf("ToJava(env, self->GetPosition(BodyID(bodyId)), out);")
//    private static native void _getPositionSp(long _a, int bodyId, JtVec3f out);
//
//    public JtVec3d getPositionDp(int bodyId, JtVec3d out) {
//        _getPositionDp(address, bodyId, out);
//        return out;
//    }
//    public JtVec3d getPositionDp(int bodyId) { return getPositionDp(bodyId, new JtVec3d()); }
//    @JniBindSelf("ToJava(env, self->GetPosition(BodyID(bodyId)), out);")
//    private static native void _getPositionDp(long _a, int bodyId, JtVec3d out);
//
//    public void setPositionSp(int bodyId, JtVec3f value, Activation activationMode) { _setPositionSp(address, bodyId, value.x, value.y, value.z, activationMode.ordinal()); }
//    @JniBindSelf("self->SetPosition(BodyID(bodyId), RVec3(valueX, valueY, valueZ), (EActivation) activationMode);")
//    private static native void _setPositionSp(long _a, int bodyId, float valueX, float valueY, float valueZ, int activationMode);
//
//    public void setPositionDp(int bodyId, JtVec3d value, Activation activationMode) { _setPositionDp(address, bodyId, value.x, value.y, value.z, activationMode.ordinal()); }
//    @JniBindSelf("self->SetPosition(BodyID(bodyId), RVec3(valueX, valueY, valueZ), (EActivation) activationMode);")
//    private static native void _setPositionDp(long _a, int bodyId, double valueX, double valueY, double valueZ, int activationMode);
//
//    // center of mass
//
//    public JtVec3f getCenterOfMassPositionSp(int bodyId, JtVec3f out) {
//        _getCenterOfMassPositionSp(address, bodyId, out);
//        return out;
//    }
//    public JtVec3f getCenterOfMassPositionSp(int bodyId) { return getCenterOfMassPositionSp(bodyId, new JtVec3f()); }
//    @JniBindSelf("ToJava(env, self->GetCenterOfMassPosition(BodyID(bodyId)), out);")
//    private static native void _getCenterOfMassPositionSp(long _a, int bodyId, JtVec3f out);
//
//    public JtVec3d getCenterOfMassPositionDp(int bodyId, JtVec3d out) {
//        _getCenterOfMassPositionDp(address, bodyId, out);
//        return out;
//    }
//    public JtVec3d getCenterOfMassPositionDp(int bodyId) { return getCenterOfMassPositionDp(bodyId, new JtVec3d()); }
//    @JniBindSelf("ToJava(env, self->GetCenterOfMassPosition(BodyID(bodyId)), out);")
//    private static native void _getCenterOfMassPositionDp(long _a, int bodyId, JtVec3d out);
//
//    // rotation
//
//    public JtQuat getRotation(int bodyId, JtQuat out) {
//        _getRotation(address, bodyId, out);
//        return out;
//    }
//    public JtQuat getRotation(int bodyId) { return getRotation(bodyId, new JtQuat()); }
//    @JniBindSelf("ToJava(env, self->GetRotation(BodyID(bodyId)), out);")
//    private static native void _getRotation(long _a, int bodyId, JtQuat out);
//
//    public void setRotation(int bodyId, JtQuat value, Activation activationMode) { _setRotation(address, bodyId, value.x, value.y, value.z, value.w, activationMode.ordinal()); }
//    @JniBindSelf("self->SetRotation(BodyID(bodyId), Quat(valueX, valueY, valueZ, valueW), (EActivation) activationMode);")
//    private static native void _setRotation(long _a, int bodyId, float valueX, float valueY, float valueZ, float valueW, int activationMode);
//
//    // velocity
//
//    public JtVec3f getLinearVelocity(int bodyId, JtVec3f into) {
//        _getLinearVelocity(address, bodyId, into);
//        return into;
//    }
//    public JtVec3f getLinearVelocity(int bodyId) { return getLinearVelocity(bodyId, new JtVec3f()); }
//    @JniBindSelf("ToJavaSp(env, self->GetLinearVelocity(BodyID(bodyId)), into);")
//    private static native void _getLinearVelocity(long _a, int bodyId, JtVec3f into);
//
//    public void setLinearVelocity(int bodyId, JtVec3f value) { _setLinearVelocity(address, bodyId, value.x, value.y, value.z); }
//    @JniBindSelf("self->SetLinearVelocity(BodyID(bodyId), Vec3(valueX, valueY, valueZ));")
//    private static native void _setLinearVelocity(long _a, int bodyId, float valueX, float valueY, float valueZ);
//
//    // advanced
//    public BodyImpl createBodyWithId(int bodyId, BodyCreationSettings settings) {
//        long body = _createBodyWithId(address, bodyId, settings.getAddress());
//        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
//        return BodyImpl.ref(body);
//    }
//    @JniBindSelf("return (jlong) self->CreateBodyWithID(BodyID(bodyId), *((BodyCreationSettings*) settings));")
//    private static native long _createBodyWithId(long _a, int bodyId, long settings);
//
//    public BodyImpl createBodyWithoutId(BodyCreationSettings settings) { return BodyImpl.ref(_createBodyWithoutId(address, settings.getAddress())); }
//    @JniBindSelf("return (jlong) self->CreateBodyWithoutID(*((BodyCreationSettings*) settings));")
//    private static native long _createBodyWithoutId(long _a, long settings);
//
//    public void destroyBodyWithoutId(BodyImpl body) { _destroyBodyWithoutId(address, body.getAddress()); }
//    @JniBindSelf("self->DestroyBodyWithoutID((Body*) body);")
//    private static native void _destroyBodyWithoutId(long _a, long body);
//
//    public boolean assignBodyId(BodyImpl body) { return _assignBodyId0(address, body.getAddress()); }
//    @JniBindSelf("return self->AssignBodyID((Body*) body);")
//    private static native boolean _assignBodyId0(long _a, long body);
//
//    public boolean assignBodyId(BodyImpl body, int bodyId) { return _assignBodyId1(address, body.getAddress(), bodyId); }
//    @JniBindSelf("return self->AssignBodyID((Body*) body, BodyID(bodyId));")
//    private static native boolean _assignBodyId1(long _a, long body, int bodyId);
}
