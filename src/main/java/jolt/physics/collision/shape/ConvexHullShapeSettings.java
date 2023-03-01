package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.*;

public final class ConvexHullShapeSettings extends ConvexShapeSettings {
    public static ConvexHullShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ConvexHullShapeSettings(address);
    }

    public static ConvexHullShapeSettings create(FVec3[] points, float convexRadius, @Nullable PhysicsMaterial material) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_ConvexHullShapeSettings_Create(
                    FVec3.allocate(session, points),
                    points.length,
                    (int) (3 * C_FLOAT.byteSize()),
                    convexRadius,
                    Jolt.ptr(material)
            );
            return new ConvexHullShapeSettings(address);
        }
    }

    public static ConvexHullShapeSettings create(Collection<FVec3> points, float convexRadius, @Nullable PhysicsMaterial material) {
        return create(points.toArray(new FVec3[0]), convexRadius, material);
    }

    public static ConvexHullShapeSettings create(FVec3[] points, float convexRadius) {
        return create(points, convexRadius, null);
    }

    public static ConvexHullShapeSettings create(Collection<FVec3> points, float convexRadius) {
        return create(points.toArray(new FVec3[0]), convexRadius, null);
    }

    public static ConvexHullShapeSettings create(FVec3... points) {
        return create(points, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    public static ConvexHullShapeSettings create(Collection<FVec3> points) {
        return create(points.toArray(new FVec3[0]), Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    private ConvexHullShapeSettings(MemoryAddress address) {
        super(address);
    }

    // TODO getPoints
    // TODO setPoints

    public float getMaxConvexRadius() {
        return JPC_ConvexHullShapeSettings_GetMaxConvexRadius(address);
    }

    public void setMaxConvexRadius(float maxConvexRadius) {
        JPC_ConvexHullShapeSettings_SetMaxConvexRadius(address, maxConvexRadius);
    }

    public float getMaxErrorConvexRadius() {
        return JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius(address);
    }

    public void setMaxErrorConvexRadius(float maxErrorConvexRadius) {
        JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius(address, maxErrorConvexRadius);
    }

    public float getHullTolerance() {
        return JPC_ConvexHullShapeSettings_GetHullTolerance(address);
    }

    public void setHullTolerance(float hullTolerance) {
        JPC_ConvexHullShapeSettings_SetHullTolerance(address, hullTolerance);
    }
}
