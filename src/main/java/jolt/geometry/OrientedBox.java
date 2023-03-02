package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FMat44;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_OrientedBox.*;

public final class OrientedBox extends SegmentedJoltNative {
    public static OrientedBox at(MemorySegment segment) {
        return new OrientedBox(segment);
    }

    public static OrientedBox at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    public static OrientedBox create(MemorySession session, FMat44 orientation, FVec3 halfExtents) {
        var segment = allocate(session);
        orientation.write(orientation$slice(segment));
        halfExtents.write(half_extent$slice(segment));
        return new OrientedBox(segment);
    }

    private OrientedBox(MemorySegment segment) {
        super(segment);
    }

    public FMat44 getOrientation() {
        return FMat44.at(orientation$slice(segment));
    }

    public FVec3 getHalfExtent() {
        return FVec3.at(half_extent$slice(segment));
    }

    public void set(OrientedBox b) {
        getOrientation().set(b.getOrientation());
        getHalfExtent().set(b.getHalfExtent());
    }

    @Override
    public String toString() {
        return "OrientedBox(orientation=%s, halfExtent=%s)".formatted(getOrientation(), getHalfExtent());
    }
}
