package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class TriangleShape extends ConvexShape {
    public static TriangleShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new TriangleShape(address);
    }

    public static TriangleShape create(FVec3 v1, FVec3 v2, FVec3 v3, float convexRadius, @Nullable PhysicsMaterial material) {
        var address = JPC_TriangleShape_Create(
                v1.address(),
                v2.address(),
                v3.address(),
                convexRadius,
                Jolt.ptr(material)
        );
        return new TriangleShape(address);
    }

    public static TriangleShape create(FVec3 v1, FVec3 v2, FVec3 v3, float convexRadius) {
        return create(v1, v2, v3, convexRadius, null);
    }

    public static TriangleShape create(FVec3 v1, FVec3 v2, FVec3 v3) {
        return create(v1, v2, v3, 0.0f, null);
    }

    private TriangleShape(MemoryAddress address) {
        super(address);
    }

    public float getConvexRadius() {
        return JPC_TriangleShape_GetConvexRadius(address);
    }
}
