package jolt.math;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniInit;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtMat44d;
        jmethodID JtMat44d_set;
        
        void ToJavaDp(JNIEnv* env, const DMat44 from, jobject to) {
            auto ax = from.GetAxisX();
            auto ay = from.GetAxisY();
            auto az = from.GetAxisZ();
            auto tl = from.GetTranslation();
            env->CallObjectMethod(to, JtMat44d_set,
                ax.GetX(), ay.GetX(), az.GetX(), tl.GetX(),
                ax.GetY(), ay.GetY(), az.GetY(), tl.GetY(),
                ax.GetZ(), ay.GetZ(), az.GetZ(), tl.GetZ(),
                0.0f, 0.0f, 0.0f
            );
        }
        
        #ifdef JPH_DOUBLE_PRECISION
        void ToJava(JNIEnv* env, const RMat44 from, jobject to) {
            ToJavaDp(env, from, to);
        }
        #endif""")
@JniInit("""
        JtMat44d = env->FindClass("jolt/math/JtMat44d");
        JtMat44d_set = env->GetMethodID(JtMat44d, "set", "(FFFDFFFDFFFDFFF)V");""")
public final class JtMat44d {
    public static final JtMat44d IDENTITY = new JtMat44d(
            1f, 0f, 0f, 0.0,
            0f, 1f, 0f, 0.0,
            0f, 0f, 1f, 0.0,
            0f, 0f, 0f
    );

    public float n00; public float n01; public float n02; public double n03;
    public float n10; public float n11; public float n12; public double n13;
    public float n20; public float n21; public float n22; public double n23;
    public float n30; public float n31; public float n32; // n33 = 1

    public JtMat44d() {}
    public JtMat44d(
            float n00, float n01, float n02, double n03,
            float n10, float n11, float n12, double n13,
            float n20, float n21, float n22, double n23,
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
            float n00, float n01, float n02, double n03,
            float n10, float n11, float n12, double n13,
            float n20, float n21, float n22, double n23,
            float n30, float n31, float n32
    ) {
        this.n00 = n00; this.n01 = n01; this.n02 = n02; this.n03 = n03;
        this.n10 = n10; this.n11 = n11; this.n12 = n12; this.n13 = n13;
        this.n20 = n20; this.n21 = n21; this.n22 = n22; this.n23 = n23;
        this.n30 = n30; this.n31 = n31; this.n32 = n32;
    }

    public void set(JtMat44d m) {
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
                Mat44d(
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
