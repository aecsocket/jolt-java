package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public final class FMat44 extends SegmentedJoltNative {
    public static FMat44 at(MemorySegment segment) {
        return new FMat44(segment);
    }

    public static FMat44 at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(MemorySegment.ofAddress(ptr.address(), 16 * C_FLOAT.byteSize(), session));
    }

    public static FMat44 create(
            MemorySession session,
            float n00, float n01, float n02, float n03,
            float n10, float n11, float n12, float n13,
            float n20, float n21, float n22, float n23,
            float n30, float n31, float n32, float n33
    ) {
        var segment = session.allocateArray(C_FLOAT,
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        );
        return new FMat44(segment);
    }

    public static FMat44 create(MemorySession session, float s) {
        return create(session,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s
        );
    }

    public static FMat44 create(MemorySession session) {
        return create(session,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f
        );
    }

    public static FMat44 createIdentity(MemorySession session) {
        return create(session,
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    private FMat44(MemorySegment segment) {
        super(segment);
    }

    public float[] components() {
        return segment.toArray(C_FLOAT);
    }

    public float get(int index) {
        return segment.getAtIndex(C_FLOAT, index);
    }

    public void set(int index, float value) {
        segment.setAtIndex(C_FLOAT, index, value);
    }

    public float get(int row, int col) {
        return segment.getAtIndex(C_FLOAT, row * 4L + col);
    }

    public void set(int row, int col, float value) {
        segment.setAtIndex(C_FLOAT, row * 4L + col, value);
    }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 16; i++) {
            set(i, address.getAtIndex(C_FLOAT, i));
        }
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 16; i++) {
            segment.setAtIndex(C_FLOAT, i, get(i));
        }
    }

    public void set(FMat44 m) {
        read(m.address());
    }

    @Override
    public String toString() {
        float[] values = components();
        return """
                [
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                ]""".formatted(
                values[ 0], values[ 1], values[ 2], values[ 3],
                values[ 4], values[ 5], values[ 6], values[ 7],
                values[ 8], values[ 9], values[10], values[11],
                values[12], values[13], values[14], values[15]
        );
    }
}
