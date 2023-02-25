package jolt.physics.collision.shape;

import jolt.math.JtVec3f;
import jolt.physics.PhysicsSettings;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;

public sealed interface SphereShapeSettings extends ConvexShapeSettings permits BoxShapeSettingsImpl {
    static SphereShapeSettings ref(long address) { return address == 0 ? null : new BoxShapeSettingsImpl(address); }

    static SphereShapeSettings create(JtVec3f halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        return new BoxShapeSettingsImpl(halfExtent, convexRadius, material);
    }

    static SphereShapeSettings create(JtVec3f halfExtent, float convexRadius) {
        return new BoxShapeSettingsImpl(halfExtent, convexRadius, null);
    }

    static SphereShapeSettings create(JtVec3f halfExtent) {
        return new BoxShapeSettingsImpl(halfExtent, PhysicsSettings.DEFAULT_CONVEX_RADIUS, null);
    }

    JtVec3f getHalfExtent(JtVec3f out);
    default JtVec3f getHalfExtent() { return getHalfExtent(new JtVec3f()); }

    void setHalfExtent(JtVec3f halfExtent);

    float getConvexRadius();

    void setConvexRadius(float convexRadius);
}
