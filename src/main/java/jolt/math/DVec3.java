package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_DOUBLE;

public final class DVec3 extends SegmentedJoltNative {
    private static final long BYTES_SIZE = 4 * C_DOUBLE.byteSize();

    //region PrimitiveJoltNative
    private DVec3(MemorySegment handle) {
        super(handle);
    }

    public static DVec3 at(MemorySegment segment) {
        return new DVec3(segment);
    }

    public static DVec3 at(MemorySession arena, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new DVec3(MemorySegment.ofAddress(addr, BYTES_SIZE, arena));
    }

    public static DVec3 of(SegmentAllocator alloc) {
        return new DVec3(alloc.allocate(BYTES_SIZE));
    }
    //endregion PrimitiveJoltNative

    public static DVec3 of(SegmentAllocator alloc, double x, double y, double z) {
        return new DVec3(alloc.allocateArray(C_DOUBLE, x, y, z, z));
    }

    public static DVec3 of(SegmentAllocator alloc, double s) {
        return of(alloc, s, s, s);
    }

    public static MemorySegment ofArray(SegmentAllocator alloc, DVec3... values) {
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

    public double getX() { return get(0); }
    public void setX(double x) { set(0, x); }

    public double getY() { return get(1); }
    public void setY(double y) { set(1, y); }

    public double getZ() { return get(2); }
    public void setZ(double z) { set(2, z); set(3, z); }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 3; i++) {
            set(i, address.getAtIndex(C_DOUBLE, i));
        }
        set(3, get(2));
    }

    public void read(DVec3 v) {
        read(v.address());
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 3; i++) {
            segment.setAtIndex(C_DOUBLE, i, get(i));
        }
    }

    public boolean equalValue(DVec3 v) {
        double[] ours = components();
        double[] theirs = v.components();
        for (int i = 0; i < 3; i++) {
            if (Double.compare(ours[i], theirs[i]) != 0)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(%f, %f, %f)".formatted(getX(), getY(), getZ());
    }
}
