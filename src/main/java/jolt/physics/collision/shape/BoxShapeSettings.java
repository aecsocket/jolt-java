package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.JoltNative;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.broadphase.RayCastBodyCollector;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class BoxShapeSettings extends ConvexShapeSettings {
    public static BoxShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new BoxShapeSettings(address);
    }

    public static BoxShapeSettings create(FVec3 halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_BoxShapeSettings_Create(halfExtent.allocate(session), convexRadius, Jolt.ptr(material));
            return new BoxShapeSettings(address);
        }
    }

    public static BoxShapeSettings create(FVec3 halfExtent, float convexRadius) {
        return create(halfExtent, convexRadius, null);
    }

    public static BoxShapeSettings create(FVec3 halfExtent) {
        return create(halfExtent, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    private BoxShapeSettings(MemoryAddress address) {
        super(address);
    }

    public FVec3 getHalfExtent() {
        try (var session = MemorySession.openConfined()) {
            var out = FVec3.ZERO.allocate(session);
            JPC_BoxShapeSettings_GetHalfExtent(address, out);
            return FVec3.read(out.address());
        }
    }

    public void setHalfExtent(FVec3 halfExtent) {
        try (var session = MemorySession.openConfined()) {
            JPC_BoxShapeSettings_SetHalfExtent(address, halfExtent.allocate(session));
        }
    }

    public float getConvexRadius() {
        return JPC_BoxShapeSettings_GetConvexRadius(address);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_BoxShapeSettings_SetConvexRadius(address, convexRadius);
    }
}
