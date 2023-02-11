package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/CapsuleShape.h>")
@JniTypeMapping("CapsuleShapeSettings")
public final class CapsuleShapeSettings extends ConvexShapeSettings {
    private CapsuleShapeSettings(long address) { super(address); }
    public static CapsuleShapeSettings ref(long address) { return address == 0 ? null : new CapsuleShapeSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public CapsuleShapeSettings(float halfHeightOfCylinder, float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(halfHeightOfCylinder, radius, ptr(material));
    }
    @JniBind("return (jlong) new CapsuleShapeSettings(halfHeightOfCylinder, radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float halfHeightOfCylinder, float radius, long material);

    public CapsuleShapeSettings(float halfHeightOfCylinder, float radius) { this(halfHeightOfCylinder, radius, null); }

    public float getRadius() { return _getRadius(address); }
    @JniBindSelf("return self->mRadius;")
    private static native float _getRadius(long _a);

    public void setRadius(float value) { _setRadius(address, value); }
    @JniBindSelf("self->mRadius = value;")
    private static native void _setRadius(long _a, float value);

    public float getHalfHeightOfCylinder() { return _getHalfHeightOfCylinder(address); }
    @JniBindSelf("return self->mHalfHeightOfCylinder;")
    private static native float _getHalfHeightOfCylinder(long _a);

    public void setHalfHeightOfCylinder(float value) { _setHalfHeightOfCylinder(address, value); }
    @JniBindSelf("self->mHalfHeightOfCylinder = value;")
    private static native void _setHalfHeightOfCylinder(long _a, float value);
}
