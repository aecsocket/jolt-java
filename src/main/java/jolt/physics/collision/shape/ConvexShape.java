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
    //region Jolt-Pointer-Protected
    protected ConvexShape(MemoryAddress handle) {
        super(handle);
    }

    public static ConvexShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ConvexShape(addr);
    }
    //endregion Jolt-Pointer-Protected

    public void setMaterial(@Nullable PhysicsMaterial material) {
        JPC_ConvexShape_SetMaterial(handle, Jolt.ptr(material));
    }

    public @Nullable PhysicsMaterial getMaterial() {
        return PhysicsMaterial.at(JPC_ConvexShape_GetMaterial(handle));
    }

    public void setDensity(float density) {
        JPC_ConvexShape_SetDensity(handle, density);
    }

    public float getDensity() {
        return JPC_ConvexShape_GetDensity(handle);
    }
}
