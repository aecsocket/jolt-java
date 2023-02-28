package jolt.physics.body;

import jolt.AbstractJoltNative;
import jolt.UnimplementedException;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;
import static jolt.headers.JPC_BodyCreationSettings.*;

public final class BodyCreationSettings extends AbstractJoltNative {
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
        try (var session2 = MemorySession.openConfined()) {
            JPC_BodyCreationSettings_Set(
                    segment,
                    shape.address(), allocateFVec3(session2, position), allocateQuat(session2, rotation),
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
        // TODO
        throw new UnimplementedException();
    }

    private BodyCreationSettings(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void destroyInternal() {}


}
