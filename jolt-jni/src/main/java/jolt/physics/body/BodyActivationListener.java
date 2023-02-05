package jolt.physics.body;

import jolt.jni.*;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/Body/BodyActivationListener.h>")
@JniType("BodyActivationListenerImpl")
@JniHeader("""
        class BodyActivationListenerImpl : JNINative, BodyActivationListener {
        public:
            BodyActivationListenerImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBodyActivated(const BodyID& inBodyID, uint64 inBodyUserData) override {
                JNIEnv* env = jniThread.getEnv();
                env->CallVoidMethod(obj, BodyActivationListener_onBodyActivated);
            }
            
            void OnBodyDeactivated(const BodyID& inBodyID, uint64 inBodyUserData) override {
                JNIEnv* env = jniThread.getEnv();
                env->CallVoidMethod(obj, BodyActivationListener_onBodyDeactivated);
            }
        };""")
public class BodyActivationListener extends JoltNative {
    private BodyActivationListener(long address) { super(address); }
    public static BodyActivationListener ref(long address) { return address == 0 ? null : new BodyActivationListener(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public BodyActivationListener() { address = _ctor(); }
    @JniBind("return (jlong) new BodyActivationListenerImpl(env, obj);")
    private native long _ctor();

    public void onBodyActivated(BodyIds bodyID, long bodyUserData) {}
    @JniCallback
    private void _onBodyActivated(long bodyId, long bodyUserData) { onBodyActivated(BodyIds.ref(bodyId), bodyUserData); }

    public void onBodyDeactivated(BodyIds bodyId, long bodyUserData) {}
    @JniCallback
    private void _onBodyDeactivated(long bodyId, long bodyUserData) { onBodyDeactivated(BodyIds.ref(bodyId), bodyUserData); }
}
