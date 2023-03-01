package jolt.physics.body;

import jolt.AddressedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class Body extends AddressedJoltNative {
    public static Body at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new Body(address);
    }

    private Body(MemoryAddress address) {
        super(address);
    }

    public int getId() {
        return JPC_Body_GetID(address);
    }
}
