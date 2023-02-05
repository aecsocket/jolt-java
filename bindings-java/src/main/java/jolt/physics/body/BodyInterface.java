package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniBindSelf;
import jolt.jni.JniType;
import jolt.math.JtVec3f;
import jolt.physics.Activation;

@JniInclude("<Jolt/Physics/Body/BodyInterface.h>")
@JniType("BodyInterface")
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



    // TODO double-precision
    public JtVec3f getCenterOfMassPositionSp(int bodyId) { return _getCenterOfMassPositionSp(address, bodyId); }
    @JniBindSelf("return ToJava(env, self->GetCenterOfMassPosition(BodyID(bodyId)));")
    private static native JtVec3f _getCenterOfMassPositionSp(long _a, int bodyId);

    public JtVec3f getLinearVelocity(int bodyId) { return _getLinearVelocity(address, bodyId); }
    @JniBindSelf("return ToJava(env, self->GetLinearVelocity(BodyID(bodyId)));")
    private static native JtVec3f _getLinearVelocity(long _a, int bodyId);

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
