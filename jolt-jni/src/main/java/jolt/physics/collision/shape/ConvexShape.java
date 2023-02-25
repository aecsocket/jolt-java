package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;

@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("ConvexShape")
public sealed class ConvexShape extends Shape permits BoxShape, SphereShape, CapsuleShape {
    protected ConvexShape(long address) { super(address); }
    public static ConvexShape ref(long address) { return address == 0 ? null : new ConvexShape(address); }

    protected ConvexShape() {}

    public final float getDensity() { return _getDensity(address); }
    @JniBindSelf("return self->GetDensity();")
    private static native float _getDensity(long _a);

    public final void setDensity(float density) { _setDensity(address, density); }
    @JniBindSelf("self->SetDensity(value);")
    private static native void _setDensity(long _a, float value);
}
