package jolt.physics.body;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.Activation;

@JniInclude("<Jolt/Physics/Body/BodyInterface.h>")
@JniTypeMapping("BodyInterface")
public final class BodyInterface extends JoltNative {
    private static final String OUT_OF_BODIES = "Out of bodies";

    private BodyInterface(long address) { super(address); }
    public static BodyInterface ref(long address) { return address == 0 ? null : new BodyInterface(address); }

    // add/remove

    public Body createBody(BodyCreationSettings settings) {
        long body = _createBody(address, settings.getAddress());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return Body.ref(body);
    }
    @JniBindSelf("return (jlong) self->CreateBody(*((BodyCreationSettings*) settings));")
    private static native long _createBody(long _a, long settings);

    public void destroyBody(int bodyId) { _destroyBody(address, bodyId); }
    @JniBindSelf("self->DestroyBody(BodyID(bodyId));")
    private static native void _destroyBody(long _a, int bodyId);

    public void addBody(int bodyId, Activation activationMode) { _addBody(address, bodyId, activationMode.ordinal()); }
    @JniBindSelf("return self->AddBody(BodyID(bodyId), (EActivation) activationMode);")
    private static native void _addBody(long _a, int bodyId, long activationMode);

    public void removeBody(int bodyId) { _removeBody(address, bodyId); }
    @JniBindSelf("self->RemoveBody(BodyID(bodyId));")
    private static native void _removeBody(long _a, int bodyId);

    public int createAndAddBody(BodyCreationSettings settings, Activation activationMode) {
        int res = _createAndAddBody(address, settings.getAddress(), activationMode.ordinal());
        if (!BodyIds.isValid(res)) throw new IllegalStateException(OUT_OF_BODIES);
        return res;
    }
    @JniBindSelf("""
            return (jint) self->CreateAndAddBody(*((BodyCreationSettings*) settings), (EActivation) activationMode)
                .GetIndexAndSequenceNumber();""")
    private static native int _createAndAddBody(long _a, long settings, int activationMode);

    // activation

    public boolean isActive(int bodyId) { return _isActive(address, bodyId); }
    @JniBindSelf("return self->IsActive(BodyID(bodyId));")
    private static native boolean _isActive(long _a, int bodyId);

    public void activateBody(int bodyId) { _activateBody(address, bodyId); }
    @JniBindSelf("self->ActivateBody(BodyID(bodyId));")
    private static native void _activateBody(long _a, int bodyId);

    public void deactivateBody(int bodyId) { _deactivateBody(address, bodyId); }
    @JniBindSelf("self->DeactivateBody(BodyID(bodyId));")
    private static native void _deactivateBody(long _a, int bodyId);

    // transform
    // TODO double-precision

    public void getPositionAndRotationSp(int bodyId, JtVec3f outPosition, JtQuat outRotation) { _getPositionAndRotationSp(address, bodyId, outPosition, outRotation); }
    @JniBindSelf("""
            RVec3 position;
            Quat rotation;
            self->GetPositionAndRotation(BodyID(bodyId), position, rotation);
            ToJava(env, position, outPosition);
            ToJava(env, rotation, outRotation);""")
    private static native void _getPositionAndRotationSp(long _a, int bodyId, JtVec3f outPosition, JtQuat outRotation);

    public void getPositionAndRotationDp(int bodyId, JtVec3d outPosition, JtQuat outRotation) { _getPositionAndRotationDp(address, bodyId, outPosition, outRotation); }
    @JniBindSelf("""
            RVec3 position;
            Quat rotation;
            self->GetPositionAndRotation(BodyID(bodyId), position, rotation);
            ToJava(env, position, outPosition);
            ToJava(env, rotation, outRotation);""")
    private static native void _getPositionAndRotationDp(long _a, int bodyId, JtVec3d outPosition, JtQuat outRotation);

    public void setPositionAndRotationSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode) {
        _setPositionAndRotationSp(
                address, bodyId,
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                activationMode.ordinal()
        );
    }
    @JniBindSelf("""
            self->SetPositionAndRotation(
                BodyID(bodyId),
                RVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EActivation) activationMode
            );""")
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
            self->SetPositionAndRotation(
                BodyID(bodyId),
                RVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EActivation) activationMode
            );""")
    private static native void _setPositionAndRotationDp(
            long _a, int bodyId,
            double positionX, double positionY, double positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int activationMode
    );

    // position

    public JtVec3f getPositionSp(int bodyId, JtVec3f out) {
        _getPositionSp(address, bodyId, out);
        return out;
    }
    public JtVec3f getPositionSp(int bodyId) { return getPositionSp(bodyId, new JtVec3f()); }
    @JniBindSelf("ToJava(env, self->GetPosition(BodyID(bodyId)), out);")
    private static native void _getPositionSp(long _a, int bodyId, JtVec3f out);

    public JtVec3d getPositionDp(int bodyId, JtVec3d out) {
        _getPositionDp(address, bodyId, out);
        return out;
    }
    public JtVec3d getPositionDp(int bodyId) { return getPositionDp(bodyId, new JtVec3d()); }
    @JniBindSelf("ToJava(env, self->GetPosition(BodyID(bodyId)), out);")
    private static native void _getPositionDp(long _a, int bodyId, JtVec3d out);

    public void setPositionSp(int bodyId, JtVec3f value, Activation activationMode) { _setPositionSp(address, bodyId, value.x, value.y, value.z, activationMode.ordinal()); }
    @JniBindSelf("self->SetPosition(BodyID(bodyId), RVec3(valueX, valueY, valueZ), (EActivation) activationMode);")
    private static native void _setPositionSp(long _a, int bodyId, float valueX, float valueY, float valueZ, int activationMode);

    public void setPositionDp(int bodyId, JtVec3d value, Activation activationMode) { _setPositionDp(address, bodyId, value.x, value.y, value.z, activationMode.ordinal()); }
    @JniBindSelf("self->SetPosition(BodyID(bodyId), RVec3(valueX, valueY, valueZ), (EActivation) activationMode);")
    private static native void _setPositionDp(long _a, int bodyId, double valueX, double valueY, double valueZ, int activationMode);

    // center of mass

    public JtVec3f getCenterOfMassPositionSp(int bodyId, JtVec3f out) {
        _getCenterOfMassPositionSp(address, bodyId, out);
        return out;
    }
    public JtVec3f getCenterOfMassPositionSp(int bodyId) { return getCenterOfMassPositionSp(bodyId, new JtVec3f()); }
    @JniBindSelf("ToJava(env, self->GetCenterOfMassPosition(BodyID(bodyId)), out);")
    private static native void _getCenterOfMassPositionSp(long _a, int bodyId, JtVec3f out);

    public JtVec3d getCenterOfMassPositionDp(int bodyId, JtVec3d out) {
        _getCenterOfMassPositionDp(address, bodyId, out);
        return out;
    }
    public JtVec3d getCenterOfMassPositionDp(int bodyId) { return getCenterOfMassPositionDp(bodyId, new JtVec3d()); }
    @JniBindSelf("ToJava(env, self->GetCenterOfMassPosition(BodyID(bodyId)), out);")
    private static native void _getCenterOfMassPositionDp(long _a, int bodyId, JtVec3d out);

    // rotation

    public JtQuat getRotation(int bodyId, JtQuat out) {
        _getRotation(address, bodyId, out);
        return out;
    }
    public JtQuat getRotation(int bodyId) { return getRotation(bodyId, new JtQuat()); }
    @JniBindSelf("ToJava(env, self->GetRotation(BodyID(bodyId)), out);")
    private static native void _getRotation(long _a, int bodyId, JtQuat out);

    public void setRotation(int bodyId, JtQuat value, Activation activationMode) { _setRotation(address, bodyId, value.x, value.y, value.z, value.w, activationMode.ordinal()); }
    @JniBindSelf("self->SetRotation(BodyID(bodyId), Quat(valueX, valueY, valueZ, valueW), (EActivation) activationMode);")
    private static native void _setRotation(long _a, int bodyId, float valueX, float valueY, float valueZ, float valueW, int activationMode);

    // velocity

    public JtVec3f getLinearVelocity(int bodyId, JtVec3f into) {
        _getLinearVelocity(address, bodyId, into);
        return into;
    }
    public JtVec3f getLinearVelocity(int bodyId) { return getLinearVelocity(bodyId, new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->GetLinearVelocity(BodyID(bodyId)), into);")
    private static native void _getLinearVelocity(long _a, int bodyId, JtVec3f into);

    public void setLinearVelocity(int bodyId, JtVec3f value) { _setLinearVelocity(address, bodyId, value.x, value.y, value.z); }
    @JniBindSelf("self->SetLinearVelocity(BodyID(bodyId), Vec3(valueX, valueY, valueZ));")
    private static native void _setLinearVelocity(long _a, int bodyId, float valueX, float valueY, float valueZ);

    // advanced
    public Body createBodyWithId(int bodyId, BodyCreationSettings settings) {
        long body = _createBodyWithId(address, bodyId, settings.getAddress());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return Body.ref(body);
    }
    @JniBindSelf("return (jlong) self->CreateBodyWithID(BodyID(bodyId), *((BodyCreationSettings*) settings));")
    private static native long _createBodyWithId(long _a, int bodyId, long settings);

    public Body createBodyWithoutId(BodyCreationSettings settings) { return Body.ref(_createBodyWithoutId(address, settings.getAddress())); }
    @JniBindSelf("return (jlong) self->CreateBodyWithoutID(*((BodyCreationSettings*) settings));")
    private static native long _createBodyWithoutId(long _a, long settings);

    public void destroyBodyWithoutId(Body body) { _destroyBodyWithoutId(address, body.getAddress()); }
    @JniBindSelf("self->DestroyBodyWithoutID((Body*) body);")
    private static native void _destroyBodyWithoutId(long _a, long body);

    public boolean assignBodyId(Body body) { return _assignBodyId0(address, body.getAddress()); }
    @JniBindSelf("return self->AssignBodyID((Body*) body);")
    private static native boolean _assignBodyId0(long _a, long body);

    public boolean assignBodyId(Body body, int bodyId) { return _assignBodyId1(address, body.getAddress(), bodyId); }
    @JniBindSelf("return self->AssignBodyID((Body*) body, BodyID(bodyId));")
    private static native boolean _assignBodyId1(long _a, long body, int bodyId);
}
