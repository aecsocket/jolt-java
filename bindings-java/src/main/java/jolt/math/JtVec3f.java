package jolt.math;

import jolt.JoltNative;
import jolt.jni.JniHeader;
import jolt.jni.JniInit;
import jolt.jni.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtVec3f;
        jmethodID JtVec3f_set;
        
        void ToJava(JNIEnv* env, const Vec3 from, jobject to) {
            env->CallObjectMethod(to, JtVec3f_set,
                from.GetX(), from.GetY(), from.GetZ());
        }
        
        jobject ToJava(JNIEnv* env, const Vec3 from) {
            jobject to = env->AllocObject(JtVec3f);
            ToJava(env, from, to);
            return to;
        }""")
@JniInit("""
        JtVec3f = env->FindClass("jolt/math/JtVec3f");
        JtVec3f_set = env->GetMethodID(JtVec3f, "set", "(FFF)V");""")
public class JtVec3f {
    public static final JtVec3f ZERO = new JtVec3f(0f, 0f, 0f);

    public float x;
    public float y;
    public float z;

    public JtVec3f() {}
    public JtVec3f(float x, float y, float z) { set(x, y, z); }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() { return String.format("(%f, %f, %f)", x, y, z); }
}
