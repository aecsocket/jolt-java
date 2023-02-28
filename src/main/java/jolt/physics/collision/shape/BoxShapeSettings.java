package jolt.physics.collision.shape;

import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class BoxShapeSettings extends ConvexShapeSettings {
    public static BoxShapeSettings at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BoxShapeSettings(address);
    }

    public static BoxShapeSettings create(FVec3 halfExtent, float convexRadius) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_BoxShapeSettings_Create0(
                    halfExtent.allocate(session),
                    convexRadius
            );
            return new BoxShapeSettings(address);
        }
    }

    public static BoxShapeSettings create(FVec3 halfExtent) {
        try (var session = MemorySession.openConfined()) {
            var address = JPC_BoxShapeSettings_Create1(
                    halfExtent.allocate(session)
            );
            return new BoxShapeSettings(address);
        }
    }

    private BoxShapeSettings(MemoryAddress address) {
        super(address);
    }
}
