package jolt.physics.body;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.C_INT;

public final class BodyIds {
    private BodyIds() {}

    public static int read(MemoryAddress address) {
        return address.get(C_INT, 0);
    }
}
