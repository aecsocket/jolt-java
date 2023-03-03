package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

/* column-major, indices:
[
   0  4  8 12
   1  5  9 13
   2  6 10 14
   3  7 11 15
]
 */
public final class FMat44 extends SegmentedJoltNative {
    private static final int NUM_COMPONENTS = 16;
    private static final long BYTES_SIZE = NUM_COMPONENTS * C_FLOAT.byteSize();

    // START PrimitiveJoltNative
    private FMat44(MemorySegment handle) {
        super(handle);
    }

    public static FMat44 at(MemorySegment segment) {
        return new FMat44(segment);
    }

    public static FMat44 at(MemorySession arena, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new FMat44(MemorySegment.ofAddress(addr.address(), BYTES_SIZE, arena));
    }

    public static FMat44 of(SegmentAllocator alloc) {
        return new FMat44(alloc.allocate(BYTES_SIZE));
    }
    // END PrimitiveJoltNative

    public static FMat44 of(
            SegmentAllocator alloc,
            float n00, float n01, float n02, float n03,
            float n10, float n11, float n12, float n13,
            float n20, float n21, float n22, float n23,
            float n30, float n31, float n32, float n33
    ) {
        return new FMat44(alloc.allocateArray(C_FLOAT,
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        ));
    }

    public static FMat44 of(SegmentAllocator alloc, float s) {
        return of(alloc,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s,
                s, s, s, s);
    }


    public static FMat44 ofIdentity(SegmentAllocator alloc) {
        return of(alloc,
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f);
    }

    public static MemorySegment ofArray(SegmentAllocator alloc, FMat44... values) {
        var segment = alloc.allocate(values.length * BYTES_SIZE);
        for (int i = 0; i < values.length; i++) {
            values[i].write(segment.asSlice(i * BYTES_SIZE));
        }
        return segment;
    }

    public float[] components() {
        return handle.toArray(C_FLOAT);
    }

    public float get(int index) {
        return handle.getAtIndex(C_FLOAT, index);
    }

    public void set(int index, float value) {
        handle.setAtIndex(C_FLOAT, index, value);
    }

    public float get(int row, int col) {
        return handle.getAtIndex(C_FLOAT, col * 4L + row);
    }

    public void set(int row, int col, float value) {
        handle.setAtIndex(C_FLOAT, col * 4L + row, value);
    }

    public void read(MemoryAddress address) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            set(i, address.getAtIndex(C_FLOAT, i));
        }
    }

    public void read(FMat44 m) {
        read(m.address());
    }

    public void read(float[] rotation, float[] translation) {
        set( 0, rotation[0]); set( 4, rotation[3]); set( 8, rotation[6]); set(12, translation[0]);
        set( 1, rotation[1]); set( 5, rotation[4]); set( 9, rotation[7]); set(13, translation[1]);
        set( 2, rotation[2]); set( 6, rotation[5]); set(10, rotation[8]); set(14, translation[2]);
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            segment.setAtIndex(C_FLOAT, i, get(i));
        }
    }

    public boolean equalValue(FMat44 m) {
        float[] ours = components();
        float[] theirs = m.components();
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            if (Float.compare(ours[i], theirs[i]) != 0)
                return false;
        }
        return true;
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
                values[ 0], values[ 4], values[ 8], values[12],
                values[ 1], values[ 5], values[ 9], values[13],
                values[ 2], values[ 6], values[10], values[14],
                values[ 3], values[ 7], values[11], values[15]
        );
    }
}
