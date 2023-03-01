package jolt.math;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public record DVec3(double x, double y, double z) {
    public static final DVec3 ZERO = new DVec3(0.0, 0.0, 0.0);

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
            var value = values[i];
            segment.setAtIndex(C_DOUBLE, i * 3L,     value.x);
            segment.setAtIndex(C_DOUBLE, i * 3L + 1, value.y);
            segment.setAtIndex(C_DOUBLE, i * 3L + 2, value.z);
        }
    }

    public MemorySegment allocate(MemorySession session) {
        return session.allocateArray(C_DOUBLE, x, y, z);
    }

    public static MemorySegment allocate(MemorySession session, DVec3... values) {
        double[] components = new double[values.length * 3];
        for (int i = 0; i < values.length; i++) {
            var value = values[i];
            components[i * 3    ] = value.x;
            components[i * 3 + 1] = value.y;
            components[i * 3 + 2] = value.z;
        }
        return session.allocateArray(C_DOUBLE, components);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
