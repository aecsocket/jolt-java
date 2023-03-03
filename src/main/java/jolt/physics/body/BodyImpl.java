package jolt.physics.body;

import jolt.AddressedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.*;

final class BodyImpl extends AddressedJoltNative implements MutableBody {
    BodyImpl(MemoryAddress handle) {
        super(handle);
    }

    @Override
    public int getId() {
        return JPC_Body_GetID(handle);
    }
}
