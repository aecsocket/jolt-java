package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ConvexShape extends Shape
        permits SphereShape, BoxShape, TriangleShape, CapsuleShape,
        TaperedCapsuleShape, CylinderShape, ConvexHullShape {
    public static ConvexShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ConvexShape(address);
    }

    protected ConvexShape(MemoryAddress address) {
        super(address);
    }

    public void setMaterial(@Nullable PhysicsMaterial material) {
        JPC_ConvexShape_SetMaterial(address, Jolt.ptr(material));
    }

    public @Nullable PhysicsMaterial getMaterial() {
        return PhysicsMaterial.at(JPC_ConvexShape_GetMaterial(address));
    }

    public void setDensity(float density) {
        JPC_ConvexShape_SetDensity(address, density);
    }

    public float getDensity() {
        return JPC_ConvexShape_GetDensity(address);
    }
}
