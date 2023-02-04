package jolt.physics.collision.shape;

import jolt.JoltNative;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniType("Shape")
public class Shape extends JoltNative {
    protected Shape(long address) { super(address); }
    public static Shape ref(long address) { return address == 0 ? null : new Shape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected Shape() {}
}
