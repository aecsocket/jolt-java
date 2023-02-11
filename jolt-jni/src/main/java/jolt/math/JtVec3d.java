package jolt.math;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJavaDp(JNIEnv* env, const DVec3 from, jobject to) {
            JNI_JtVec3d_set(env, to,
                from.GetX(), from.GetY(), from.GetZ());
        }
        
        #ifdef JPH_DOUBLE_PRECISION
        void ToJava(JNIEnv* env, const RVec3 from, jobject to) {
            ToJavaDp(env, from, to);
        }
        #endif""")
public final class JtVec3d {
    public static final JtVec3d ZERO = new JtVec3d(0.0, 0.0, 0.0);

    public double x;
    public double y;
    public double z;

    public JtVec3d() {}
    public JtVec3d(double x, double y, double z) { set(x, y, z); }

    @JniCallback
    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(JtVec3d v) { set(v.x, v.y, v.z); }

    @Override
    public String toString() { return String.format("(%f, %f, %f)", x, y, z); }
}
