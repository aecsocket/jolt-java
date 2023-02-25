package jolt.physics.collision.shape;

import jolt.math.JtVec3f;
import jolt.physics.PhysicsSettings;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface BoxShape extends ConvexShape permits BoxShapeImpl {
    static BoxShape ref(long address) { return address == 0 ? null : new BoxShapeImpl(address); }

    static BoxShape create(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        return new BoxShapeImpl(halfExtent, convexRadius, material);
    }

    static BoxShape create(JtVec3f halfExtent, float convexRadius) {
        return new BoxShapeImpl(halfExtent, convexRadius, null);
    }

    static BoxShape create(JtVec3f halfExtent) {
        return new BoxShapeImpl(halfExtent, PhysicsSettings.DEFAULT_CONVEX_RADIUS, null);
    }

    JtVec3f getHalfExtent(JtVec3f out);
    default JtVec3f getHalfExtent() { return getHalfExtent(new JtVec3f()); }
}
