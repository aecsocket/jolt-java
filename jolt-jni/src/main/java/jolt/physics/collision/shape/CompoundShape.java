package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.*;

@JniInclude("<Jolt/Physics/Collision/Shape/CompoundShape.h>")
@JniTypeMapping("CompoundShape")
public class CompoundShape extends Shape {
    protected CompoundShape(long address) { super(address); }
    public static CompoundShape ref(long address) { return address == 0 ? null : new CompoundShape(address); }

    protected CompoundShape() {}

    // TODO GetSubShapes
    public int getNumSubShapes() { return _getNumSubShapes(address); }
    @JniBindSelf("return self->GetNumSubShapes();")
    private static native int _getNumSubShapes(long _a);
}
