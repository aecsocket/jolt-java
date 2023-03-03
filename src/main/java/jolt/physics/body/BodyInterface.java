package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.Activation;

import java.lang.foreign.MemoryAddress;

import static jolt.headers_f.JoltPhysicsC.*;

public abstract sealed class BodyInterface extends AddressedJoltNative
        permits BodyInterface.F, BodyInterface.D {
    // START Jolt-Pointer-FD
    private BodyInterface(MemoryAddress handle) {
        super(handle);
    }

    public static BodyInterface at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(addr) : new F(addr);
    }
    // END Jolt-Pointer-FD

    public Body createBody(BodyCreationSettings settings) {
        return Body.at(JPC_BodyInterface_CreateBody(handle, settings.address()));
    }

    public void addBody(int bodyId, Activation activationMode) {
        JPC_BodyInterface_AddBody(handle, bodyId, activationMode.ordinal());
    }

    public int createAndAddBody(BodyCreationSettings settings, Activation activationMode) {
        return JPC_BodyInterface_CreateAndAddBody(handle, settings.address(), activationMode.ordinal());
    }

    public void removeBody(int bodyId) {
        JPC_BodyInterface_RemoveBody(handle, bodyId);
    }

    public void destroyBody(int bodyId) {
        JPC_BodyInterface_DestroyBody(handle, bodyId);
    }

    public void setLinearVelocity(int bodyId, FVec3 linearVelocity) {
        JPC_BodyInterface_SetLinearVelocity(handle, bodyId, linearVelocity.address());
    }

    public boolean isActive(int bodyId) {
        return JPC_BodyInterface_IsActive(handle, bodyId);
    }

    public abstract void getCenterOfMassPosition(int bodyId, FVec3 out);

    public abstract void getCenterOfMassPosition(int bodyId, DVec3 out);

    public void getLinearVelocity(int bodyId, FVec3 out) {
        JPC_BodyInterface_GetLinearVelocity(handle, bodyId, out.address());
    }

    protected static final class F extends BodyInterface {
        private F(MemoryAddress address) {
            super(address);
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassPosition(handle, bodyId, out.address());
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    protected static final class D extends BodyInterface {
        private D(MemoryAddress address) {
            super(address);
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassPosition(handle, bodyId, out.address());
        }
    }
}
