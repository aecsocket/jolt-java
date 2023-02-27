package jolt.physics.collision.shape;

import jolt.math.Vec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class BoxShapeSettings extends ConvexShapeSettings {
    public static BoxShapeSettings at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BoxShapeSettings(address);
    }

    public static BoxShapeSettings create(Vec3 halfExtent, float convexRadius) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_BoxShapeSettings_Create0(
                    allocateVec3(session, halfExtent),
                    convexRadius
            );
            return new BoxShapeSettings(address);
        }
    }

    public static BoxShapeSettings create(Vec3 halfExtent) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_BoxShapeSettings_Create1(
                    allocateVec3(session, halfExtent)
            );
            return new BoxShapeSettings(address);
        }
    }

    private BoxShapeSettings(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() { /* todo */ }
}
