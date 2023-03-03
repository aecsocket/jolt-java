package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TriangleShapeSettings extends ConvexShapeSettings {
    // START Jolt-Pointer
    private TriangleShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static TriangleShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TriangleShapeSettings(addr);
    }
    // END Jolt-Pointer

    public static TriangleShapeSettings of(
            FVec3 v1,
            FVec3 v2,
            FVec3 v3,
            float convexRadius,
            @Nullable PhysicsMaterial material
    ) {
        return new TriangleShapeSettings(JPC_TriangleShapeSettings_Create(
                v1.address(),
                v2.address(),
                v3.address(),
                convexRadius,
                Jolt.ptr(material)
        ));
    }

    public static TriangleShapeSettings of(FVec3 v1, FVec3 v2, FVec3 v3, float convexRadius) {
        return of(v1, v2, v3, convexRadius, null);
    }

    public static TriangleShapeSettings of(FVec3 v1, FVec3 v2, FVec3 v3) {
        return of(v1, v2, v3, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    public void getVertices(FVec3 outV1, FVec3 outV2, FVec3 outV3) {
        JPC_TriangleShapeSettings_GetVertices(handle, outV1.address(), outV2.address(), outV3.address());
    }

    public void setVertices(FVec3 v1, FVec3 v2, FVec3 v3) {
        JPC_TriangleShapeSettings_SetVertices(handle,
                v1.address(),
                v2.address(),
                v3.address()
        );
    }

    public float getConvexRadius() {
        return JPC_TriangleShapeSettings_GetConvexRadius(handle);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_TriangleShapeSettings_SetConvexRadius(handle, convexRadius);
    }
}
