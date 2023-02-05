package jolt.physics.collision.shape;

import jolt.jni.*;
import jolt.math.JtVec3f;
import jolt.physics.PhysicsSettings;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/BoxShape.h>")
@JniType("BoxShape")
public final class BoxShape extends ConvexShape {
    private BoxShape(long address) { super(address); }
    public static BoxShape ref(long address) { return address == 0 ? null : new BoxShape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public BoxShape(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
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

    public BoxShape(JtVec3f halfExtent, float convexRadius) { this(halfExtent, convexRadius, null); }

    public BoxShape(JtVec3f halfExtent) { this(halfExtent, PhysicsSettings.DEFAULT_CONVEX_RADIUS, null); }

    public JtVec3f getHalfExtent() { return _getHalfExtent(address); }
    @JniBindSelf("return ToJava(env, self->GetHalfExtent());")
    private static native JtVec3f _getHalfExtent(long _a);
}
