package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public final class Quat extends SegmentedJoltNative {
    private static final int NUM_COMPONENTS = 4;
    private static final long BYTES_SIZE = NUM_COMPONENTS * C_FLOAT.byteSize();

    //region PrimitiveJoltNative
    private Quat(MemorySegment handle) {
        super(handle);
    }

    public static Quat at(MemorySegment segment) {
        return new Quat(segment);
    }

    public static Quat at(MemorySession arena, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new Quat(MemorySegment.ofAddress(addr, BYTES_SIZE, arena));
    }

    public static Quat of(SegmentAllocator alloc) {
        return new Quat(alloc.allocate(BYTES_SIZE));
    }
    //endregion PrimitiveJoltNative

    public static Quat of(SegmentAllocator alloc, float x, float y, float z, float w) {
        return new Quat(alloc.allocateArray(C_FLOAT, x, y, z, w));
    }

    public static Quat ofIdentity(SegmentAllocator alloc) {
        return of(alloc, 0.0f, 0.0f, 0.0f, 1.0f);
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

    public float getW() { return get(3); }
    public void setW(float w) { set(3, w); }

    public void read(MemoryAddress address) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            set(i, address.getAtIndex(C_FLOAT, i));
        }
    }

    public void read(Quat q) {
        read(q.address());
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            segment.setAtIndex(C_FLOAT, i, get(i));
        }
    }

    @Override
    public String toString() {
        return "(%f, %f, %f, %f)".formatted(getX(), getY(), getZ(), getW());
    }
}
