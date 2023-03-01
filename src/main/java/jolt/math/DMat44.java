package jolt.math;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public record DMat44(
        double n00, double n01, double n02, double n03,
        double n10, double n11, double n12, double n13,
        double n20, double n21, double n22, double n23,
        double n30, double n31, double n32, double n33
) {
    public static final DMat44 ZERO = new DMat44(
            0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f
    );
    public static final DMat44 IDENTITY = new DMat44(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    );

    public static DMat44 read(Addressable addressable) {
        var address = addressable.address();
        return new DMat44(
                address.getAtIndex(C_DOUBLE,  0), address.getAtIndex(C_DOUBLE,  1), address.getAtIndex(C_DOUBLE,  2), address.getAtIndex(C_DOUBLE,  3),
                address.getAtIndex(C_DOUBLE,  4), address.getAtIndex(C_DOUBLE,  5), address.getAtIndex(C_DOUBLE,  6), address.getAtIndex(C_DOUBLE,  7),
                address.getAtIndex(C_DOUBLE,  8), address.getAtIndex(C_DOUBLE,  9), address.getAtIndex(C_DOUBLE, 10), address.getAtIndex(C_DOUBLE, 11),
                address.getAtIndex(C_DOUBLE, 12), address.getAtIndex(C_DOUBLE, 13), address.getAtIndex(C_DOUBLE, 14), address.getAtIndex(C_DOUBLE, 15)
        );
    }

    public static DMat44[] read(Addressable addressable, int size) {
        var address = addressable.address();
        var result = new DMat44[size];
        for (int i = 0; i < size; i++) {
            result[i] = new DMat44(
                    address.getAtIndex(C_DOUBLE, i * 16L     ), address.getAtIndex(C_DOUBLE, i * 16L +  1), address.getAtIndex(C_DOUBLE, i * 16L +  2), address.getAtIndex(C_DOUBLE, i * 16L +  3),
                    address.getAtIndex(C_DOUBLE, i * 16L +  4), address.getAtIndex(C_DOUBLE, i * 16L +  5), address.getAtIndex(C_DOUBLE, i * 16L +  6), address.getAtIndex(C_DOUBLE, i * 16L +  7),
                    address.getAtIndex(C_DOUBLE, i * 16L +  8), address.getAtIndex(C_DOUBLE, i * 16L +  9), address.getAtIndex(C_DOUBLE, i * 16L + 10), address.getAtIndex(C_DOUBLE, i * 16L + 11),
                    address.getAtIndex(C_DOUBLE, i * 16L + 12), address.getAtIndex(C_DOUBLE, i * 16L + 13), address.getAtIndex(C_DOUBLE, i * 16L + 14), address.getAtIndex(C_DOUBLE, i * 16L + 15)
            );
        }
        return result;
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_DOUBLE,  0, n00); segment.setAtIndex(C_DOUBLE,  1, n01); segment.setAtIndex(C_DOUBLE,  2, n02); segment.setAtIndex(C_DOUBLE,  3, n03);
        segment.setAtIndex(C_DOUBLE,  4, n10); segment.setAtIndex(C_DOUBLE,  5, n11); segment.setAtIndex(C_DOUBLE,  6, n12); segment.setAtIndex(C_DOUBLE,  7, n13);
        segment.setAtIndex(C_DOUBLE,  8, n20); segment.setAtIndex(C_DOUBLE,  9, n21); segment.setAtIndex(C_DOUBLE, 10, n22); segment.setAtIndex(C_DOUBLE, 11, n23);
        segment.setAtIndex(C_DOUBLE, 12, n30); segment.setAtIndex(C_DOUBLE, 13, n31); segment.setAtIndex(C_DOUBLE, 14, n32); segment.setAtIndex(C_DOUBLE, 15, n33);
    }

    public static void write(MemorySegment segment, DMat44... values) {
        for (int i = 0; i < values.length; i++) {
            var m = values[i];
            segment.setAtIndex(C_DOUBLE, i * 16L     , m.n00); segment.setAtIndex(C_DOUBLE, i * 16L +  1, m.n01); segment.setAtIndex(C_DOUBLE, i * 16L +  2, m.n02); segment.setAtIndex(C_DOUBLE, i * 16L +  3, m.n03);
            segment.setAtIndex(C_DOUBLE, i * 16L +  4, m.n10); segment.setAtIndex(C_DOUBLE, i * 16L +  5, m.n11); segment.setAtIndex(C_DOUBLE, i * 16L +  6, m.n12); segment.setAtIndex(C_DOUBLE, i * 16L +  7, m.n13);
            segment.setAtIndex(C_DOUBLE, i * 16L +  8, m.n20); segment.setAtIndex(C_DOUBLE, i * 16L +  9, m.n21); segment.setAtIndex(C_DOUBLE, i * 16L + 10, m.n22); segment.setAtIndex(C_DOUBLE, i * 16L + 11, m.n23);
            segment.setAtIndex(C_DOUBLE, i * 16L + 12, m.n30); segment.setAtIndex(C_DOUBLE, i * 16L + 13, m.n31); segment.setAtIndex(C_DOUBLE, i * 16L + 14, m.n32); segment.setAtIndex(C_DOUBLE, i * 16L + 15, m.n33);
        }
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocateArray(C_DOUBLE,
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        );
    }

    public static MemorySegment allocate(SegmentAllocator allocator, DMat44... values) {
        double[] components = new double[values.length * 3];
        for (int i = 0; i < values.length; i++) {
            var m = values[i];
            components[i * 16     ] = m.n00; components[i * 16 +  1] = m.n01; components[i * 16 +  2] = m.n02; components[i * 16 +  3] = m.n03;
            components[i * 16 +  4] = m.n10; components[i * 16 +  5] = m.n11; components[i * 16 +  6] = m.n12; components[i * 16 +  7] = m.n13;
            components[i * 16 +  8] = m.n20; components[i * 16 +  9] = m.n21; components[i * 16 + 10] = m.n22; components[i * 16 + 11] = m.n23;
            components[i * 16 + 12] = m.n30; components[i * 16 + 13] = m.n31; components[i * 16 + 14] = m.n32; components[i * 16 + 15] = m.n33;
        }
        return allocator.allocateArray(C_DOUBLE, components);
    }

    @Override
    public String toString() {
        return """
                [
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                ]""".formatted(
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        );
    }
}
