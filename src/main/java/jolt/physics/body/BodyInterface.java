package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.Activation;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers_f.JoltPhysicsC.*;

public abstract sealed class BodyInterface extends AddressedJoltNative
        permits BodyInterface.F, BodyInterface.D {
    public static BodyInterface at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(address) : new F(address);
    }

    private BodyInterface(MemoryAddress address) {
        super(address);
    }

    public Body createBody(BodyCreationSettings settings) {
        return Body.at(JPC_BodyInterface_CreateBody(address, settings.address()));
    }

    public void addBody(int bodyId, Activation activationMode) {
        JPC_BodyInterface_AddBody(address, bodyId, activationMode.ordinal());
    }

    public int createAndAddBody(BodyCreationSettings settings, Activation activationMode) {
        return JPC_BodyInterface_CreateAndAddBody(address, settings.address(), activationMode.ordinal());
    }

    public void removeBody(int bodyId) {
        JPC_BodyInterface_RemoveBody(address, bodyId);
    }

    public void destroyBody(int bodyId) {
        JPC_BodyInterface_DestroyBody(address, bodyId);
    }

    public void setLinearVelocity(int bodyId, FVec3 linearVelocity) {
        try (var session = MemorySession.openConfined()) {
            JPC_BodyInterface_SetLinearVelocity(address, bodyId, linearVelocity.allocate(session));
        }
    }

    public boolean isActive(int bodyId) {
        return JPC_BodyInterface_IsActive(address, bodyId);
    }

    public abstract FVec3 getCenterOfMassPositionF(int bodyId);

    public abstract DVec3 getCenterOfMassPositionD(int bodyId);

    public FVec3 getLinearVelocity(int bodyId) {
        try (var session = MemorySession.openConfined()) {
            var out = FVec3.ZERO.allocate(session);
            JPC_BodyInterface_GetLinearVelocity(address, bodyId, out);
            return FVec3.read(out);
        }
    }

    protected static final class F extends BodyInterface {
        private F(MemoryAddress address) {
            super(address);
        }

        @Override
        public FVec3 getCenterOfMassPositionF(int bodyId) {
            try (var session = MemorySession.openConfined()) {
                var out = FVec3.ZERO.allocate(session);
                jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassPosition(address, bodyId, out);
                return FVec3.read(out);
            }
        }

        @Override
        public DVec3 getCenterOfMassPositionD(int bodyId) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    protected static final class D extends BodyInterface {
        private D(MemoryAddress address) {
            super(address);
        }

        @Override
        public FVec3 getCenterOfMassPositionF(int bodyId) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public DVec3 getCenterOfMassPositionD(int bodyId) {
            try (var session = MemorySession.openConfined()) {
                var out = DVec3.ZERO.allocate(session);
                jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassPosition(address, bodyId, out);
                return DVec3.read(out.address());
            }
        }
    }
}
