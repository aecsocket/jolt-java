package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/SphereShape.h>")
@JniType("SphereShapeSettings")
public final class SphereShapeSettings extends ConvexShapeSettings {
    private SphereShapeSettings(long address) { super(address); }
    public static SphereShapeSettings ref(long address) { return address == 0 ? null : new SphereShapeSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public SphereShapeSettings(float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(radius, ptr(material));
    }
    @JniBind("return (jlong) new SphereShapeSettings(radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float radius, long material);

    public SphereShapeSettings(float radius) { this(radius, null); }

    public float getRadius() { return _getRadius(address); }
    @JniBindSelf("return self->mRadius;")
    private static native float _getRadius(long _a);

    public void setRadius(float value) { _setRadius(address, value); }
    @JniBindSelf("self->mRadius = value;")
    private static native void _setRadius(long _a, float value);
}
