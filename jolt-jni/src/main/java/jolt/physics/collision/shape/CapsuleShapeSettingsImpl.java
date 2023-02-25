package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/CapsuleShape.h>")
@JniTypeMapping("CapsuleShapeSettings")
final class CapsuleShapeSettingsImpl extends ConvexShapeSettingsImpl implements CapsuleShapeSettings {
    CapsuleShapeSettingsImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    CapsuleShapeSettingsImpl(float halfHeightOfCylinder, float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(halfHeightOfCylinder, radius, ptr(material));
    }
    @JniBind("return (jlong) new CapsuleShapeSettings(halfHeightOfCylinder, radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float halfHeightOfCylinder, float radius, long material);

    @Override
    public float getRadius() { return _getRadius(address); }
    @JniBindSelf("return self->mRadius;")
    private static native float _getRadius(long _a);

    @Override
    public void setRadius(float value) { _setRadius(address, value); }
    @JniBindSelf("self->mRadius = value;")
    private static native void _setRadius(long _a, float value);

    @Override
    public float getHalfHeightOfCylinder() { return _getHalfHeightOfCylinder(address); }
    @JniBindSelf("return self->mHalfHeightOfCylinder;")
    private static native float _getHalfHeightOfCylinder(long _a);

    @Override
    public void setHalfHeightOfCylinder(float value) { _setHalfHeightOfCylinder(address, value); }
    @JniBindSelf("self->mHalfHeightOfCylinder = value;")
    private static native void _setHalfHeightOfCylinder(long _a, float value);
}
