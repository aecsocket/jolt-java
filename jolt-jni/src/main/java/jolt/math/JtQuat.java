package jolt.math;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniInit;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtQuat;
        jmethodID JtQuat_set;
        
        void ToJava(JNIEnv* env, const Quat from, jobject to) {
            env->CallObjectMethod(to, JtQuat_set,
                from.GetX(), from.GetY(), from.GetZ(), from.GetW());
        }""")
@JniInit("""
        JtQuat = env->FindClass("jolt/math/JtQuat");
        JtQuat_set = env->GetMethodID(JtQuat, "set", "(FFFF)V");""")
public final class JtQuat {
    public static final JtQuat IDENTITY = new JtQuat(0f, 0f, 0f, 1f);

    public float x;
    public float y;
    public float z;
    public float w;

    public JtQuat() {}
    public JtQuat(float x, float y, float z, float w) { set(x, y, z, w); }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(JtQuat q) { set(q.x, q.y, q.z, q.w); }

    @Override
    public String toString() { return String.format("(%f + %fi + %fj + %fk)", w, x, y, z); }
}
