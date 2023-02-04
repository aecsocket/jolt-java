package jolt.physics.collision.shape;

import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/Shape/ConvexShape.h>")
@JniType("ConvexShapeSettings")
public class ConvexShapeSettings extends ShapeSettings {
    protected ConvexShapeSettings(long address) { super(address); }
    public static ConvexShapeSettings ref(long address) { return new ConvexShapeSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long address);

    protected ConvexShapeSettings() {}
}
