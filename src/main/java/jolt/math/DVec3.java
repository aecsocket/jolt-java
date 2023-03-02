package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public final class DVec3 extends SegmentedJoltNative {
    public static DVec3 at(MemorySegment segment) {
        return new DVec3(segment);
    }

    public static DVec3 at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(MemorySegment.ofAddress(ptr.address(), 3 * C_DOUBLE.byteSize(), session));
    }

    public static DVec3 create(MemorySession session, double x, double y, double z) {
        var segment = session.allocateArray(C_DOUBLE, x, y, z);
        return new DVec3(segment);
    }

    public static DVec3 create(MemorySession session, double s) {
        return create(session, s, s, s);
    }

    public static DVec3 create(MemorySession session) {
        return create(session, 0.0, 0.0, 0.0);
    }

    private DVec3(MemorySegment segment) {
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

    public double getX() { return get(0); }
    public void setX(double x) { set(0, x); }

    public double getY() { return get(1); }
    public void setY(double y) { set(1, y); }

    public double getZ() { return get(2); }
    public void setZ(double z) { set(2, z); }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 3; i++) {
            set(i, address.getAtIndex(C_DOUBLE, i));
        }
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 3; i++) {
            segment.setAtIndex(C_DOUBLE, i, get(i));
        }
    }

    public void set(DVec3 v) {
        read(v.address());
    }

    @Override
    public String toString() {
        return "(%f, %f, %f)".formatted(getX(), getY(), getZ());
    }

    public static MemorySegment allocate(SegmentAllocator allocator, DVec3... values) {
        var segment = allocator.allocate(values.length * 3 * C_DOUBLE.byteSize());
        for (int i = 0; i < values.length; i++) {
            values[i].write(segment.asSlice(i * 3 * C_DOUBLE.byteSize()));
        }
        return segment;
    }
}
