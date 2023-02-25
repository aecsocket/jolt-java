package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;

@JniInclude("<Jolt/Physics/Collision/Shape/StaticCompoundShape.h>")
@JniTypeMapping("StaticCompoundShape")
public final class StaticCompoundShape extends CompoundShape {
    private StaticCompoundShape(long address) { super(address); }
    public static StaticCompoundShape ref(long address) { return address == 0 ? null : new StaticCompoundShape(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);
}
