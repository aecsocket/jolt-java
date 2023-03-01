package jolt.math;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public record FMat44(
        float n00, float n01, float n02, float n03,
        float n10, float n11, float n12, float n13,
        float n20, float n21, float n22, float n23,
        float n30, float n31, float n32, float n33
) {
    public static final FMat44 ZERO = new FMat44(
            0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 0.0f
    );
    public static final FMat44 IDENTITY = new FMat44(
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    );

    public static FMat44 read(Addressable addressable) {
        var address = addressable.address();
        return new FMat44(
                address.getAtIndex(C_FLOAT,  0), address.getAtIndex(C_FLOAT,  1), address.getAtIndex(C_FLOAT,  2), address.getAtIndex(C_FLOAT,  3),
                address.getAtIndex(C_FLOAT,  4), address.getAtIndex(C_FLOAT,  5), address.getAtIndex(C_FLOAT,  6), address.getAtIndex(C_FLOAT,  7),
                address.getAtIndex(C_FLOAT,  8), address.getAtIndex(C_FLOAT,  9), address.getAtIndex(C_FLOAT, 10), address.getAtIndex(C_FLOAT, 11),
                address.getAtIndex(C_FLOAT, 12), address.getAtIndex(C_FLOAT, 13), address.getAtIndex(C_FLOAT, 14), address.getAtIndex(C_FLOAT, 15)
        );
    }

    public static FMat44[] read(Addressable addressable, int size) {
        var address = addressable.address();
        var result = new FMat44[size];
        for (int i = 0; i < size; i++) {
            result[i] = new FMat44(
                    address.getAtIndex(C_FLOAT, i * 16L     ), address.getAtIndex(C_FLOAT, i * 16L +  1), address.getAtIndex(C_FLOAT, i * 16L +  2), address.getAtIndex(C_FLOAT, i * 16L +  3),
                    address.getAtIndex(C_FLOAT, i * 16L +  4), address.getAtIndex(C_FLOAT, i * 16L +  5), address.getAtIndex(C_FLOAT, i * 16L +  6), address.getAtIndex(C_FLOAT, i * 16L +  7),
                    address.getAtIndex(C_FLOAT, i * 16L +  8), address.getAtIndex(C_FLOAT, i * 16L +  9), address.getAtIndex(C_FLOAT, i * 16L + 10), address.getAtIndex(C_FLOAT, i * 16L + 11),
                    address.getAtIndex(C_FLOAT, i * 16L + 12), address.getAtIndex(C_FLOAT, i * 16L + 13), address.getAtIndex(C_FLOAT, i * 16L + 14), address.getAtIndex(C_FLOAT, i * 16L + 15)
            );
        }
        return result;
    }

    public void write(MemorySegment segment) {
        segment.setAtIndex(C_FLOAT,  0, n00); segment.setAtIndex(C_FLOAT,  1, n01); segment.setAtIndex(C_FLOAT,  2, n02); segment.setAtIndex(C_FLOAT,  3, n03);
        segment.setAtIndex(C_FLOAT,  4, n10); segment.setAtIndex(C_FLOAT,  5, n11); segment.setAtIndex(C_FLOAT,  6, n12); segment.setAtIndex(C_FLOAT,  7, n13);
        segment.setAtIndex(C_FLOAT,  8, n20); segment.setAtIndex(C_FLOAT,  9, n21); segment.setAtIndex(C_FLOAT, 10, n22); segment.setAtIndex(C_FLOAT, 11, n23);
        segment.setAtIndex(C_FLOAT, 12, n30); segment.setAtIndex(C_FLOAT, 13, n31); segment.setAtIndex(C_FLOAT, 14, n32); segment.setAtIndex(C_FLOAT, 15, n33);
    }

    public static void write(MemorySegment segment, FMat44... values) {
        for (int i = 0; i < values.length; i++) {
            var m = values[i];
            segment.setAtIndex(C_FLOAT, i * 16L     , m.n00); segment.setAtIndex(C_FLOAT, i * 16L +  1, m.n01); segment.setAtIndex(C_FLOAT, i * 16L +  2, m.n02); segment.setAtIndex(C_FLOAT, i * 16L +  3, m.n03);
            segment.setAtIndex(C_FLOAT, i * 16L +  4, m.n10); segment.setAtIndex(C_FLOAT, i * 16L +  5, m.n11); segment.setAtIndex(C_FLOAT, i * 16L +  6, m.n12); segment.setAtIndex(C_FLOAT, i * 16L +  7, m.n13);
            segment.setAtIndex(C_FLOAT, i * 16L +  8, m.n20); segment.setAtIndex(C_FLOAT, i * 16L +  9, m.n21); segment.setAtIndex(C_FLOAT, i * 16L + 10, m.n22); segment.setAtIndex(C_FLOAT, i * 16L + 11, m.n23);
            segment.setAtIndex(C_FLOAT, i * 16L + 12, m.n30); segment.setAtIndex(C_FLOAT, i * 16L + 13, m.n31); segment.setAtIndex(C_FLOAT, i * 16L + 14, m.n32); segment.setAtIndex(C_FLOAT, i * 16L + 15, m.n33);
        }
    }

    public MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocateArray(C_FLOAT,
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        );
    }

    public static MemorySegment allocate(SegmentAllocator allocator, FMat44... values) {
        float[] components = new float[values.length * 3];
        for (int i = 0; i < values.length; i++) {
            var m = values[i];
            components[i * 16     ] = m.n00; components[i * 16 +  1] = m.n01; components[i * 16 +  2] = m.n02; components[i * 16 +  3] = m.n03;
            components[i * 16 +  4] = m.n10; components[i * 16 +  5] = m.n11; components[i * 16 +  6] = m.n12; components[i * 16 +  7] = m.n13;
            components[i * 16 +  8] = m.n20; components[i * 16 +  9] = m.n21; components[i * 16 + 10] = m.n22; components[i * 16 + 11] = m.n23;
            components[i * 16 + 12] = m.n30; components[i * 16 + 13] = m.n31; components[i * 16 + 14] = m.n32; components[i * 16 + 15] = m.n33;
        }
        return allocator.allocateArray(C_FLOAT, components);
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
