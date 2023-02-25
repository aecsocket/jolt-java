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
public sealed class ConvexShapeSettings extends ShapeSettings permits SphereShapeSettings, BoxShapeSettings, CapsuleShapeSettings {
    protected ConvexShapeSettings(long address) { super(address); }
    public static ConvexShapeSettings ref(long address) { return address == 0 ? null : new ConvexShapeSettings(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    protected ConvexShapeSettings() {}

    public final float getDensity() { return _getDensity(address); }
    @JniBindSelf("return self->mDensity;")
    private static native float _getDensity(long _a);

    public final void setDensity(float density) { _setDensity(address, density); }
    @JniBindSelf("self->SetDensity(value);")
    private static native void _setDensity(long _a, float value);

    public final @Nullable PhysicsMaterial getMaterial() {
        throw new UnimplementedMethodException(); // TODO
    }

    public final void setMaterial(@Nullable PhysicsMaterial material) {
        throw new UnimplementedMethodException(); // TODO
    }
}
