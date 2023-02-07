package jolt.math;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniInit;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtMat44f;
        jmethodID JtMat44f_set;
        
        void ToJavaSp(JNIEnv* env, const Mat44 from, jobject to) {
            env->CallObjectMethod(to, JtMat44f_set,
                from(0, 0), from(0, 1), from(0, 2), from(0, 3),
                from(1, 0), from(1, 1), from(1, 2), from(1, 3),
                from(2, 0), from(2, 1), from(2, 2), from(2, 3),
                from(3, 0), from(3, 1), from(3, 2), from(3, 3)
            );
        }
        
        #ifndef JPH_DOUBLE_PRECISION
        void ToJava(JNIEnv* env, const RMat44 from, jobject to) {
            ToJavaSp(env, from, to);
        }
        #endif""")
@JniInit("""
        JtMat44f = env->FindClass("jolt/math/JtMat44f");
        JtMat44f_set = env->GetMethodID(JtMat44f, "set", "(FFFFFFFFFFFFFFF)V");""")
public final class JtMat44f {
    public static final JtMat44f IDENTITY = new JtMat44f(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            0f, 0f, 0f
    );
    public static final JtMat44f ZERO = new JtMat44f(
            0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f,
            0f, 0f, 0f, 0f,
            0f, 0f, 0f
    );

    public float n00; public float n01; public float n02; public float n03;
    public float n10; public float n11; public float n12; public float n13;
    public float n20; public float n21; public float n22; public float n23;
    public float n30; public float n31; public float n32; // n33 = 1

    public JtMat44f() {}
    public JtMat44f(
            float n00, float n01, float n02, float n03,
            float n10, float n11, float n12, float n13,
            float n20, float n21, float n22, float n23,
            float n30, float n31, float n32
    ) {
        set(
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32
        );
    }

    public void set(
            float n00, float n01, float n02, float n03,
            float n10, float n11, float n12, float n13,
            float n20, float n21, float n22, float n23,
            float n30, float n31, float n32
    ) {
        this.n00 = n00; this.n01 = n01; this.n02 = n02; this.n03 = n03;
        this.n10 = n10; this.n11 = n11; this.n12 = n12; this.n13 = n13;
        this.n20 = n20; this.n21 = n21; this.n22 = n22; this.n23 = n23;
        this.n30 = n30; this.n31 = n31; this.n32 = n32;
    }

    public void set(JtMat44f m) {
        set(
                m.n00, m.n01, m.n02, m.n03,
                m.n10, m.n11, m.n12, m.n13,
                m.n20, m.n21, m.n22, m.n23,
                m.n30, m.n31, m.n32
        );
    }

    @Override
    public String toString() {
        return String.format("""
                Mat44f(
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f (1)
                )""",
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32
        );
    }
}
