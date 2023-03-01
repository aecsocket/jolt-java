package jolt.physics.collision.shape;

import jolt.physics.collision.PhysicsMaterial;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class ConvexShapeSettings extends ShapeSettings
        permits SphereShapeSettings, BoxShapeSettings, TriangleShapeSettings, CapsuleShapeSettings,
        TaperedCapsuleShapeSettings, CylinderShapeSettings, ConvexHullShapeSettings {
    public static ConvexShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ConvexShapeSettings(address);
    }

    protected ConvexShapeSettings(MemoryAddress address) {
        super(address);
    }

    public PhysicsMaterial getMaterial() {
        return PhysicsMaterial.at(JPC_ConvexShapeSettings_GetMaterial(address));
    }

    public void setMaterial(PhysicsMaterial material) {
        JPC_ConvexShapeSettings_SetMaterial(address, material.address());
    }

    public float getDensity() {
        return JPC_ConvexShapeSettings_GetDensity(address);
    }

    public void setDensity(float density) {
        JPC_ConvexShapeSettings_SetDensity(address, density);
    }
}
