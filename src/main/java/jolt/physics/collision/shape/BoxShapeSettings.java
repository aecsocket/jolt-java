package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class BoxShapeSettings extends ConvexShapeSettings {
    // START Jolt-Pointer
    private BoxShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static BoxShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BoxShapeSettings(addr);
    }
    // END Jolt-Pointer

    public static BoxShapeSettings of(FVec3 halfExtent, float convexRadius, @Nullable PhysicsMaterial material) {
        return new BoxShapeSettings(JPC_BoxShapeSettings_Create(halfExtent.address(), convexRadius, Jolt.ptr(material)));
    }

    public static BoxShapeSettings of(FVec3 halfExtent, float convexRadius) {
        return of(halfExtent, convexRadius, null);
    }

    public static BoxShapeSettings of(FVec3 halfExtent) {
        return of(halfExtent, Shape.DEFAULT_CONVEX_RADIUS, null);
    }

    public void getHalfExtent(FVec3 out) {
        JPC_BoxShapeSettings_GetHalfExtent(handle, out.address());
    }

    public void setHalfExtent(FVec3 halfExtent) {
        JPC_BoxShapeSettings_SetHalfExtent(handle, halfExtent.address());
    }

    public float getConvexRadius() {
        return JPC_BoxShapeSettings_GetConvexRadius(handle);
    }

    public void setConvexRadius(float convexRadius) {
        JPC_BoxShapeSettings_SetConvexRadius(handle, convexRadius);
    }
}
