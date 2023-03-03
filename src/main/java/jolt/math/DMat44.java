package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

/* column-major, indices:
[
   0  4  8 12
   1  5  9 13
   2  6 10 14
   3  7 11 15
]
 */
public final class DMat44 extends SegmentedJoltNative {
    private static final int NUM_COMPONENTS = 16;
    private static final long BYTES_SIZE = NUM_COMPONENTS * C_DOUBLE.byteSize();

    // START PrimitiveJoltNative
    private DMat44(MemorySegment handle) {
        super(handle);
    }

    public static DMat44 at(MemorySegment segment) {
        return new DMat44(segment);
    }

    public static DMat44 at(MemorySession arena, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new DMat44(MemorySegment.ofAddress(addr.address(), BYTES_SIZE, arena));
    }

    public static DMat44 of(SegmentAllocator alloc) {
        return new DMat44(alloc.allocate(BYTES_SIZE));
    }
    // END PrimitiveJoltNative

    public static DMat44 of(
            SegmentAllocator alloc,
            double n00, double n01, double n02, double n03,
            double n10, double n11, double n12, double n13,
            double n20, double n21, double n22, double n23,
            double n30, double n31, double n32, double n33
    ) {
        return new DMat44(alloc.allocateArray(C_DOUBLE,
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        ));
    }

    public static DMat44 of(SegmentAllocator alloc, double s) {
        return of(alloc,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s);
    }
    
    
    public static DMat44 ofIdentity(SegmentAllocator alloc) {
        return of(alloc,
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 1.0);
    }

    public static MemorySegment ofArray(SegmentAllocator alloc, DMat44... values) {
        var segment = alloc.allocate(values.length * BYTES_SIZE);
        for (int i = 0; i < values.length; i++) {
            values[i].write(segment.asSlice(i * BYTES_SIZE));
        }
        return segment;
    }

    public double[] components() {
        return handle.toArray(C_DOUBLE);
    }

    public double get(int index) {
        return handle.getAtIndex(C_DOUBLE, index);
    }

    public void set(int index, double value) {
        handle.setAtIndex(C_DOUBLE, index, value);
    }

    public double get(int row, int col) {
        return handle.getAtIndex(C_DOUBLE, col * 4L + row);
    }

    public void set(int row, int col, double value) {
        handle.setAtIndex(C_DOUBLE, col * 4L + row, value);
    }

    public void read(MemoryAddress address) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            set(i, address.getAtIndex(C_DOUBLE, i));
        }
    }

    public void read(DMat44 m) {
        read(m.address());
    }

    public void read(float[] rotation, double[] translation) {
        set( 0, rotation[0]); set( 4, rotation[3]); set( 8, rotation[6]); set(12, translation[0]);
        set( 1, rotation[1]); set( 5, rotation[4]); set( 9, rotation[7]); set(13, translation[1]);
        set( 2, rotation[2]); set( 6, rotation[5]); set(10, rotation[8]); set(14, translation[2]);
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            segment.setAtIndex(C_DOUBLE, i, get(i));
        }
    }

    public boolean equalValue(DMat44 m) {
        double[] ours = components();
        double[] theirs = m.components();
        for (int i = 0; i < NUM_COMPONENTS; i++) {
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
                values[ 0], values[ 4], values[ 8], values[12],
                values[ 1], values[ 5], values[ 9], values[13],
                values[ 2], values[ 6], values[10], values[14],
                values[ 3], values[ 7], values[11], values[15]
        );
    }
}
