package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.*;

public final class ConvexHullShapeSettings extends ConvexShapeSettings {
    //region Jolt-Pointer
    private ConvexHullShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ConvexHullShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ConvexHullShapeSettings(addr);
    }
    //endregion Jolt-Pointer

    public static ConvexHullShapeSettings of(FVec3[] points, float convexRadius, @Nullable PhysicsMaterial material) {
        try (var arena = MemorySession.openConfined()) {
            return new ConvexHullShapeSettings(JPC_ConvexHullShapeSettings_Create(
                    FVec3.ofArray(arena, points),
                    points.length,
                    (int) (3 * C_FLOAT.byteSize()),
                    convexRadius,
                    Jolt.ptr(material)
            ));
        }
    }

    public static ConvexHullShapeSettings of(Collection<FVec3> points, float convexRadius, @Nullable PhysicsMaterial material) {
        return of(points.toArray(new FVec3[0]), convexRadius, material);
    }

    public static ConvexHullShapeSettings of(FVec3[] points, float convexRadius) {
        return of(points, convexRadius, null);
    }

    public static ConvexHullShapeSettings of(Collection<FVec3> points, float convexRadius) {
        return of(points.toArray(new FVec3[0]), convexRadius, null);
    }

    public static ConvexHullShapeSettings of(FVec3... points) {
        return of(points, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    public static ConvexHullShapeSettings of(Collection<FVec3> points) {
        return of(points.toArray(new FVec3[0]), Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    // TODO getPoints
    // TODO setPoints

    public float getMaxConvexRadius() {
        return JPC_ConvexHullShapeSettings_GetMaxConvexRadius(handle);
    }

    public void setMaxConvexRadius(float maxConvexRadius) {
        JPC_ConvexHullShapeSettings_SetMaxConvexRadius(handle, maxConvexRadius);
    }

    public float getMaxErrorConvexRadius() {
        return JPC_ConvexHullShapeSettings_GetMaxErrorConvexRadius(handle);
    }

    public void setMaxErrorConvexRadius(float maxErrorConvexRadius) {
        JPC_ConvexHullShapeSettings_SetMaxErrorConvexRadius(handle, maxErrorConvexRadius);
    }

    public float getHullTolerance() {
        return JPC_ConvexHullShapeSettings_GetHullTolerance(handle);
    }

    public void setHullTolerance(float hullTolerance) {
        JPC_ConvexHullShapeSettings_SetHullTolerance(handle, hullTolerance);
    }
}
