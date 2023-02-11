package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("ConvexShape")
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
