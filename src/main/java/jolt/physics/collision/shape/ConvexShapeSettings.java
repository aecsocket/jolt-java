package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ConvexShapeSettings extends ShapeSettings
        permits SphereShapeSettings, BoxShapeSettings, TriangleShapeSettings, CapsuleShapeSettings,
        TaperedCapsuleShapeSettings, CylinderShapeSettings, ConvexHullShapeSettings {
    //region Jolt-Pointer-Protected
    protected ConvexShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static ConvexShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ConvexShapeSettings(addr);
    }
    //endregion Jolt-Pointer-Protected

    public @Nullable PhysicsMaterial getMaterial() {
        return PhysicsMaterial.at(JPC_ConvexShapeSettings_GetMaterial(handle));
    }

    public void setMaterial(@Nullable PhysicsMaterial material) {
        JPC_ConvexShapeSettings_SetMaterial(handle, Jolt.ptr(material));
    }

    public float getDensity() {
        return JPC_ConvexShapeSettings_GetDensity(handle);
    }

    public void setDensity(float density) {
        JPC_ConvexShapeSettings_SetDensity(handle, density);
    }
}
