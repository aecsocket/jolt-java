package jolt.math;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public record FVec3(float x, float y, float z) {
    public static final FVec3 ZERO = new FVec3(0.0f, 0.0f, 0.0f);

    public static FVec3 read(MemoryAddress address) {
        return new FVec3(
                address.getAtIndex(C_FLOAT, 0),
                address.getAtIndex(C_FLOAT, 1),
                address.getAtIndex(C_FLOAT, 2)
        );
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_FLOAT, 0, x);
        segment.setAtIndex(C_FLOAT, 1, y);
        segment.setAtIndex(C_FLOAT, 2, z);
    }

    public MemorySegment allocate(MemorySession session) {
        return session.allocateArray(C_FLOAT, x, y, z);
    }

    public FVec3 negate() {
        return new FVec3(-x, -y, -z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
