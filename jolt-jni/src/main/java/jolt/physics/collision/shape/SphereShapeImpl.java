package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/SphereShape.h>")
@JniTypeMapping("SphereShape")
final class SphereShapeImpl extends ConvexShapeImpl implements SphereShape {
    SphereShapeImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    SphereShapeImpl(float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(radius, ptr(material));
    }
    @JniBind("return (jlong) new SphereShape(radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float radius, long material);

    @Override
    public float getRadius() { return _getRadius(address); }
    @JniBindSelf("return self->GetRadius();")
    private static native float _getRadius(long _a);
}
