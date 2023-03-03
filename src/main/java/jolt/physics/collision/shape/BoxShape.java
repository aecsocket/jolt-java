package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class BoxShape extends ConvexShape {
    // START Jolt-Pointer
    private BoxShape(MemoryAddress handle) {
        super(handle);
    }

    public static BoxShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BoxShape(addr);
    }
    // END Jolt-Pointer

    public static BoxShape of(FVec3 halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        return new BoxShape(JPC_BoxShape_Create(halfExtent.address(), convexRadius, Jolt.ptr(material)));
    }

    public static BoxShape of(FVec3 halfExtent, float convexRadius) {
        return of(halfExtent, convexRadius, null);
    }

    public static BoxShape of(FVec3 halfExtent) {
        return of(halfExtent, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    public void getHalfExtent(FVec3 out) {
        JPC_BoxShape_GetHalfExtent(handle, out.address());
    }
}
