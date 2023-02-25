package jolt.physics.collision.shape;

import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface SphereShapeSettings extends ConvexShapeSettings permits SphereShapeSettingsImpl {
    static SphereShapeSettings ref(long address) { return address == 0 ? null : new SphereShapeSettingsImpl(address); }

    static SphereShapeSettings create(float radius, @Nullable PhysicsMaterial material) {
        return new SphereShapeSettingsImpl(radius, material);
    }

    static SphereShapeSettings create(float radius) {
        return new SphereShapeSettingsImpl(radius, null);
    }

    float getRadius();

    void setRadius(float radius);
}
