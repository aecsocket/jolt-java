package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;

@JniInclude("<Jolt/Physics/Collision/Shape/StaticCompoundShape.h>")
@JniTypeMapping("StaticCompoundShape")
public final class StaticCompoundShape extends CompoundShape {
    private StaticCompoundShape(long address) { super(address); }
    public static StaticCompoundShape ref(long address) { return address == 0 ? null : new StaticCompoundShape(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public StaticCompoundShape() { address = _ctor(); }
    @JniBind("return (jlong) new StaticCompoundShape();")
    private native long _ctor();
}
