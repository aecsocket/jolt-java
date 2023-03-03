package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public final class DMat44 extends SegmentedJoltNative {
    public static DMat44 at(MemorySegment segment) {
        return new DMat44(segment);
    }

    public static DMat44 at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(MemorySegment.ofAddress(ptr.address(), 16 * C_DOUBLE.byteSize(), session));
    }

    public static DMat44 create(
            MemorySession session,
            double n00, double n01, double n02, double n03,
            double n10, double n11, double n12, double n13,
            double n20, double n21, double n22, double n23,
            double n30, double n31, double n32, double n33
    ) {
        var segment = session.allocateArray(C_DOUBLE,
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        );
        return new DMat44(segment);
    }

    public static DMat44 create(MemorySession session, double s) {
        return create(session,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s
        );
    }

    public static DMat44 create(MemorySession session) {
        return create(session,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0
        );
    }

    public static DMat44 createIdentity(MemorySession session) {
        return create(session,
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        );
    }

    private DMat44(MemorySegment segment) {
        super(segment);
    }

    public double[] components() {
        return segment.toArray(C_DOUBLE);
    }

    public double get(int index) {
        return segment.getAtIndex(C_DOUBLE, index);
    }

    public void set(int index, double value) {
        segment.setAtIndex(C_DOUBLE, index, value);
    }

    public double get(int row, int col) {
        return segment.getAtIndex(C_DOUBLE, row * 4L + col);
    }

    public void set(int row, int col, double value) {
        segment.setAtIndex(C_DOUBLE, row * 4L + col, value);
    }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 16; i++) {
            set(i, address.getAtIndex(C_DOUBLE, i));
        }
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 16; i++) {
            segment.setAtIndex(C_DOUBLE, i, get(i));
        }
    }

    public void set(DMat44 m) {
        read(m.address());
    }

    public boolean equalValue(DMat44 v) {
        double[] ours = components();
        double[] theirs = v.components();
        for (int i = 0; i < 16; i++) {
            if (Double.compare(ours[i], theirs[i]) != 0)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        double[] values = components();
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
