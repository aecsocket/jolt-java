package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.UnimplementedException;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;
import static jolt.headers.JPC_BodyCreationSettings.*;

public final class BodyCreationSettings extends AddressedJoltNative {
    public static BodyCreationSettings at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BodyCreationSettings(address);
    }

    public static BodyCreationSettings create(
            MemorySession session,
            Shape shape,
            FVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        var segment = allocate(session);
        try (var s = MemorySession.openConfined()) {
            JPC_BodyCreationSettings_Set(
                    segment,
                    shape.address(), position.allocate(s), rotation.allocate(s),
                    (byte) motionType.ordinal(),
                    layer
            );
        }
        return new BodyCreationSettings(segment.address());
    }

    public static BodyCreationSettings create(
            MemorySession session,
            Shape shape,
            DVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        var segment = allocate(session);
        try (var s = MemorySession.openConfined()) {
            JPC_BodyCreationSettings_Set(
                    segment,
                    shape.address(), position.allocate(s), rotation.allocate(s),
                    (byte) motionType.ordinal(),
                    layer
            );
        }
        return new BodyCreationSettings(segment.address());
    }

    private BodyCreationSettings(MemoryAddress address) {
        super(address);
    }
}
