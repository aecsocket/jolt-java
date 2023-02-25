package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.math.JtVec3f;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/BoxShape.h>")
@JniTypeMapping("BoxShape")
final class BoxShapeImpl extends ConvexShapeImpl implements BoxShape {
    BoxShapeImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    BoxShapeImpl(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        address = _ctor(
                halfExtent.x, halfExtent.y, halfExtent.z,
                convexRadius,
                ptr(material)
        );
    }
    @JniBind("""
            return (jlong) new BoxShape(
                Vec3(halfExtentX, halfExtentY, halfExtentZ),
                convexRadius,
                (PhysicsMaterial*) material
            );""")
    private static native long _ctor(
            float halfExtentX, float halfExtentY, float halfExtentZ,
            float convexRadius,
            long material
    );

    @Override
    public JtVec3f getHalfExtent(JtVec3f out) {
        _getHalfExtent(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetHalfExtent(), out);")
    private static native void _getHalfExtent(long _a, JtVec3f out);
}
