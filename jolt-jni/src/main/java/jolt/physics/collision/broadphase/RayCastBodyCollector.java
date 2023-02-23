package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;
import jolt.physics.body.Body;
import jolt.physics.collision.CollisionCollector;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>")
@JniReferenced
@JniTypeMapping("RayCastBodyCollectorImpl")
@JniHeader("""
        class RayCastBodyCollectorImpl : JNINative, RayCastBodyCollector {
        public:
            RayCastBodyCollectorImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBody(const Body &inBody) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_RayCastBodyCollector_onBody(env, obj,
                    (jlong) &inBody);
            }
            
            void AddHit(const ResultType &inResult) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_RayCastBodyCollector_addHit(env, obj,
                    (jlong) &inResult);
            }
        };""")
public class RayCastBodyCollector extends JoltNative implements CollisionCollector<BroadPhaseCastResult> {
    private RayCastBodyCollector(long address) { super(address); }
    public static RayCastBodyCollector ref(long address) { return address == 0 ? null : new RayCastBodyCollector(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public RayCastBodyCollector() { address = _ctor(); }
    @JniBind("return (jlong) new RayCastBodyCollectorImpl(env, obj);")
    private native long _ctor();

    public void onBody(Body body) {}
    @JniCallback
    private void _onBody(long body) { onBody(Body.ref(body)); }

    @Override
    public void addHit(BroadPhaseCastResult result) {}
    @JniCallback
    private void _addHit(long result) { addHit(BroadPhaseCastResult.ref(result)); }
}
