package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TriangleShape extends ConvexShape {
    //region Jolt-Pointer
    private TriangleShape(MemoryAddress handle) {
        super(handle);
    }

    public static TriangleShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TriangleShape(addr);
    }
    //endregion Jolt-Pointer

    public static TriangleShape of(FVec3 v1, FVec3 v2, FVec3 v3, float convexRadius, @Nullable PhysicsMaterial material) {
        return new TriangleShape(JPC_TriangleShape_Create(
                v1.address(),
                v2.address(),
                v3.address(),
                convexRadius,
                Jolt.ptr(material)
        ));
    }

    public static TriangleShape of(FVec3 v1, FVec3 v2, FVec3 v3, float convexRadius) {
        return of(v1, v2, v3, convexRadius, null);
    }

    public static TriangleShape of(FVec3 v1, FVec3 v2, FVec3 v3) {
        return of(v1, v2, v3, 0.0f, null);
    }

    public float getConvexRadius() {
        return JPC_TriangleShape_GetConvexRadius(handle);
    }
}
