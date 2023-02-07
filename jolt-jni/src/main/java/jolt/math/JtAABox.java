package jolt.math;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniInit;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtAABox;
        jmethodID JtAABox_set;
        
        void ToJava(JNIEnv* env, const AABox from, jobject to) {
            env->CallObjectMethod(to, JtAABox_set,
                from.mMin.GetX(), from.mMin.GetY(), from.mMin.GetZ(),
                from.mMax.GetX(), from.mMax.GetY(), from.mMax.GetZ()
            );
        }""")
@JniInit("""
        JtAABox = env->FindClass("jolt/math/JtAABox");
        JtAABox_set = env->GetMethodID(JtAABox, "set", "(FFFFFF)V");""")
public final class JtAABox {
    public JtVec3f min;
    public JtVec3f max;

    public JtAABox() {}
    public JtAABox(JtVec3f min, JtVec3f max) {
        set(min, max);
    }

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
