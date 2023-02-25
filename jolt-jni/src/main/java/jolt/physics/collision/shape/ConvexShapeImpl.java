package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;


@JniInclude("<Jolt/Physics/Collision/Shape/Shape.h>")
@JniTypeMapping("ConvexShape")
sealed class ConvexShapeImpl extends ShapeImpl implements ConvexShape permits BoxShapeImpl, SphereShapeImpl, CapsuleShapeImpl {
    ConvexShapeImpl(long address) { super(address); }

    protected ConvexShapeImpl() {}

    @Override
    public float getDensity() { return _getDensity(address); }
    @JniBindSelf("return self->GetDensity();")
    private static native float _getDensity(long _a);

    @Override
    public void setDensity(float density) { _setDensity(address, density); }
    @JniBindSelf("self->SetDensity(value);")
    private static native void _setDensity(long _a, float value);
}
