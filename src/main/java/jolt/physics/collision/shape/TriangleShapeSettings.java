package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.JoltNative;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class TriangleShapeSettings extends ConvexShapeSettings {
    public static TriangleShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new TriangleShapeSettings(address);
    }

    public static TriangleShapeSettings create(
            FVec3 v1,
            FVec3 v2,
            FVec3 v3,
            float convexRadius,
            @Nullable PhysicsMaterial material
    ) {
        var address = JPC_TriangleShapeSettings_Create(
                v1.address(),
                v2.address(),
                v3.address(),
                convexRadius,
                Jolt.ptr(material)
        );
        return new TriangleShapeSettings(address);
    }

    public static TriangleShapeSettings create(FVec3 v1, FVec3 v2, FVec3 v3, float convexRadius) {
        return create(v1, v2, v3, convexRadius, null);
    }

    public static TriangleShapeSettings create(FVec3 v1, FVec3 v2, FVec3 v3) {
        return create(v1, v2, v3, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    private TriangleShapeSettings(MemoryAddress address) {
        super(address);
    }

    public void getVertices(FVec3 outV1, FVec3 outV2, FVec3 outV3) {
        JPC_TriangleShapeSettings_GetVertices(address, outV1.address(), outV2.address(), outV3.address());
    }

    public void setVertices(FVec3 v1, FVec3 v2, FVec3 v3) {
        JPC_TriangleShapeSettings_SetVertices(address,
                v1.address(),
                v2.address(),
                v3.address()
        );
    }

    public float getConvexRadius() {
        return JPC_TriangleShapeSettings_GetConvexRadius(address);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_TriangleShapeSettings_SetConvexRadius(address, convexRadius);
    }
}
