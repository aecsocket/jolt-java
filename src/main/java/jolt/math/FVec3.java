package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.*;

public final class FVec3 extends SegmentedJoltNative {
    private static final int NUM_COMPONENTS = 3;
    private static final long BYTES_SIZE = NUM_COMPONENTS * C_FLOAT.byteSize();

    // START PrimitiveJoltNative
    private FVec3(MemorySegment handle) {
        super(handle);
    }

    public static FVec3 at(MemorySegment segment) {
        return new FVec3(segment);
    }

    public static FVec3 at(MemorySession arena, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new FVec3(MemorySegment.ofAddress(addr, BYTES_SIZE, arena));
    }

    public static FVec3 of(SegmentAllocator alloc) {
        return new FVec3(alloc.allocate(BYTES_SIZE));
    }
    // END PrimitiveJoltNative

    public static FVec3 of(SegmentAllocator alloc, float x, float y, float z) {
        return new FVec3(alloc.allocateArray(C_FLOAT, x, y, z));
    }

    public static FVec3 of(SegmentAllocator alloc, float s) {
        return of(alloc, s, s, s);
    }

    public static MemorySegment ofArray(SegmentAllocator alloc, FVec3... values) {
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

    public float getX() { return get(0); }
    public void setX(float x) { set(0, x); }

    public float getY() { return get(1); }
    public void setY(float y) { set(1, y); }

    public float getZ() { return get(2); }
    public void setZ(float z) { set(2, z); }

    public void read(MemoryAddress address) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            set(i, address.getAtIndex(C_FLOAT, i));
        }
    }

    public void read(FVec3 v) {
        read(v.address());
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            segment.setAtIndex(C_FLOAT, i, get(i));
        }
    }

    public boolean equalValue(FVec3 v) {
        float[] ours = components();
        float[] theirs = v.components();
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            if (Float.compare(ours[i], theirs[i]) != 0)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(%f, %f, %f)".formatted(getX(), getY(), getZ());
    }
}
