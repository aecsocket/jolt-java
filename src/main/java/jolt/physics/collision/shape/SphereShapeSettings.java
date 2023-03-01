package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.JoltNative;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class SphereShapeSettings extends ConvexShapeSettings {
    public static SphereShapeSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new SphereShapeSettings(address);
    }

    public static SphereShapeSettings create(float radius, @Nullable PhysicsMaterial material) {
        var address = JPC_SphereShapeSettings_Create(radius, Jolt.ptr(material));
        return new SphereShapeSettings(address);
    }

    public static SphereShapeSettings create(float radius) {
        return create(radius, null);
    }

    private SphereShapeSettings(MemoryAddress address) {
        super(address);
    }

    public float getRadius() {
        return JPC_SphereShapeSettings_GetRadius(address);
    }

    public void setRadius(float radius) {
        JPC_SphereShapeSettings_SetRadius(address, radius);
    }
}
