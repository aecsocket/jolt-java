package jolt.physics.body;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Body/BodyActivationListener.h>")
@JniReferenced
@JniTypeMapping("BodyActivationListenerImpl")
@JniHeader("""
        class BodyActivationListenerImpl : JNINative, BodyActivationListener {
        public:
            BodyActivationListenerImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBodyActivated(const BodyID& inBodyID, uint64 inBodyUserData) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_BodyActivationListener_onBodyActivated(env, obj,
                    inBodyID.GetIndexAndSequenceNumber(), inBodyUserData);
            }
            
            void OnBodyDeactivated(const BodyID& inBodyID, uint64 inBodyUserData) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_BodyActivationListener_onBodyDeactivated(env, obj,
                    inBodyID.GetIndexAndSequenceNumber(), inBodyUserData);
            }
        };""")
public class BodyActivationListener extends JoltNativeImpl {
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

    public void onBodyActivated(int bodyId, long bodyUserData) {}
    @JniCallback
    private void _onBodyActivated(int bodyId, long bodyUserData) { onBodyActivated(bodyId, bodyUserData); }

    public void onBodyDeactivated(int bodyId, long bodyUserData) {}
    @JniCallback
    private void _onBodyDeactivated(int bodyId, long bodyUserData) { onBodyDeactivated(bodyId, bodyUserData); }
}
