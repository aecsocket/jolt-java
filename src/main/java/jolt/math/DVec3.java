package jolt.math;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public record DVec3(double x, double y, double z) {
    public static final DVec3 ZERO = new DVec3(0.0, 0.0, 0.0);
    public static final DVec3 ONE = new DVec3(1.0, 1.0, 1.0);

    public DVec3 negate() {
        return new DVec3(-x, -y, -z);
    }

    public static DVec3 read(Addressable addressable) {
        var address = addressable.address();
        return new DVec3(
                address.getAtIndex(C_DOUBLE, 0),
                address.getAtIndex(C_DOUBLE, 1),
                address.getAtIndex(C_DOUBLE, 2)
        );
    }

    public static DVec3[] read(Addressable addressable, int size) {
        var address = addressable.address();
        var result = new DVec3[size];
        for (int i = 0; i < size; i++) {
            result[i] = new DVec3(
                    address.getAtIndex(C_DOUBLE, i * 3L),
                    address.getAtIndex(C_DOUBLE, i * 3L + 1),
                    address.getAtIndex(C_DOUBLE, i * 3L + 2)
            );
        }
        return result;
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_DOUBLE, 0, x);
        segment.setAtIndex(C_DOUBLE, 1, y);
        segment.setAtIndex(C_DOUBLE, 2, z);
    }

    public static void write(MemorySegment segment, DVec3... values) {
        for (int i = 0; i < values.length; i++) {
            var v = values[i];
            segment.setAtIndex(C_DOUBLE, i * 3L,     v.x);
            segment.setAtIndex(C_DOUBLE, i * 3L + 1, v.y);
            segment.setAtIndex(C_DOUBLE, i * 3L + 2, v.z);
        }
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocateArray(C_DOUBLE, x, y, z);
    }

    public static MemorySegment allocate(SegmentAllocator allocator, DVec3... values) {
        double[] components = new double[values.length * 3];
        for (int i = 0; i < values.length; i++) {
            var v = values[i];
            components[i * 3    ] = v.x;
            components[i * 3 + 1] = v.y;
            components[i * 3 + 2] = v.z;
        }
        return allocator.allocateArray(C_DOUBLE, components);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
