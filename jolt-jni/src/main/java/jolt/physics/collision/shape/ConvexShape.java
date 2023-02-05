package jolt.physics.collision.shape;

import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniType("ConvexShape")
public class ConvexShape extends Shape {
    protected ConvexShape(long address) { super(address); }
    public static ConvexShape ref(long address) { return address == 0 ? null : new ConvexShape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected ConvexShape() {}
}
