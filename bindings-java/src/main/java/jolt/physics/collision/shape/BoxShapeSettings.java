package jolt.physics.collision.shape;

import jolt.jni.JniBind;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;
import jolt.math.JtVec3f;
import jolt.physics.PhysicsSettings;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/BoxShape.h>")
@JniType("BoxShapeSettings")
public final class BoxShapeSettings extends ConvexShapeSettings {
    private BoxShapeSettings(long address) { super(address); }
    public static BoxShapeSettings ref(long address) { return new BoxShapeSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long address);

    public BoxShapeSettings(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        address = _ctor(
                halfExtent.x(), halfExtent.y(), halfExtent.z(),
                convexRadius,
                ptr(material)
        );
    }
    @JniBind("""
        return (long) new BoxShapeSettings(
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
}
