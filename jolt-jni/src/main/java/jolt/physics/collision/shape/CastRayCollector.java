package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;
import jolt.physics.body.Body;
import jolt.physics.collision.CollisionCollector;
import jolt.physics.collision.RayCastResult;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniReferenced
@JniTypeMapping("CastRayCollectorImpl")
@JniHeader("""
        class CastRayCollectorImpl : JNINative, CastRayCollector {
        public:
            CastRayCollectorImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            void OnBody(const Body &inBody) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_CastRayCollector_onBody(env, obj,
                    (jlong) &inBody);
            }
            
            void AddHit(const RayCastResult &inResult) override {
                JNIEnv* env = jniThread.getEnv();
                JNI_CastRayCollector_addHit(env, obj,
                    inResult.mBodyID.GetIndexAndSequenceNumber(), inResult.mFraction, inResult.mSubShapeID2.GetValue());
            }
        };""")
public class CastRayCollector extends JoltNative implements CollisionCollector<RayCastResult> {
    private CastRayCollector(long address) { super(address); }
    public static CastRayCollector ref(long address) { return address == 0 ? null : new CastRayCollector(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public CastRayCollector() { address = _ctor(); }
    @JniBind("return (jlong) new CastRayCollectorImpl(env, obj);")
    private native long _ctor();

    public void onBody(Body body) {}
    @JniCallback
    private void _onBody(long body) { onBody(Body.ref(body)); }

    @Override
    public void addHit(RayCastResult result) {}
    @JniCallback
    private void _addHit(int bodyId, float fraction, int subShapeId) { addHit(new RayCastResult(bodyId, fraction, subShapeId)); }
}
