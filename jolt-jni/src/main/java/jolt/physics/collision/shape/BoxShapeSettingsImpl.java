package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.math.JtVec3f;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/BoxShape.h>")
@JniTypeMapping("BoxShapeSettings")
final class BoxShapeSettingsImpl extends ConvexShapeSettingsImpl implements BoxShapeSettings {
    BoxShapeSettingsImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    BoxShapeSettingsImpl(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        address = _ctor(
                halfExtent.x, halfExtent.y, halfExtent.z,
                convexRadius,
                ptr(material)
        );
    }
    @JniBind("""
        return (jlong) new BoxShapeSettings(
            Vec3Arg(halfExtentX, halfExtentY, halfExtentZ),
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
    @JniBindSelf("ToJavaSp(env, self->mHalfExtent, out);")
    private static native void _getHalfExtent(long _a, JtVec3f out);

    @Override
    public void setHalfExtent(JtVec3f halfExtent) { _setHalfExtent(address, halfExtent.x, halfExtent.y, halfExtent.z); }
    @JniBindSelf("self->mHalfExtent = Vec3(valueX, valueY, valueZ);")
    private static native void _setHalfExtent(long _a, float valueX, float valueY, float valueZ);

    @Override
    public float getConvexRadius() { return _getConvexRadius(address); }
    @JniBindSelf("return self->mConvexRadius;")
    private static native float _getConvexRadius(long _a);

    @Override
    public void setConvexRadius(float convexRadius) { _setConvexRadius(address, convexRadius); }
    @JniBindSelf("self->mConvexRadius = value;")
    private static native void _setConvexRadius(long _a, float value);
}
