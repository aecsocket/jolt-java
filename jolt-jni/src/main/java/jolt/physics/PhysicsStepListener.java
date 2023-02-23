package jolt.physics;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/PhysicsStepListener.h>")
@JniReferenced
@JniTypeMapping("PhysicsStepListenerImpl")
@JniHeader("""
        class PhysicsStepListenerImpl : JNINative, PhysicsStepListener {
        public:
            PhysicsStepListenerImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnStep(float inDeltaTime, PhysicsSystem &inPhysicsSystem) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_PhysicsStepListener_onStep(env, obj,
                    inDeltaTime, (jlong) &inPhysicsSystem);
            }
        };""")
public class PhysicsStepListener extends JoltNative {
    private PhysicsStepListener(long address) { super(address); }
    public static PhysicsStepListener ref(long address) { return address == 0 ? null : new PhysicsStepListener(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public PhysicsStepListener() { address = _ctor(); }
    @JniBind("return (jlong) new PhysicsStepListenerImpl(env, obj);")
    private native long _ctor();

    public void onStep(float deltaTime, PhysicsSystem physicsSystem) {}
    @JniCallback
    private void _onStep(float deltaTime, long physicsSystem) { onStep(deltaTime, PhysicsSystem.ref(physicsSystem)); }
}
