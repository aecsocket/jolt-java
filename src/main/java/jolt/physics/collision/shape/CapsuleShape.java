package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class CapsuleShape extends ConvexShape {
    public static CapsuleShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new CapsuleShape(address);
    }

    public static CapsuleShape create(float halfHeight, float radius, @Nullable PhysicsMaterial material) {
        var address = JPC_CapsuleShape_Create(halfHeight, radius, Jolt.ptr(material));
        return new CapsuleShape(address);
    }

    public static CapsuleShape create(float halfHeight, float radius) {
        return create(halfHeight, radius, null);
    }

    private CapsuleShape(MemoryAddress address) {
        super(address);
    }

    public float getRadius() {
        return JPC_CapsuleShape_GetRadius(address);
    }

    public float getHalfHeight() {
        return JPC_CapsuleShape_GetHalfHeightOfCylinder(address);
    }
}
