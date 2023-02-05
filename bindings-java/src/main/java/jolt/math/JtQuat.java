package jolt.math;

import jolt.JoltNative;
import jolt.jni.JniHeader;
import jolt.jni.JniInit;
import jolt.jni.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtQuat;
        jmethodID JtQuat_set;""")
@JniInit("""
        JtQuat = env->FindClass("jolt/math/JtQuat");
        JtQuat_set = env->GetMethodID(JtQuat, "set", "(FFFF)V");""")
public class JtQuat {
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

    @Override
    public String toString() { return String.format("(%f + %fi + %fj + %fk)", w, x, y, z); }
}
