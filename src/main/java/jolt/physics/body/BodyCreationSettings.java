package jolt.physics.body;

import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.*;

import static jolt.headers.JPC_BodyCreationSettings.*;

public abstract sealed class BodyCreationSettings extends SegmentedJoltNative
        permits BodyCreationSettings.F, BodyCreationSettings.D {
    // START Jolt-Value-FD
    private BodyCreationSettings(MemorySegment handle) {
        super(handle);
    }

    public static BodyCreationSettings at(MemorySegment segment) {
        return Jolt.doublePrecision() ? new D(segment) : new F(segment);
    }

    public static BodyCreationSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(jolt.headers_d.JPC_BodyCreationSettings.ofAddress(addr, alloc))
                : new F(jolt.headers_f.JPC_BodyCreationSettings.ofAddress(addr, alloc));
    }

    public static BodyCreationSettings of(SegmentAllocator alloc) {
        return Jolt.doublePrecision()
                ? new D(jolt.headers_d.JPC_BodyCreationSettings.allocate(alloc))
                : new F(jolt.headers_f.JPC_BodyCreationSettings.allocate(alloc));
    }
    // END Jolt-Value-FD

    public static BodyCreationSettings of(
            SegmentAllocator alloc,
            Shape shape,
            FVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        Jolt.assertSinglePrecision();
        var segment = jolt.headers_f.JPC_BodyCreationSettings.allocate(alloc);
        jolt.headers_f.JoltPhysicsC.JPC_BodyCreationSettings_Set(
                segment,
                shape.address(), position.address(), rotation.address(),
                (byte) motionType.ordinal(),
                layer
        );
        return new BodyCreationSettings.F(segment);
    }

    public static BodyCreationSettings of(
            SegmentAllocator alloc,
            Shape shape,
            DVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        Jolt.assertDoublePrecision();
        var segment = jolt.headers_d.JPC_BodyCreationSettings.allocate(alloc);
        jolt.headers_d.JoltPhysicsC.JPC_BodyCreationSettings_Set(
                segment,
                shape.address(), position.address(), rotation.address(),
                (byte) motionType.ordinal(),
                layer
        );
        return new BodyCreationSettings.D(segment);
    }

    public abstract FVec3 getPositionF();

    public abstract void setPositionF(FVec3 position);

    public abstract DVec3 getPositionD();

    public abstract void setPositionD(DVec3 position);

    // TODO the rest

    static final class F extends BodyCreationSettings {
        private F(MemorySegment handle) {
            super(handle);
        }

        @Override
        public FVec3 getPositionF() {
            return FVec3.at(jolt.headers_f.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public void setPositionF(FVec3 position) {
            position.write(jolt.headers_f.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public DVec3 getPositionD() {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPositionD(DVec3 position) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends BodyCreationSettings {
        private D(MemorySegment handle) {
            super(handle);
        }

        @Override
        public FVec3 getPositionF() {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPositionF(FVec3 position) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public DVec3 getPositionD() {
            return DVec3.at(jolt.headers_d.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public void setPositionD(DVec3 position) {
            position.write(jolt.headers_d.JPC_BodyCreationSettings.position$slice(handle));
        }
    }
}
