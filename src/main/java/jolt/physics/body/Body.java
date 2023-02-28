package jolt.physics.body;

import jolt.AbstractJoltNative;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class Body extends AbstractJoltNative {
    public static Body at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new Body(address);
    }

    private Body(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void destroyInternal() {}

    public int getId() {
        return JPC_Body_GetID(address);
    }
}
