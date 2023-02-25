package jolt.physics.body;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNativeImpl;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.ShapeSettings;

@JniInclude("<Jolt/Physics/Body/BodyCreationSettings.h>")
@JniTypeMapping("BodyCreationSettings")
public final class BodyCreationSettings extends JoltNativeImpl {
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

    // shape settings
    public BodyCreationSettings(
            ShapeSettings shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            short objectLayer
    ) {
        address = _ctor0Sp(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            #ifndef JPH_DOUBLE_PRECISION
            return (jlong) new BodyCreationSettings(
                (ShapeSettings*) shape,
                Vec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );
            #else
            JniThrow(env, WRONG_PRECISION);
            return (jlong) nullptr;
            #endif""")
    private static native long _ctor0Sp(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            short objectLayer
    );

    public BodyCreationSettings(
            ShapeSettings shape,
            JtVec3d position,
            JtQuat rotation,
            MotionType motionType,
            short objectLayer
    ) {
        address = _ctor0Dp(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            #ifdef JPH_DOUBLE_PRECISION
            return (jlong) new BodyCreationSettings(
                (ShapeSettings*) shape,
                DVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );
            #else
            JniThrow(env, WRONG_PRECISION);
            return (jlong) nullptr;
            #endif""")
    private static native long _ctor0Dp(
            long shape,
            double positionX, double positionY, double positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            short objectLayer
    );

    // shape
    public BodyCreationSettings(
            Shape shape,
            JtVec3f position,
            JtQuat rotation,
            MotionType motionType,
            short objectLayer
    ) {
        address = _ctor1Sp(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            #ifndef JPH_DOUBLE_PRECISION
            return (jlong) new BodyCreationSettings(
                (Shape*) shape,
                Vec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );
            #else
            JniThrow(env, WRONG_PRECISION);
            return (jlong) nullptr;
            #endif""")
    private static native long _ctor1Sp(
            long shape,
            float positionX, float positionY, float positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            short objectLayer
    );

    public BodyCreationSettings(
            Shape shape,
            JtVec3d position,
            JtQuat rotation,
            MotionType motionType,
            short objectLayer
    ) {
        address = _ctor1Dp(
                shape.getAddress(),
                position.x, position.y, position.z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                motionType.ordinal(),
                objectLayer
        );
    }
    @JniBind("""
            #ifdef JPH_DOUBLE_PRECISION
            return (jlong) new BodyCreationSettings(
                (Shape*) shape,
                DVec3(positionX, positionY, positionZ),
                Quat(rotationX, rotationY, rotationZ, rotationW),
                (EMotionType) motionType,
                (ObjectLayer) objectLayer
            );
            #else
            JniThrow(env, WRONG_PRECISION);
            return (jlong) nullptr;
            #endif""")
    private static native long _ctor1Dp(
            long shape,
            double positionX, double positionY, double positionZ,
            float rotationX, float rotationY, float rotationZ, float rotationW,
            int motionType,
            short objectLayer
    );

    public ShapeSettings getShapeSettings() { return ShapeSettings.ref(_getShapeSettings(address)); }
    @JniBindSelf("return (jlong) self->GetShapeSettings();")
    private static native long _getShapeSettings(long _a);

    public void setShapeSettings(ShapeSettings shape) { _setShapeSettings(address, shape.getAddress()); }
    @JniBindSelf("self->SetShapeSettings((ShapeSettings*) shape);")
    private static native void _setShapeSettings(long _a, long shape);
    
    // TODO convertShapeSettings

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
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            ToJavaSp(env, self->mPosition, out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionSp(long _a, JtVec3f out);

    public JtVec3d getPositionDp(JtVec3d out) {
        _getPositionDp(address, out);
        return out;
    }
    public JtVec3d getPositionDp() { return getPositionDp(new JtVec3d()); }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            ToJavaDp(env, self->mPosition, out);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _getPositionDp(long _a, JtVec3d out);

    public void setPositionSp(JtVec3f position) { _setPositionSp(address, position.x, position.y, position.z); }
    @JniBindSelf("""
            #ifndef JPH_DOUBLE_PRECISION
            self->mPosition = Vec3(valueX, valueY, valueZ);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _setPositionSp(long _a, float valueX, float valueY, float valueZ);

    public void setPositionDp(JtVec3d position) { _setPositionDp(address, position.x, position.y, position.z); }
    @JniBindSelf("""
            #ifdef JPH_DOUBLE_PRECISION
            self->mPosition = DVec3(valueX, valueY, valueZ);
            #else
            THROW_WRONG_PRECISION
            #endif""")
    private static native void _setPositionDp(long _a, double valueX, double valueY, double valueZ);

    public JtQuat getRotation(JtQuat out) {
        _getRotation(address, out);
        return out;
    }
    public JtQuat getRotation() { return getRotation(new JtQuat()); }
    @JniBindSelf("ToJava(env, self->mRotation, out);")
    private static native void _getRotation(long _a, JtQuat out);
    
    public void setRotation(JtQuat rotation) { _setRotation(address, rotation.x, rotation.y, rotation.z, rotation.w); }
    @JniBindSelf("self->mRotation = Quat(valueX, valueY, valueZ, valueW);")
    private static native void _setRotation(long _a, float valueX, float valueY, float valueZ, float valueW);
    
    public JtVec3f getLinearVelocity(JtVec3f out) {
        _getLinearVelocity(address, out);
        return out;
    }
    public JtVec3f getLinearVelocity() { return getLinearVelocity(new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->mLinearVelocity, out);")
    private static native void _getLinearVelocity(long _a, JtVec3f out);
    
    public void setLinearVelocity(JtVec3f linearVelocity) { _setLinearVelocity(address, linearVelocity.x, linearVelocity.y, linearVelocity.z); }
    @JniBindSelf("self->mLinearVelocity = Vec3(valueX, valueY, valueZ);")
    private static native void _setLinearVelocity(long _a, float valueX, float valueY, float valueZ);

    public JtVec3f getAngularVelocity(JtVec3f out) {
        _getAngularVelocity(address, out);
        return out;
    }
    public JtVec3f getAngularVelocity() { return getAngularVelocity(new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->mAngularVelocity, out);")
    private static native void _getAngularVelocity(long _a, JtVec3f out);

    public void setAngularVelocity(JtVec3f angularVelocity) { _setAngularVelocity(address, angularVelocity.x, angularVelocity.y, angularVelocity.z); }
    @JniBindSelf("self->mAngularVelocity = Vec3(valueX, valueY, valueZ);")
    private static native void _setAngularVelocity(long _a, float valueX, float valueY, float valueZ);

    public long getUserData() { return _getUserData(address); }
    @JniBindSelf("return self->mUserData;")
    private static native long _getUserData(long _a);

    public void setUserData(long userData) { _setUserData(address, userData); }
    @JniBindSelf("self->mUserData = value;")
    private static native void _setUserData(long _a, long value);

    public short getObjectLayer() { return _getObjectLayer(address); }
    @JniBindSelf("return self->mObjectLayer;")
    private static native short _getObjectLayer(long _a);

    public void setObjectLayer(short layer) { _setObjectLayer(address, layer); }
    @JniBindSelf("self->mObjectLayer = value;")
    private static native void _setObjectLayer(long _a, short value);

    // todo collision group

    public MotionType getMotionType() { return MotionType.values()[_getMotionType(address)]; }
    @JniBindSelf("return (jint) self->mMotionType;")
    private static native int _getMotionType(long _a);

    public void setMotionType(MotionType motionType) { _setMotionType(address, motionType.ordinal()); }
    @JniBindSelf("self->mMotionType = (EMotionType) value;")
    private static native void _setMotionType(long _a, int value);

    public boolean getAllowDynamicOrKinematic() { return _getAllowDynamicOrKinematic(address); }
    @JniBindSelf("return self->mAllowDynamicOrKinematic;")
    private static native boolean _getAllowDynamicOrKinematic(long _a);

    public void setAllowDynamicOrKinematic(boolean allowDynamicOrKinematic) { _setAllowDynamicOrKinematic(address, allowDynamicOrKinematic); }
    @JniBindSelf("self->mAllowDynamicOrKinematic = value;")
    private static native void _setAllowDynamicOrKinematic(long _a, boolean value);

    public boolean isSensor() { return _isSensor(address); }
    @JniBindSelf("return self->mIsSensor;")
    private static native boolean _isSensor(long _a);

    public void setIsSensor(boolean isSensor) { _setIsSensor(address, isSensor); }
    @JniBindSelf("self->mIsSensor = value;")
    private static native void _setIsSensor(long _a, boolean value);

    public boolean getUseManifoldReduction() { return _getUseManifoldReduction(address); }
    @JniBindSelf("return self->mUseManifoldReduction;")
    private static native boolean _getUseManifoldReduction(long _a);

    public void setUseManifoldReduction(boolean useManifoldReduction) { _setUseManifoldReduction(address, useManifoldReduction); }
    @JniBindSelf("self->mUseManifoldReduction = value;")
    private static native void _setUseManifoldReduction(long _a, boolean value);

    public MotionQuality getMotionQuality() { return MotionQuality.values()[_getMotionQuality(address)]; }
    @JniBindSelf("return (jint) self->mMotionQuality;")
    private static native int _getMotionQuality(long _a);

    public void setMotionQuality(MotionQuality motionQuality) { _setMotionQuality(address, motionQuality.ordinal()); }
    @JniBindSelf("self->mMotionQuality = (EMotionQuality) value;")
    private static native void _setMotionQuality(long _a, int value);

    public boolean getAllowSleeping() { return _getAllowSleeping(address); }
    @JniBindSelf("return self->mAllowSleeping;")
    private static native boolean _getAllowSleeping(long _a);

    public void setAllowSleeping(boolean allowSleeping) { _setAllowSleeping(address, allowSleeping); }
    @JniBindSelf("self->mAllowSleeping = value;")
    private static native void _setAllowSleeping(long _a, boolean value);

    public float getFriction() { return _getFriction(address); }
    @JniBindSelf("return self->mFriction;")
    private static native float _getFriction(long _a);

    public void setFriction(float friction) { _setFriction(address, friction); }
    @JniBindSelf("self->mFriction = value;")
    private static native void _setFriction(long _a, float value);

    public float getRestitution() { return _getRestitution(address); }
    @JniBindSelf("return self->mRestitution;")
    private static native float _getRestitution(long _a);

    public void setRestitution(float restitution) { _setRestitution(address, restitution); }
    @JniBindSelf("self->mRestitution = value;")
    private static native void _setRestitution(long _a, float value);

    public float getLinearDamping() { return _getLinearDamping(address); }
    @JniBindSelf("return self->mLinearDamping;")
    private static native float _getLinearDamping(long _a);

    public void setLinearDamping(float linearDamping) { _setLinearDamping(address, linearDamping); }
    @JniBindSelf("self->mLinearDamping = value;")
    private static native void _setLinearDamping(long _a, float value);

    public float getAngularDamping() { return _getAngularDamping(address); }
    @JniBindSelf("return self->mAngularDamping;")
    private static native float _getAngularDamping(long _a);

    public void setAngularDamping(float angularDamping) { _setAngularDamping(address, angularDamping); }
    @JniBindSelf("self->mAngularDamping = value;")
    private static native void _setAngularDamping(long _a, float value);

    public float getMaxLinearVelocity() { return _getMaxLinearVelocity(address); }
    @JniBindSelf("return self->mMaxLinearVelocity;")
    private static native float _getMaxLinearVelocity(long _a);

    public void setMaxLinearVelocity(float maxLinearVelocity) { _setMaxLinearVelocity(address, maxLinearVelocity); }
    @JniBindSelf("self->mMaxLinearVelocity = value;")
    private static native void _setMaxLinearVelocity(long _a, float value);


    public float getMaxAngularVelocity() { return _getMaxAngularVelocity(address); }
    @JniBindSelf("return self->mMaxAngularVelocity;")
    private static native float _getMaxAngularVelocity(long _a);

    public void setMaxAngularVelocity(float maxAngularVelocity) { _setMaxAngularVelocity(address, maxAngularVelocity); }
    @JniBindSelf("self->mMaxAngularVelocity = value;")
    private static native void _setMaxAngularVelocity(long _a, float value);

    public float getGravityFactor() { return _getGravityFactor(address); }
    @JniBindSelf("return self->mGravityFactor;")
    private static native float _getGravityFactor(long _a);

    public void setGravityFactor(float gravityFactor) { _setGravityFactor(address, gravityFactor); }
    @JniBindSelf("self->mGravityFactor = value;")
    private static native void _setGravityFactor(long _a, float value);

    public OverrideMassProperties getOverrideMassProperties() { return OverrideMassProperties.values()[_getOverrideMassProperties(address)]; }
    @JniBindSelf("return (jint) self->mOverrideMassProperties;")
    private static native int _getOverrideMassProperties(long _a);

    public void setOverrideMassProperties(OverrideMassProperties overrideMassProperties) { _setOverrideMassProperties(address, overrideMassProperties.ordinal()); }
    @JniBindSelf("self->mOverrideMassProperties = (EOverrideMassProperties) value;")
    private static native void _setOverrideMassProperties(long _a, int value);

    public float getInertiaMultiplier() { return _getInertiaMultiplier(address); }
    @JniBindSelf("return self->mInertiaMultiplier;")
    private static native float _getInertiaMultiplier(long _a);

    public void setInertiaMultiplier(float inertiaMultiplier) { _setInertiaMultiplier(address, inertiaMultiplier); }
    @JniBindSelf("self->mInertiaMultiplier = value;")
    private static native void _setInertiaMultiplier(long _a, float value);

    public MassProperties getMassPropertiesOverride(MassProperties out) {
        _getMassPropertiesOverride(address, out);
        return out;
    }
    public MassProperties getMassPropertiesOverride() { return getMassPropertiesOverride(new MassProperties()); }
    @JniBindSelf("ToJava(env, self->mMassPropertiesOverride, out);")
    private static native void _getMassPropertiesOverride(long _a, MassProperties out);

    public void setMassPropertiesOverride(MassProperties massPropertiesOverride) {
        JtMat44f inertia = massPropertiesOverride.inertia;
        _setMassPropertiesOverride(
                address, massPropertiesOverride.mass,
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
