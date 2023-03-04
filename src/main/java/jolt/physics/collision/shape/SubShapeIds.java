package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.C_INT;

public final class SubShapeIds {
    private SubShapeIds() {}

    public static int at(MemoryAddress address) {
        return address.get(C_INT, 0);
    }
}
