package jolt.geometry;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;
import jolt.math.JtVec3f;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJava(JNIEnv* env, const AABox from, jobject to) {
            JNI_AABox_set(env, to,
                from.mMin.GetX(), from.mMin.GetY(), from.mMin.GetZ(),
                from.mMax.GetX(), from.mMax.GetY(), from.mMax.GetZ());
        }""")
public final class AABox {
    public JtVec3f min;
    public JtVec3f max;

    public AABox() {}
    public AABox(JtVec3f min, JtVec3f max) {
        set(min, max);
    }

    @JniCallback
    public void set(
            float minX, float minY, float minZ,
            float maxX, float maxY, float maxZ
    ) {
        min.x = minX; min.y = minY; min.z = minZ;
        max.x = maxX; max.y = maxY; max.z = maxZ;
    }

    public void set(JtVec3f min, JtVec3f max) {
        this.min.set(min);
        this.max.set(max);
    }

    public void set(AABox b) { set(b.min, b.max); }

    @Override
    public String toString() {
        return "AABox(" + min + ", " + max + ")";
    }
}
