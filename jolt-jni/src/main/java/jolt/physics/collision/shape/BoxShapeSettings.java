package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.math.JtVec3f;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/BoxShape.h>")
@JniTypeMapping("BoxShapeSettings")
public final class BoxShapeSettings extends ConvexShapeSettings {
    private BoxShapeSettings(long address) { super(address); }
    public static BoxShapeSettings ref(long address) { return address == 0 ? null : new BoxShapeSettings(address); }


    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public BoxShapeSettings(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
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

    public BoxShapeSettings(JtVec3f halfExtent, float convexRadius) { this(halfExtent, convexRadius, null); }

    public BoxShapeSettings(JtVec3f halfExtent) { this(halfExtent, PhysicsSettings.DEFAULT_CONVEX_RADIUS, null); }

    public JtVec3f getHalfExtent(JtVec3f out) {
        _getHalfExtent(address, out);
        return out;
    }
    public JtVec3f getHalfExtent() { return getHalfExtent(new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->mHalfExtent, out);")
    private static native void _getHalfExtent(long _a, JtVec3f out);

    public void setHalfExtent(JtVec3f value) { _setHalfExtent(address, value.x, value.y, value.z); }
    @JniBindSelf("self->mHalfExtent = Vec3(valueX, valueY, valueZ);")
    private static native void _setHalfExtent(long _a, float valueX, float valueY, float valueZ);

    public float getConvexRadius() { return _getConvexRadius(address); }
    @JniBindSelf("return self->mConvexRadius;")
    private static native float _getConvexRadius(long _a);

    public void setConvexRadius(float value) { _setConvexRadius(address, value); }
    @JniBindSelf("self->mConvexRadius = value;")
    private static native void _setConvexRadius(long _a, float value);
}
