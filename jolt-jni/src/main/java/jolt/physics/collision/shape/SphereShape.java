package jolt.physics.collision.shape;

import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface SphereShape extends ConvexShape permits SphereShapeImpl {
    static SphereShape ref(long address) { return address == 0 ? null : new SphereShapeImpl(address); }

    static SphereShape create(float radius, @Nullable PhysicsMaterial material) {
        return new SphereShapeImpl(radius, material);
    }

    static SphereShape create(float radius) {
        return new SphereShapeImpl(radius, null);
    }

    float getRadius();
}
