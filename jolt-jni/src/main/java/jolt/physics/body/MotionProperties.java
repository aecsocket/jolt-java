package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.*;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.ShapeSettings;

@JniInclude("<Jolt/Physics/Body/MotionProperties.h>")
@JniType("MotionProperties")
public final class MotionProperties extends JoltNative {
    private MotionProperties(long address) { super(address); }
    public static MotionProperties ref(long address) { return address == 0 ? null : new MotionProperties(address); }

    public void setMassProperties(MassProperties value) {
        JtMat44f inertia = value.inertia;
        _setMassProperties(address, value.mass,
                inertia.n00, inertia.n01, inertia.n02, inertia.n03,
                inertia.n10, inertia.n11, inertia.n12, inertia.n13,
                inertia.n20, inertia.n21, inertia.n22, inertia.n23,
                inertia.n30, inertia.n31, inertia.n32
        );
    }
    @JniBindSelf("""
            MassProperties value;
            value.mMass = mass;
            value.mInertia = Mat44(
                Vec4(inertia00, inertia01, inertia02, inertia03),
                Vec4(inertia10, inertia11, inertia12, inertia13),
                Vec4(inertia20, inertia21, inertia22, inertia23),
                Vec4(inertia30, inertia31, inertia32, 1.0f)
            );
            self->SetMassProperties(value);""")
    private static native void _setMassProperties(
            long _a, float mass,
            float inertia00, float inertia01, float inertia02, float inertia03,
            float inertia10, float inertia11, float inertia12, float inertia13,
            float inertia20, float inertia21, float inertia22, float inertia23,
            float inertia30, float inertia31, float inertia32
    );
}
