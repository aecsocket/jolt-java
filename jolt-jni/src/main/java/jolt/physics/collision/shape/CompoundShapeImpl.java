package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.UnimplementedMethodException;
import jolt.geometry.AABox;
import jolt.geometry.OrientedBox;

@JniInclude("<Jolt/Physics/Collision/Shape/CompoundShape.h>")
@JniTypeMapping("CompoundShape")
sealed class CompoundShapeImpl extends ShapeImpl implements CompoundShape permits StaticCompoundShapeImpl {
    CompoundShapeImpl(long address) { super(address); }

    protected CompoundShapeImpl() {}

    @Override
    public int[] getIntersectingSubShapes(AABox box) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public int[] getIntersectingSubShapes(OrientedBox box) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public int getNumSubShapes() { return _getNumSubShapes(address); }
    @JniBindSelf("return self->GetNumSubShapes();")
    private static native int _getNumSubShapes(long _a);
}
