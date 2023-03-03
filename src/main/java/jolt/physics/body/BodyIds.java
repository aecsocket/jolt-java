package jolt.physics.body;

import java.lang.foreign.MemoryAddress;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.C_INT;

public final class BodyIds {
    private BodyIds() {}

    public static int at(MemoryAddress address) {
        return address.get(C_INT, 0);
    }

    public static int[] arrayOf(Collection<? extends Integer> collection) {
        return collection.stream().mapToInt(x -> x).toArray();
    }
}
