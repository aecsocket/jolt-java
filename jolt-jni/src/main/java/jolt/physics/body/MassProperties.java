package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.*;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.ShapeSettings;

/**
 * Describes the mass and inertia properties of a body. Used during body construction only.
 */
@JniNative(JoltNative.MODEL)
@JniHeader("""
        jclass JtMassProperties;
        jmethodID JtMassProperties_set;
        
        void ToJava(JNIEnv* env, const MassProperties from, jobject to) {
            env->CallObjectMethod(to, JtMassProperties_set,
                from.mMass,
                from.mInertia(0, 0), from.mInertia(0, 1), from.mInertia(0, 2), from.mInertia(0, 3),
                from.mInertia(1, 0), from.mInertia(1, 1), from.mInertia(1, 2), from.mInertia(1, 3),
                from.mInertia(2, 0), from.mInertia(2, 1), from.mInertia(2, 2), from.mInertia(2, 3),
                from.mInertia(3, 0), from.mInertia(3, 1), from.mInertia(3, 2)
            );
        }""")
@JniInit("""
        JtMassProperties = env->FindClass("jolt/physics/body/MassProperties");
        JtMassProperties_set = env->GetMethodID(JtMassProperties, "set", "(FFFFFFFFFFFFFFFF)V");""")
public final class MassProperties {
    public float mass = 0.0f;
    public JtMat44f inertia = JtMat44f.ZERO;

    public MassProperties() {}
    public MassProperties(float mass, JtMat44f inertia) {
        set(mass, inertia);
    }

    public void set(
            float mass,
            float inertia00, float inertia01, float inertia02, float inertia03,
            float inertia10, float inertia11, float inertia12, float inertia13,
            float inertia20, float inertia21, float inertia22, float inertia23,
            float inertia30, float inertia31, float inertia32
    ) {
        this.mass = mass;
        inertia.set(
                inertia00, inertia01, inertia02, inertia03,
                inertia10, inertia11, inertia12, inertia13,
                inertia20, inertia21, inertia22, inertia23,
                inertia30, inertia31, inertia32
        );
    }

    public void set(float mass, JtMat44f inertia) {
        this.mass = mass;
        this.inertia.set(inertia);
    }

    public void set(MassProperties p) { set(p.mass, p.inertia); }
}
