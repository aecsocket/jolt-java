package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.UnimplementedMethodException;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Collision/Shape/ConvexShape.h>")
@JniTypeMapping("ConvexShapeSettings")
sealed class ConvexShapeSettingsImpl extends ShapeSettingsImpl implements ConvexShapeSettings permits SphereShapeSettingsImpl, BoxShapeSettingsImpl, CapsuleShapeSettingsImpl {
    ConvexShapeSettingsImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    protected ConvexShapeSettingsImpl() {}

    @Override
    public float getDensity() { return _getDensity(address); }
    @JniBindSelf("return self->mDensity;")
    private static native float _getDensity(long _a);

    @Override
    public void setDensity(float density) { _setDensity(address, density); }
    @JniBindSelf("self->SetDensity(value);")
    private static native void _setDensity(long _a, float value);

    @Override
    public @Nullable PhysicsMaterial getMaterial() {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void setMaterial(@Nullable PhysicsMaterial material) {
        throw new UnimplementedMethodException(); // TODO
    }
}
