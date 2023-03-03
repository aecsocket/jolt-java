package jolt.physics.body;

import jolt.AddressedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.*;

public final class Body extends AddressedJoltNative {
    // START Jolt-Pointer
    private Body(MemoryAddress handle) {
        super(handle);
    }

    public static Body at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new Body(addr);
    }
    // END Jolt-Pointer

    public int getID() {
        return JPC_Body_GetID(handle);
    }
}
