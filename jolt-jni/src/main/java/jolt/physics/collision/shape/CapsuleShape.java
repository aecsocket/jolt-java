package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/CapsuleShape.h>")
@JniTypeMapping("CapsuleShape")
public final class CapsuleShape extends ConvexShape {
    private CapsuleShape(long address) { super(address); }
    public static CapsuleShape ref(long address) { return address == 0 ? null : new CapsuleShape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public CapsuleShape(float halfHeightOfCylinder, float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(halfHeightOfCylinder, radius, ptr(material));
    }
    @JniBind("return (jlong) new CapsuleShape(halfHeightOfCylinder, radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float halfHeightOfCylinder, float radius, long material);

    public CapsuleShape(float halfHeightOfCylinder, float radius) { this(halfHeightOfCylinder, radius, null); }

    public float getRadius() { return _getRadius(address); }
    @JniBindSelf("return self->GetRadius();")
    private static native float _getRadius(long _a);

    public float getHalfHeightOfCylinder() { return _getHalfHeightOfCylinder(address); }
    @JniBindSelf("return self->GetHalfHeightOfCylinder();")
    private static native float _getHalfHeightOfCylinder(long _a);
}
