package jolt.physics.body;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.ShapeSettings;

@JniInclude("<Jolt/Physics/Body/BodyCreationSettings.h>")
@JniTypeMapping("BodyCreationSettings")
public final class BodyCreationSettings extends JoltNative {
    private BodyCreationSettings(long address) { super(address); }
    public static BodyCreationSettings ref(long address) { return address == 0 ? null : new BodyCreationSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public static BodyCreationSettings sp(
            ShapeSettings shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        return new BodyCreationSettings(_sp0(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        ));
    }
    @JniBind("""
            return (jlong) new BodyCreationSettings(
                (ShapeSettings*) shape,
                RVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );""")
    private static native long _sp0(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );

    public static BodyCreationSettings sp(
            Shape shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        return new BodyCreationSettings(_sp1(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        ));
    }
    @JniBind("""
            auto* abc = new BodyCreationSettings(
                (Shape*) shape,
                RVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );
            return (jlong) abc;""")
    private static native long _sp1(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );

    public static BodyCreationSettings dp(
            ShapeSettings shape,
            JtVec3d position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        return new BodyCreationSettings(_dp0(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        ));
    }
    @JniBind("""
            return (jlong) new BodyCreationSettings(
                (ShapeSettings*) shape,
                RVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );""")
    private static native long _dp0(
            long shape,
            double positionX, double positionY, double positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );

    public static BodyCreationSettings dp(
            Shape shape,
            JtVec3d position,
            JtQuat rotation,
            MotionType motionType,
            int objectLayer
    ) {
        return new BodyCreationSettings(_dp1(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        ));
    }
    @JniBind("""
            return (jlong) new BodyCreationSettings(
                (Shape*) shape,
                RVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );""")
    private static native long _dp1(
            long shape,
            double positionX, double positionY, double positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            int objectLayer
    );

    public ShapeSettings getShapeSettings() { return ShapeSettings.ref(_getShapeSettings(address)); }
    @JniBindSelf("return (jlong) self->GetShapeSettings();")
    private static native long _getShapeSettings(long _a);

    public void setShapeSettings(ShapeSettings shape) { _setShapeSettings(address, shape.getAddress()); }
    @JniBindSelf("self->SetShapeSettings((ShapeSettings*) shape);")
    private static native void _setShapeSettings(long _a, long shape);

    public Shape getShape() { return Shape.ref(_getShape(address)); }
    @JniBindSelf("return (jlong) self->GetShape();")
    private static native long _getShape(long _a);

    public void setShape(Shape shape) { _setShape(address, shape.getAddress()); }
    @JniBindSelf("self->SetShape((Shape*) shape);")
    private static native void _setShape(long _a, long shape);

    public boolean hasMassProperties() { return _hasMassProperties(address); }
    @JniBindSelf("return self->HasMassProperties();")
    private static native boolean _hasMassProperties(long _a);

    public MassProperties getMassProperties(MassProperties out) {
        _getMassProperties(address, out);
        return out;
    }
    public MassProperties getMassProperties() { return getMassProperties(new MassProperties()); }
    @JniBindSelf("ToJava(env, self->GetMassProperties(), out);")
    private static native void _getMassProperties(long _a, MassProperties out);

    public JtVec3f getPositionSp(JtVec3f out) {
        _getPositionSp(address, out);
        return out;
    }
    public JtVec3f getPositionSp() { return getPositionSp(new JtVec3f()); }
    @JniBindSelf("ToJava(env, self->mPosition, out);")
    private static native void _getPositionSp(long _a, JtVec3f out);

    public void setPositionSp(JtVec3f value) { _setPositionSp(address, value.x, value.y, value.z); }
    @JniBindSelf("self->mPosition = RVec3(valueX, valueY, valueZ);")
    private static native void _setPositionSp(long _a, float valueX, float valueY, float valueZ);

    public JtVec3d getPositionDp(JtVec3d out) {
        _getPositionDp(address, out);
        return out;
    }
    public JtVec3d getPositionDp() { return getPositionDp(new JtVec3d()); }
    @JniBindSelf("ToJava(env, self->mPosition, out);")
    private static native void _getPositionDp(long _a, JtVec3d out);

    public JtQuat getRotation(JtQuat out) {
        _getRotation(address, out);
        return out;
    }
    public JtQuat getRotation() { return getRotation(new JtQuat()); }
    @JniBindSelf("ToJava(env, self->mRotation, out);")
    private static native void _getRotation(long _a, JtQuat out);

    public OverrideMassProperties getOverrideMassProperties() { return OverrideMassProperties.values()[_getOverrideMassProperties(address)]; }
    @JniBindSelf("return (jint) self->mOverrideMassProperties;")
    private static native int _getOverrideMassProperties(long _a);

    public void setOverrideMassProperties(OverrideMassProperties value) { _setOverrideMassProperties(address, value.ordinal()); }
    @JniBindSelf("self->mOverrideMassProperties = (EOverrideMassProperties) value;")
    private static native void _setOverrideMassProperties(long _a, int value);

    public float getInertiaMultiplier() { return _getInertiaMultiplier(address); }
    @JniBindSelf("return self->mInertiaMultiplier;")
    private static native float _getInertiaMultiplier(long _a);

    public void setInertiaMultiplier(float value) { _setInertiaMultiplier(address, value); }
    @JniBindSelf("self->mInertiaMultiplier = value;")
    private static native void _setInertiaMultiplier(long _a, float value);

    public MassProperties getMassPropertiesOverride(MassProperties out) {
        _getMassPropertiesOverride(address, out);
        return out;
    }
    public MassProperties getMassPropertiesOverride() { return getMassPropertiesOverride(new MassProperties()); }
    @JniBindSelf("ToJava(env, self->mMassPropertiesOverride, out);")
    private static native void _getMassPropertiesOverride(long _a, MassProperties out);

    public void setMassPropertiesOverride(MassProperties value) {
        JtMat44f inertia = value.inertia;
        _setMassPropertiesOverride(
                address, value.mass,
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
            self->mMassPropertiesOverride = value;""")
    private static native void _setMassPropertiesOverride(
            long _a, float mass,
            float inertia00, float inertia01, float inertia02, float inertia03,
            float inertia10, float inertia11, float inertia12, float inertia13,
            float inertia20, float inertia21, float inertia22, float inertia23,
            float inertia30, float inertia31, float inertia32
    );
}
