package jolt.math;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJava(JNIEnv* env, const Quat from, jobject to) {
            JNI_JtQuat_set(env, to,
                from.GetX(), from.GetY(), from.GetZ(), from.GetW());
        }""")
public final class JtQuat {
    public static final JtQuat IDENTITY = new JtQuat(0f, 0f, 0f, 1f);

    public float x;
    public float y;
    public float z;
    public float w;

    public JtQuat() {}
    public JtQuat(float x, float y, float z, float w) { set(x, y, z, w); }

    @JniCallback
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
