package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/CapsuleShape.h>")
@JniTypeMapping("CapsuleShape")
final class CapsuleShapeImpl extends ConvexShapeImpl implements CapsuleShape {
    CapsuleShapeImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    CapsuleShapeImpl(float halfHeightOfCylinder, float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(halfHeightOfCylinder, radius, ptr(material));
    }
    @JniBind("return (jlong) new CapsuleShape(halfHeightOfCylinder, radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float halfHeightOfCylinder, float radius, long material);

    @Override
    public float getRadius() { return _getRadius(address); }
    @JniBindSelf("return self->GetRadius();")
    private static native float _getRadius(long _a);

    @Override
    public float getHalfHeightOfCylinder() { return _getHalfHeightOfCylinder(address); }
    @JniBindSelf("return self->GetHalfHeightOfCylinder();")
    private static native float _getHalfHeightOfCylinder(long _a);
}
