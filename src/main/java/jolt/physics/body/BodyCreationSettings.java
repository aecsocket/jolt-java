package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

public abstract sealed class BodyCreationSettings extends AddressedJoltNative
        permits BodyCreationSettings.F, BodyCreationSettings.D {
    public static BodyCreationSettings at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(address) : new F(address);
    }

    public static BodyCreationSettings create(
            MemorySession session,
            Shape shape,
            FVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        Jolt.assertSinglePrecision();
        var segment = jolt.headers_f.JPC_BodyCreationSettings.allocate(session);
        jolt.headers_f.JoltPhysicsC.JPC_BodyCreationSettings_Set(
                segment,
                shape.address(), position.address(), rotation.address(),
                (byte) motionType.ordinal(),
                layer
        );
        return new BodyCreationSettings.F(segment.address());
    }

    public static BodyCreationSettings create(
            MemorySession session,
            Shape shape,
            DVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        Jolt.assertDoublePrecision();
        var segment = jolt.headers_d.JPC_BodyCreationSettings.allocate(session);
        jolt.headers_d.JoltPhysicsC.JPC_BodyCreationSettings_Set(
                segment,
                shape.address(), position.address(), rotation.address(),
                (byte) motionType.ordinal(),
                layer
        );
        return new BodyCreationSettings.D(segment.address());
    }

    private BodyCreationSettings(MemoryAddress address) {
        super(address);
    }

    static final class F extends BodyCreationSettings {
        private F(MemoryAddress address) {
            super(address);
        }
    }

    static final class D extends BodyCreationSettings {
        private D(MemoryAddress address) {
            super(address);
        }
    }
}
