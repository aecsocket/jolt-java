package jolt.physics.body;

import jolt.jni.JniBind;
import jolt.JoltNative;
import jolt.jni.JniCallback;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Body/BodyActivationListener.h>")
@JniHeader("""
        class BodyActivationListenerImpl : JNINative, BodyActivationListener {
        public:
            BodyActivationListenerImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBodyActivated(const BodyID& inBodyID, uint64 inBodyUserData) override {
                env->CallVoidMethod(obj, BodyActivationListener_onBodyActivated);
            }
            
            void OnBodyDeactivated(const BodyID& inBodyID, uint64 inBodyUserData) override {
                env->CallVoidMethod(obj, BodyActivationListener_onBodyDeactivated);
            }
        };""")
public class BodyActivationListener extends JoltNative {
    private BodyActivationListener(long address) { super(address); }
    public static BodyActivationListener ref(long address) { return address == 0 ? null : new BodyActivationListener(address); }

    public BodyActivationListener() { address = _ctor(); }
    @JniBind("return (long) new BodyActivationListenerImpl(env, obj);")
    private native long _ctor();

    public void onBodyActivated(BodyID bodyID, long bodyUserData) {}
    @JniCallback
    private void _onBodyActivated(long bodyId, long bodyUserData) { onBodyActivated(BodyID.ref(bodyId), bodyUserData); }

    public void onBodyDeactivated(BodyID bodyId, long bodyUserData) {}
    @JniCallback
    private void _onBodyDeactivated(long bodyId, long bodyUserData) { onBodyDeactivated(BodyID.ref(bodyId), bodyUserData); }
}
