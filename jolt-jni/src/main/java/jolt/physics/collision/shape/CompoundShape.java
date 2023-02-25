package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;
import jolt.UnimplementedMethodException;
import jolt.geometry.AABox;
import jolt.geometry.OrientedBox;

@JniInclude("<Jolt/Physics/Collision/Shape/CompoundShape.h>")
@JniTypeMapping("CompoundShape")
public sealed class CompoundShape extends Shape permits StaticCompoundShape {
    protected CompoundShape(long address) { super(address); }
    public static CompoundShape ref(long address) { return address == 0 ? null : new CompoundShape(address); }

    protected CompoundShape() {}

    public final int[] getIntersectingSubShapes(AABox box) {
        throw new UnimplementedMethodException(); // TODO
    }

    public final int[] getIntersectingSubShapes(OrientedBox box) {
        throw new UnimplementedMethodException(); // TODO
    }

    public final int getNumSubShapes() { return _getNumSubShapes(address); }
    @JniBindSelf("return self->GetNumSubShapes();")
    private static native int _getNumSubShapes(long _a);
}
