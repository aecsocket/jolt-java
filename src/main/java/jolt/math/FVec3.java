package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.*;

public final class FVec3 extends SegmentedJoltNative {
    public static FVec3 at(MemorySegment segment) {
        return new FVec3(segment);
    }

    public static FVec3 at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(MemorySegment.ofAddress(ptr.address(), 3 * C_FLOAT.byteSize(), session));
    }

    public static FVec3 create(MemorySession session, float x, float y, float z) {
        var segment = session.allocateArray(C_FLOAT, x, y, z);
        return new FVec3(segment);
    }

    public static FVec3 create(MemorySession session, float s) {
        return create(session, s, s, s);
    }

    public static FVec3 create(MemorySession session) {
        return create(session, 0.0f, 0.0f, 0.0f);
    }

    private FVec3(MemorySegment segment) {
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

    public float getX() { return get(0); }
    public void setX(float x) { set(0, x); }

    public float getY() { return get(1); }
    public void setY(float y) { set(1, y); }

    public float getZ() { return get(2); }
    public void setZ(float z) { set(2, z); }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 3; i++) {
            set(i, address.getAtIndex(C_FLOAT, i));
        }
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 3; i++) {
            segment.setAtIndex(C_FLOAT, i, get(i));
        }
    }

    public void set(FVec3 v) {
        read(v.address());
    }

    @Override
    public String toString() {
        return "(%f, %f, %f)".formatted(getX(), getY(), getZ());
    }

    public static MemorySegment allocate(SegmentAllocator allocator, FVec3... values) {
        var segment = allocator.allocate(values.length * 3 * C_FLOAT.byteSize());
        for (int i = 0; i < values.length; i++) {
            values[i].write(segment.asSlice(i * 3 * C_FLOAT.byteSize()));
        }
        return segment;
    }
}
