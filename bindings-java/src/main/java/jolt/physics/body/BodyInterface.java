package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniBindSelf;
import jolt.jni.JniType;
import jolt.physics.Activation;

@JniInclude("<Jolt/Physics/Body/BodyInterface.h>")
@JniType("BodyInterface")
public final class BodyInterface extends JoltNative {
    private static final String OUT_OF_BODIES = "Out of bodies";

    private BodyInterface(long address) { super(address); }
    public static BodyInterface ref(long address) { return address == 0 ? null : new BodyInterface(address); }

    public Body createBody(BodyCreationSettings settings) {
        long body = _createBody(address, settings.getAddress());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return Body.ref(body);
    }
    @JniBindSelf("return (long) self->CreateBody(*((BodyCreationSettings*) settings));")
    private static native long _createBody(long address, long settings);

    public Body createBodyWithId(BodyId bodyId, BodyCreationSettings settings) {
        long body = _createBodyWithId(address, bodyId.getAddress(), settings.getAddress());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return Body.ref(body);
    }
    @JniBindSelf("return (long) self->CreateBodyWithID(*((BodyID*) bodyId), *((BodyCreationSettings*) settings));")
    private static native long _createBodyWithId(long address, long bodyId, long settings);

    public void addBody(BodyId bodyId, Activation activationMode) { _addBody(address, bodyId.getAddress(), activationMode.ordinal()); }
    @JniBindSelf("return self->AddBody(*((BodyID*) bodyId), (EActivation) activationMode);")
    private static native void _addBody(long address, long bodyId, long activationMode);

    public BodyId createAndAddBody(BodyCreationSettings settings, Activation activationMode) {
        long body = _createAndAddBody(address, settings.getAddress(), activationMode.ordinal());
        if (body == 0) throw new IllegalStateException(OUT_OF_BODIES);
        return BodyId.ref(body);
    }
    @JniBindSelf("""
            BodyID res = self->CreateAndAddBody(*((BodyCreationSettings*) settings), (EActivation) activationMode);
            return (long) &res;""")
    private static native long _createAndAddBody(long address, long settings, int activationMode);
}
