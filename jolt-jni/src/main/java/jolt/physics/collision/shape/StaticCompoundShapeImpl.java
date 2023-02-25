package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;

@JniInclude("<Jolt/Physics/Collision/Shape/StaticCompoundShape.h>")
@JniTypeMapping("StaticCompoundShape")
final class StaticCompoundShapeImpl extends CompoundShapeImpl implements StaticCompoundShape {
    StaticCompoundShapeImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);
}
