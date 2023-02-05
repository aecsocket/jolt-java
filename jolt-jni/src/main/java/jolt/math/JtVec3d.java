package jolt.math;

import jolt.JoltNative;
import jolt.jni.JniHeader;
import jolt.jni.JniInit;
import jolt.jni.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtVec3d;
        jmethodID JtVec3d_set;
        
        void ToJavaDp(JNIEnv* env, const DVec3 from, jobject to) {
            env->CallObjectMethod(to, JtVec3d_set,
                from.GetX(), from.GetY(), from.GetZ());
        }
        
        #ifdef JPH_DOUBLE_PRECISION
        void ToJava(JNIEnv* env, const RVec3 from, jobject to) {
            ToJavaDp(env, from, to);
        }
        #endif""")
@JniInit("""
        JtVec3d = env->FindClass("jolt/math/JtVec3d");
        JtVec3d_set = env->GetMethodID(JtVec3d, "set", "(DDD)V");""")
public class JtVec3d {
    public static final JtVec3d ZERO = new JtVec3d(0.0, 0.0, 0.0);

    public double x;
    public double y;
    public double z;

    public JtVec3d() {}
    public JtVec3d(double x, double y, double z) { set(x, y, z); }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() { return String.format("(%f, %f, %f)", x, y, z); }
}
