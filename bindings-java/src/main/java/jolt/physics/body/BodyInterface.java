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

    public void destroyBody(BodyId bodyId) { _destroyBody(address, bodyId.getAddress()); }
    @JniBindSelf("self->DestroyBody(*((BodyID*) bodyId));")
    private static native void _destroyBody(long _a, long bodyId);

    public void addBody(BodyId bodyId, Activation activationMode) { _addBody(address, bodyId.getAddress(), activationMode.ordinal()); }
    @JniBindSelf("return self->AddBody(*((BodyID*) bodyId), (EActivation) activationMode);")
    private static native void _addBody(long _a, long bodyId, long activationMode);

    public void removeBody(BodyId bodyId) { _removeBody(address, bodyId.getAddress()); }
    @JniBindSelf("self->RemoveBody(*((BodyID*) bodyId));")
    private static native void _removeBody(long _a, long bodyId);

    public BodyId createAndAddBody(BodyCreationSettings settings, Activation activationMode) {
        long body = _createAndAddBody(address, settings.getAddress(), activationMode.ordinal());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return BodyId.ref(body);
    }
    @JniBindSelf("""
            const BodyID& res = self->CreateAndAddBody(*((BodyCreationSettings*) settings), (EActivation) activationMode);
            return (jlong) &res;""")
    private static native long _createAndAddBody(long _a, long settings, int activationMode);

    // body access
    public boolean isActive(BodyId bodyId) { return _isActive(address, bodyId.getAddress()); }
    @JniBindSelf("return self->IsActive(*((BodyID*) bodyId));")
    private static native boolean _isActive(long _a, long bodyId);

    // TODO double-precision
    public JtVec3f getCenterOfMassPosition(BodyId bodyId) { return _getCenterOfMassPosition(address, bodyId.getAddress()); }
    @JniBindSelf("""
            return JniToJava(
                jniThread.getEnv(),
                self->GetCenterOfMassPosition(*((BodyID*) bodyId))
            );""")
    private static native JtVec3f _getCenterOfMassPosition(long _a, long bodyId);

    public JtVec3f getLinearVelocity(BodyId bodyId) { return _getLinearVelocity(address, bodyId.getAddress()); }
    @JniBindSelf("""
            return JniToJava(
                jniThread.getEnv(),
                self->GetLinearVelocity(*((BodyID*) bodyId))
            );""")
    private static native JtVec3f _getLinearVelocity(long _a, long bodyId);

    public void setLinearVelocity(BodyId bodyId, JtVec3f value) {
        _setLinearVelocity(
                address, bodyId.getAddress(),
                value.x, value.y, value.z
        );
    }
    @JniBindSelf("""
            self->SetLinearVelocity(
                *((BodyID*) bodyId),
                Vec3(valueX, valueY, valueZ)
            );""")
    private static native void _setLinearVelocity(
            long _a, long bodyId,
            float valueX, float valueY, float valueZ
    );

    // advanced
    public Body createBodyWithId(BodyId bodyId, BodyCreationSettings settings) {
        long body = _createBodyWithId(address, bodyId.getAddress(), settings.getAddress());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return Body.ref(body);
    }
    @JniBindSelf("return (jlong) self->CreateBodyWithID(*((BodyID*) bodyId), *((BodyCreationSettings*) settings));")
    private static native long _createBodyWithId(long _a, long bodyId, long settings);

    public Body createBodyWithoutId(BodyCreationSettings settings) { return Body.ref(_createBodyWithoutId(address, settings.getAddress())); }
    @JniBindSelf("return (jlong) self->CreateBodyWithoutID(*((BodyCreationSettings*) settings));")
    private static native long _createBodyWithoutId(long _a, long settings);

    public void destroyBodyWithoutId(Body body) { _destroyBodyWithoutId(address, body.getAddress()); }
    @JniBindSelf("self->DestroyBodyWithoutID((Body*) body);")
    private static native void _destroyBodyWithoutId(long _a, long body);

    public boolean assignBodyId(Body body) { return _assignBodyId0(address, body.getAddress()); }
    @JniBindSelf("return self->AssignBodyID((Body*) body);")
    private static native boolean _assignBodyId0(long _a, long body);

    public boolean assignBodyId(Body body, BodyId bodyId) { return _assignBodyId1(address, body.getAddress(), bodyId.getAddress()); }
    @JniBindSelf("return self->AssignBodyID((Body*) body, (BodyID) bodyId);")
    private static native boolean _assignBodyId1(long _a, long body, long bodyId);
}
