package jolt.physics.collision.shape;

import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface CapsuleShapeSettings extends ConvexShapeSettings permits CapsuleShapeSettingsImpl {
    static CapsuleShapeSettings ref(long address) { return address == 0 ? null : new CapsuleShapeSettingsImpl(address); }

    static CapsuleShapeSettings create(float halfHeightOfCylinder, float radius, @Nullable PhysicsMaterial material) {
        return new CapsuleShapeSettingsImpl(halfHeightOfCylinder, radius, material);
    }

    static CapsuleShapeSettings create(float halfHeightOfCylinder, float radius) {
        return new CapsuleShapeSettingsImpl(halfHeightOfCylinder, radius, null);
    }

    float getRadius();

    void setRadius(float radius);

    float getHalfHeightOfCylinder();

    void setHalfHeightOfCylinder(float halfHeightOfCylinder);
}
