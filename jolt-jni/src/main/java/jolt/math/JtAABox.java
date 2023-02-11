package jolt.math;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJava(JNIEnv* env, const AABox from, jobject to) {
            JNI_JtAABox_set(env, to,
                from.mMin.GetX(), from.mMin.GetY(), from.mMin.GetZ(),
                from.mMax.GetX(), from.mMax.GetY(), from.mMax.GetZ());
        }""")
public final class JtAABox {
    public JtVec3f min;
    public JtVec3f max;

    public JtAABox() {}
    public JtAABox(JtVec3f min, JtVec3f max) {
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

    public void set(JtAABox b) { set(b.min, b.max); }

    @Override
    public String toString() {
        return "AABox(" + min + ", " + max + ")";
    }
}
