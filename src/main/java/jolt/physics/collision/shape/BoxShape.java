package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class BoxShape extends ConvexShape {
    public static BoxShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new BoxShape(address);
    }

    public static BoxShape create(FVec3 halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_BoxShape_Create(halfExtent.allocate(session), convexRadius, Jolt.ptr(material));
            return new BoxShape(address);
        }
    }

    public static BoxShape create(FVec3 halfExtent, float convexRadius) {
        return create(halfExtent, convexRadius, null);
    }

    public static BoxShape create(FVec3 halfExtent) {
        return create(halfExtent, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    private BoxShape(MemoryAddress address) {
        super(address);
    }

    public FVec3 getHalfExtent() {
        try (var session = MemorySession.openConfined()) {
            var out = FVec3.ZERO.allocate(session);
            JPC_BoxShape_GetHalfExtent(address, out);
            return FVec3.read(out);
        }
    }
}
