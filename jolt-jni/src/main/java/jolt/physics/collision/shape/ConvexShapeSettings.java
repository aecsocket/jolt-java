package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Physics/Collision/Shape/ConvexShape.h>")
@JniType("ConvexShapeSettings")
public class ConvexShapeSettings extends ShapeSettings {
    protected ConvexShapeSettings(long address) { super(address); }
    public static ConvexShapeSettings ref(long address) { return address == 0 ? null : new ConvexShapeSettings(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected ConvexShapeSettings() {}
}
