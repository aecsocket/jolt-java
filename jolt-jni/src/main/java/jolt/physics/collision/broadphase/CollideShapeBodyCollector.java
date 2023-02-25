package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNativeImpl;
import jolt.physics.body.Body;
import jolt.physics.collision.CollisionCollector;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>")
@JniReferenced
@JniTypeMapping("CollideShapeBodyCollectorImpl")
@JniHeader("""
        class CollideShapeBodyCollectorImpl : JNINative, CollideShapeBodyCollector {
        public:
            CollideShapeBodyCollectorImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBody(const Body &inBody) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_CollideShapeBodyCollector_onBody(env, obj,
                    (jlong) &inBody);
            }
            
            void AddHit(const BodyID &inResult) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_CollideShapeBodyCollector_addHit(env, obj,
                    inResult.GetIndexAndSequenceNumber());
            }
        };""")
public class CollideShapeBodyCollector extends JoltNativeImpl implements CollisionCollector<Integer> {
    private CollideShapeBodyCollector(long address) { super(address); }
    public static CollideShapeBodyCollector ref(long address) { return address == 0 ? null : new CollideShapeBodyCollector(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public CollideShapeBodyCollector() { address = _ctor(); }
    @JniBind("return (jlong) new CollideShapeBodyCollectorImpl(env, obj);")
    private native long _ctor();

    public void onBody(Body body) {}
    @JniCallback
    private void _onBody(long body) { onBody(Body.ref(body)); }

    public void addHit(int result) {}
    @Deprecated
    @Override
    public void addHit(Integer result) { addHit((int) result); }
    @JniCallback
    private void _addHit(int result) { addHit(result); }
}
