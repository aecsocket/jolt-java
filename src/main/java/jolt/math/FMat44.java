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
    private static final long BYTES_SIZE = 16 * C_FLOAT.byteSize();
    private static final long TRANSLATION_OFFSET = 12 * C_FLOAT.byteSize();

    private final MemorySegment translation;

    private FMat44(MemorySegment handle) {
        super(handle);
        translation = handle.asSlice(TRANSLATION_OFFSET);
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
    //endregion PrimitiveJoltNative

    /*
    in the method definition: typical row-major matrix
    in the memory allocation: column-major, conforms to JPH
     */
    public static FMat44 of(
            SegmentAllocator alloc,
            float n00, float n01, float n02, float n03,
            float n10, float n11, float n12, float n13,
            float n20, float n21, float n22, float n23,
            float n30, float n31, float n32, float n33
    ) {
        return new FMat44(alloc.allocateArray(C_FLOAT,
                n00, n10, n20, n30,
                n01, n11, n21, n31,
                n02, n12, n22, n32,
                n03, n13, n23, n33
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

    public MemorySegment rotationSlice() {
        return handle.asSlice(0, TRANSLATION_OFFSET);
    }

    public MemorySegment translationSlice() {
        return handle.asSlice(TRANSLATION_OFFSET);
    }

    public float[] rotationComponents() {
        return rotationSlice().toArray(C_FLOAT);
    }

    public float[] translationComponents() {
        return translationSlice().toArray(C_FLOAT);
    }

    public float getRotation(int index) {
        return handle.getAtIndex(C_FLOAT, index);
    }

    public void setRotation(int index, float value) {
        handle.setAtIndex(C_FLOAT, index, value);
    }

    public float getTranslation(int index) {
        return translation.getAtIndex(C_FLOAT, index);
    }

    public void setTranslation(int index, float value) {
        translation.setAtIndex(C_FLOAT, index, value);
    }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 12; i++)
            setRotation(i, address.getAtIndex(C_FLOAT, i));

        MemoryAddress translation = address.addOffset(TRANSLATION_OFFSET);
        for (int i = 0; i < 4; i++)
            setTranslation(i, translation.getAtIndex(C_FLOAT, i));
    }

    public void read(FMat44 m) {
        read(m.address());
    }

    public void read(float[] rotation, float[] translation) {
        for (int i = 0; i < 3; i++)
            setRotation(i, rotation[i]);
        for (int i = 3; i < 6; i++)
            setRotation(i + 1, rotation[i]);
        for (int i = 6; i < 9; i++)
            setRotation(i + 2, rotation[i]);
        for (int i = 0; i < 3; i++)
            setTranslation(i, translation[i]);
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 12; i++)
            segment.setAtIndex(C_FLOAT, i, getRotation(i));

        MemorySegment translation = segment.asSlice(TRANSLATION_OFFSET);
        for (int i = 0; i < 4; i++)
            translation.setAtIndex(C_FLOAT, i, getTranslation(i));
    }

    public void write(MemorySegment rotation, MemorySegment translation) {
        for (int i = 0; i < 3; i++)
            rotation.setAtIndex(C_FLOAT, i, getRotation(i));
        for (int i = 3; i < 6; i++)
            rotation.setAtIndex(C_FLOAT, i, getRotation(i+1));
        for (int i = 6; i < 9; i++)
            rotation.setAtIndex(C_FLOAT, i, getRotation(i+2));
        for (int i = 0; i < 3; i++)
            translation.setAtIndex(C_FLOAT, i, getTranslation(i));
    }

    public boolean equalValue(FMat44 m) {
        float[] ourRotation = rotationComponents();
        float[] theirRotation = m.rotationComponents();
        for (int i = 0; i < 12; i++)
            if (Float.compare(ourRotation[i], theirRotation[i]) != 0)
                return false;

        float[] ourTranslation = translationComponents();
        float[] theirTranslation = m.translationComponents();
        for (int i = 0; i < 4; i++)
            if (Float.compare(ourTranslation[i], theirTranslation[i]) != 0)
                return false;

        return true;
    }

    @Override
    public String toString() {
        float[] rotation = rotationComponents();
        float[] translation = translationComponents();
        return """
                [
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                  %f %f %f %f
                ]""".formatted(
                rotation[ 0], rotation[ 4], rotation[ 8], translation[12],
                rotation[ 1], rotation[ 5], rotation[ 9], translation[13],
                rotation[ 2], rotation[ 6], rotation[10], translation[14],
                rotation[ 3], rotation[ 7], rotation[11], translation[15]
        );
    }
}
