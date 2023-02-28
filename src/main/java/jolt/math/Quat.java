package jolt.math;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public record Quat(float x, float y, float z, float w) {
    public static final Quat IDENTITY = new Quat(0f, 0f, 0f, 0f);

    public static Quat read(MemoryAddress address) {
        return new Quat(
                address.getAtIndex(C_FLOAT, 0),
                address.getAtIndex(C_FLOAT, 1),
                address.getAtIndex(C_FLOAT, 2),
                address.getAtIndex(C_FLOAT, 3)
        );
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_FLOAT, 0, x);
        segment.setAtIndex(C_FLOAT, 1, y);
        segment.setAtIndex(C_FLOAT, 2, z);
        segment.setAtIndex(C_FLOAT, 3, w);
    }

    public MemorySegment allocate(MemorySession session) {
        return session.allocateArray(C_FLOAT, x, y, z, w);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
