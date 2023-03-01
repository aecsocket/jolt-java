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
        try (var session = MemorySession.openConfined()) {
            var address = JPC_TriangleShapeSettings_Create(
                    v1.allocate(session),
                    v2.allocate(session),
                    v3.allocate(session),
                    convexRadius,
                    Jolt.ptr(material)
            );
            return new TriangleShapeSettings(address);
        }
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

    public record Vertices(FVec3 v1, FVec3 v2, FVec3 v3) {}

    public Vertices getVertices() {
        try (var session = MemorySession.openConfined()) {
            var v1 = FVec3.ZERO.allocate(session);
            var v2 = FVec3.ZERO.allocate(session);
            var v3 = FVec3.ZERO.allocate(session);
            JPC_TriangleShapeSettings_GetVertices(address, v1, v2, v3);
            return new Vertices(FVec3.read(v1), FVec3.read(v2), FVec3.read(v3));
        }
    }

    public void setVertices(FVec3 v1, FVec3 v2, FVec3 v3) {
        try (var session = MemorySession.openConfined()) {
            JPC_TriangleShapeSettings_SetVertices(address,
                    v1.allocate(session),
                    v2.allocate(session),
                    v3.allocate(session)
            );
        }
    }

    public float getConvexRadius() {
        return JPC_TriangleShapeSettings_GetConvexRadius(address);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_TriangleShapeSettings_SetConvexRadius(address, convexRadius);
    }
}
