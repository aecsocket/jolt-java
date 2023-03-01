package jolt.math;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public record FVec3(float x, float y, float z) {
    public static final FVec3 ZERO = new FVec3(0.0f, 0.0f, 0.0f);

    public FVec3 negate() {
        return new FVec3(-x, -y, -z);
    }

    public static FVec3 read(Addressable addressable) {
        var address = addressable.address();
        return new FVec3(
                address.getAtIndex(C_FLOAT, 0),
                address.getAtIndex(C_FLOAT, 1),
                address.getAtIndex(C_FLOAT, 2)
        );
    }

    public static FVec3[] read(Addressable addressable, int size) {
        var address = addressable.address();
        var result = new FVec3[size];
        for (int i = 0; i < size; i++) {
            result[i] = new FVec3(
                    address.getAtIndex(C_FLOAT, i * 3L),
                    address.getAtIndex(C_FLOAT, i * 3L + 1),
                    address.getAtIndex(C_FLOAT, i * 3L + 2)
            );
        }
        return result;
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_FLOAT, 0, x);
        segment.setAtIndex(C_FLOAT, 1, y);
        segment.setAtIndex(C_FLOAT, 2, z);
    }

    public static void write(MemorySegment segment, FVec3... values) {
        for (int i = 0; i < values.length; i++) {
            var v = values[i];
            segment.setAtIndex(C_FLOAT, i * 3L,     v.x);
            segment.setAtIndex(C_FLOAT, i * 3L + 1, v.y);
            segment.setAtIndex(C_FLOAT, i * 3L + 2, v.z);
        }
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocateArray(C_FLOAT, x, y, z);
    }

    public static MemorySegment allocate(SegmentAllocator allocator, FVec3... values) {
        float[] components = new float[values.length * 3];
        for (int i = 0; i < values.length; i++) {
            var v = values[i];
            components[i * 3    ] = v.x;
            components[i * 3 + 1] = v.y;
            components[i * 3 + 2] = v.z;
        }
        return allocator.allocateArray(C_FLOAT, components);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
