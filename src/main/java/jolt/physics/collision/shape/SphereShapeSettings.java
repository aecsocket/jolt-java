package jolt.physics.collision.shape;

import jolt.Jolt;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class SphereShapeSettings extends ConvexShapeSettings {
    // START Jolt-Pointer
    private SphereShapeSettings(MemoryAddress handle) {
        super(handle);
    }

    public static SphereShapeSettings at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new SphereShapeSettings(addr);
    }
    // END Jolt-Pointer

    public static SphereShapeSettings of(float radius, @Nullable PhysicsMaterial material) {
        return new SphereShapeSettings(JPC_SphereShapeSettings_Create(radius, Jolt.ptr(material)));
    }

    public static SphereShapeSettings of(float radius) {
        return of(radius, null);
    }

    public float getRadius() {
        return JPC_SphereShapeSettings_GetRadius(handle);
    }

    public void setRadius(float radius) {
        JPC_SphereShapeSettings_SetRadius(handle, radius);
    }
}
