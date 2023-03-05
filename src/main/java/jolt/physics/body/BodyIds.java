package jolt.physics.body;

import java.lang.foreign.MemoryAddress;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.C_INT;

public final class BodyIds {
    private BodyIds() {}

    public static final int INVALID_BODY_ID = 0xffffffff;
    public static final int MAX_BODY_INDEX = 0x7fffff;

    public static int at(MemoryAddress address) {
        return address.get(C_INT, 0);
    }

    public static int index(int id) {
        return id & MAX_BODY_INDEX;
    }

    public static byte sequenceNumber(int id) {
        return (byte) (id >> 24);
    }

    public static boolean valid(int id) {
        return id != INVALID_BODY_ID;
    }

    public static String asString(int id) {
        return index(id) + "/" + sequenceNumber(id);
    }

    public static int[] arrayOf(Collection<? extends Integer> collection) {
        return collection.stream().mapToInt(x -> x).toArray();
    }
}
