package jolt.math;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public record Quat(float x, float y, float z, float w) {
    public static final Quat IDENTITY = new Quat(0f, 0f, 0f, 0f);

    public static Quat read(Addressable addressable) {
        var address = addressable.address();
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

    public MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocateArray(C_FLOAT, x, y, z, w);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
