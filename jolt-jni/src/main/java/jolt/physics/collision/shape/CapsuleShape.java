package jolt.physics.collision.shape;

import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface CapsuleShape extends ConvexShape permits CapsuleShapeImpl {
    static CapsuleShape ref(long address) { return address == 0 ? null : new CapsuleShapeImpl(address); }

    static CapsuleShape create(float halfHeightOfCylinder, float radius, @Nullable PhysicsMaterial material) {
        return new CapsuleShapeImpl(halfHeightOfCylinder, radius, material);
    }

    static CapsuleShape create(float halfHeightOfCylinder, float radius) {
        return new CapsuleShapeImpl(halfHeightOfCylinder, radius, null);
    }

    float getRadius();

    float getHalfHeightOfCylinder();
}
