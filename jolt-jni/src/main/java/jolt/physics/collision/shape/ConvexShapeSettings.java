package jolt.physics.collision.shape;

import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface ConvexShapeSettings extends ShapeSettings permits SphereShapeSettings, BoxShapeSettings, CapsuleShapeSettings, ConvexShapeSettingsImpl {
    static ConvexShapeSettings ref(long address) { return address == 0 ? null : new ConvexShapeSettingsImpl(address); }

    float getDensity();

    void setDensity(float density);

    // TODO RefConst's
    @Nullable PhysicsMaterial getMaterial();

    void setMaterial(@Nullable PhysicsMaterial material);
}
