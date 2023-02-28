package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.Activation;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class BodyInterface extends AddressedJoltNative {
    public static BodyInterface at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BodyInterface(address);
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

    public FVec3 getCenterOfMassPositionF(int bodyId) {
        Jolt.assertSinglePrecision();
        // TODO
        return FVec3.ZERO;
    }

    public DVec3 getCenterOfMassPositionD(int bodyId) {
        Jolt.assertDoublePrecision();
        // TODO
        return DVec3.ZERO;
    }

    public FVec3 getLinearVelocity(int bodyId) {
        try (var session = MemorySession.openConfined()) {
            MemorySegment out = FVec3.ZERO.allocate(session);
            JPC_BodyInterface_GetLinearVelocity(address, bodyId, out);
            return FVec3.read(out.address());
        }
    }
}
