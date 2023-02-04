package jolt.physics.body;

import jolt.jni.JniBind;
import jolt.JoltNative;
import jolt.jni.JniCallback;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Body/BodyActivationListener.h>")
public class BodyActivationListener extends JoltNative {
    private BodyActivationListener(long address) { super(address); }
    public static BodyActivationListener ref(long address) { return address == 0 ? null : new BodyActivationListener(address); }

    public BodyActivationListener() { address = _ctor(); }
    @JniBind("return (jlong) new BodyActivationListenerImpl(env, obj);")
    private native long _ctor();

    public void onBodyActivated(BodyID bodyID, long bodyUserData) {}
    @JniCallback
    private void _onBodyActivated(long bodyId, long bodyUserData) { onBodyActivated(BodyID.ref(bodyId), bodyUserData); }

    private void _onBodyDeactivated(long bodyId, long bodyUserData) { onBodyDeactivated(BodyID.ref(bodyId), bodyUserData); }
    @JniCallback
    public void onBodyDeactivated(BodyID bodyID, long bodyUserData) {}
}
