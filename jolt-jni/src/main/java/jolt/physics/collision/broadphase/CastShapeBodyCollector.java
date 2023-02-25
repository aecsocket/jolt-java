package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNativeImpl;
import jolt.physics.body.BodyImpl;
import jolt.physics.collision.CollisionCollector;

@JniInclude("""
        <Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>
        <Jolt/Physics/Collision/CastResult.h>""")
@JniReferenced
@JniTypeMapping("CastShapeBodyCollectorImpl")
@JniHeader("""
        class CastShapeBodyCollectorImpl : JNINative, CastShapeBodyCollector {
        public:
            CastShapeBodyCollectorImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBody(const Body &inBody) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_CastShapeBodyCollector_onBody(env, obj,
                    (jlong) &inBody);
            }
            
            void AddHit(const CollisionCollector::ResultType &inResult) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_CastShapeBodyCollector_addHit(env, obj,
                    inResult.mBodyID.GetIndexAndSequenceNumber(), inResult.mFraction);
            }
        };""")
public class CastShapeBodyCollector extends JoltNativeImpl implements CollisionCollector<BroadPhaseCastResult> {
    private CastShapeBodyCollector(long address) { super(address); }
    public static CastShapeBodyCollector ref(long address) { return address == 0 ? null : new CastShapeBodyCollector(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public CastShapeBodyCollector() { address = _ctor(); }
    @JniBind("return (jlong) new CastShapeBodyCollectorImpl(env, obj);")
    private native long _ctor();

    public void onBody(BodyImpl body) {}
    @JniCallback
    private void _onBody(long body) { onBody(BodyImpl.ref(body)); }

    @Override
    public void addHit(BroadPhaseCastResult result) {}
    @JniCallback
    private void _addHit(int bodyId, float fraction) { addHit(new BroadPhaseCastResult(bodyId, fraction)); }
}
