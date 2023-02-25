package jolt.math;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJavaSp(JNIEnv* env, const Vec3 from, jobject to) {
            JNI_JtVec3f_set(env, to,
                from.GetX(), from.GetY(), from.GetZ());
        }
        
        #ifndef JPH_DOUBLE_PRECISION
        void ToJava(JNIEnv* env, const RVec3 from, jobject to) {
            ToJavaSp(env, from, to);
        }
        #endif""")
public final class JtVec3f {
    public float x;
    public float y;
    public float z;

    public JtVec3f() {}
    public JtVec3f(float x, float y, float z) { set(x, y, z); }

    @JniCallback
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(JtVec3f v) { set(v.x, v.y, v.z); }

    @Override
    public String toString() { return String.format("(%f, %f, %f)", x, y, z); }
}
