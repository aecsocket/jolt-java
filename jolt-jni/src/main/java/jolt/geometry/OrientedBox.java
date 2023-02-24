package jolt.geometry;

import io.github.aecsocket.jniglue.*;
import jolt.JoltEnvironment;
import jolt.math.JtMat44f;
import jolt.math.JtVec3f;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniInclude("<Jolt/Geometry/OrientedBox.h>")
@JniReferenced
@JniHeader("""
        void ToJava(JNIEnv* env, const OrientedBox from, jobject to) {
            JNI_OrientedBox_set(env, to,
                from.mOrientation(0, 0), from.mOrientation(0, 1), from.mOrientation(0, 2), from.mOrientation(0, 3),
                from.mOrientation(1, 0), from.mOrientation(1, 1), from.mOrientation(1, 2), from.mOrientation(1, 3),
                from.mOrientation(2, 0), from.mOrientation(2, 1), from.mOrientation(2, 2), from.mOrientation(2, 3),
                from.mOrientation(3, 0), from.mOrientation(3, 1), from.mOrientation(3, 2),
                from.mHalfExtents.GetX(), from.mHalfExtents.GetY(), from.mHalfExtents.GetZ());
        }""")
public final class OrientedBox {
    public JtMat44f orientation = JtMat44f.identity();
    public JtVec3f halfExtent = new JtVec3f();

    public OrientedBox() {}
    public OrientedBox(JtMat44f orientation, JtVec3f halfExtent) {
        set(orientation, halfExtent);
    }

    @JniCallback
    public void set(
            float orientation00, float orientation01, float orientation02, float orientation03,
            float orientation10, float orientation11, float orientation12, float orientation13,
            float orientation20, float orientation21, float orientation22, float orientation23,
            float orientation30, float orientation31, float orientation32,
            float halfExtentX, float halfExtentY, float halfExtentZ
    ) {
        orientation.set(
                orientation00, orientation01, orientation02, orientation03,
                orientation10, orientation11, orientation12, orientation13,
                orientation20, orientation21, orientation22, orientation23,
                orientation30, orientation31, orientation32
        );
        halfExtent.set(halfExtentX, halfExtentY, halfExtentZ);
    }

    public void set(JtMat44f orientation, JtVec3f halfExtent) {
        this.orientation.set(orientation);
        this.halfExtent.set(halfExtent);
    }

    public void set(OrientedBox b) { set(b.orientation, b.halfExtent); }

    @Override
    public String toString() {
        return "OrientedBox(" + orientation + ", " + halfExtent + ")";
    }
}
