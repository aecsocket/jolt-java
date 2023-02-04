package jolt.physics.collision.shape;

import jolt.jni.JniBind;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/SphereShape.h>")
@JniType("SphereShape")
public final class SphereShape extends ConvexShape {
    private SphereShape(long address) { super(address); }
    public static SphereShape ref(long address) { return address == 0 ? null : new SphereShape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public SphereShape(float radius, @Nullable PhysicsMaterial material) {
        address = _ctor(radius, ptr(material));
    }
    @JniBind("return (jlong) new SphereShape(radius, (PhysicsMaterial*) material);")
    private static native long _ctor(float radius, long material);

    public SphereShape(float radius) { this(radius, null); }
}
