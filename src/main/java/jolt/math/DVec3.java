package jolt.math;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public record DVec3(double x, double y, double z) {
    public static final DVec3 ZERO = new DVec3(0.0, 0.0, 0.0);

    public static DVec3 read(MemoryAddress address) {
        return new DVec3(
                address.getAtIndex(C_DOUBLE, 0),
                address.getAtIndex(C_DOUBLE, 1),
                address.getAtIndex(C_DOUBLE, 2)
        );
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_DOUBLE, 0, x);
        segment.setAtIndex(C_DOUBLE, 1, y);
        segment.setAtIndex(C_DOUBLE, 2, z);
    }

    public MemorySegment allocate(MemorySession session) {
        return session.allocateArray(C_DOUBLE, x, y, z);
    }

    public DVec3 negate() {
        return new DVec3(-x, -y, -z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
