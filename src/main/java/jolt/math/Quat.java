package jolt.math;

import jolt.SegmentedJoltNative;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.C_FLOAT;

public final class Quat extends SegmentedJoltNative {
    public static Quat at(MemorySegment segment) {
        return new Quat(segment);
    }

    public static Quat at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(MemorySegment.ofAddress(ptr.address(), 4 * C_FLOAT.byteSize(), session));
    }

    public static Quat create(MemorySession session, float x, float y, float z, float w) {
        var segment = session.allocateArray(C_FLOAT, x, y, z, w);
        return new Quat(segment);
    }

    public static Quat createIdentity(MemorySession session) {
        return create(session, 0.0f, 0.0f, 0.0f, 1.0f);
    }

    private Quat(MemorySegment segment) {
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

    public float getW() { return get(3); }
    public void setW(float w) { set(3, w); }

    public void read(MemoryAddress address) {
        for (int i = 0; i < 4; i++) {
            set(i, address.getAtIndex(C_FLOAT, i));
        }
    }

    public void write(MemorySegment segment) {
        for (int i = 0; i < 4; i++) {
            segment.setAtIndex(C_FLOAT, i, get(i));
        }
    }

    public void set(Quat v) {
        read(v.address());
    }

    @Override
    public String toString() {
        return "(%f, %f, %f, %f)".formatted(getX(), getY(), getZ(), getW());
    }
}
