package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJava(JNIEnv* env, const RayCastResult from, jobject to) {
            JNI_RayCastResult_set(env, to,
                from.mBodyID.GetIndexAndSequenceNumber(), from.mFraction, from.mSubShapeID2.GetValue());
        }""")
public final class RayCastResult {
    public int bodyId;
    public float fraction;
    public int subShapeId;

    public RayCastResult() {}
    public RayCastResult(int bodyId, float fraction, int subShapeId) {
        set(bodyId, fraction, subShapeId);
    }

    @JniCallback
    public void set(int bodyId, float fraction, int subShapeId) {
        this.bodyId = bodyId;
        this.fraction = fraction;
        this.subShapeId = subShapeId;
    }
}
