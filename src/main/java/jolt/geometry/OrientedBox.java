package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FMat44;
import jolt.math.FVec3;

import java.lang.foreign.*;

import static jolt.headers.JPC_OrientedBox.*;

public final class OrientedBox extends SegmentedJoltNative {
    // START Jolt-Value
    private OrientedBox(MemorySegment handle) {
        super(handle);
    }

    public static OrientedBox at(MemorySegment segment) {
        return new OrientedBox(segment);
    }

    public static OrientedBox at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new OrientedBox(ofAddress(addr, alloc));
    }

    public static OrientedBox of(SegmentAllocator alloc) {
        return new OrientedBox(allocate(alloc));
    }
    // END Jolt-Value

    public static OrientedBox of(SegmentAllocator alloc, FMat44 orientation, FVec3 halfExtents) {
        var segment = allocate(alloc);
        orientation.write(orientation$slice(segment));
        halfExtents.write(half_extent$slice(segment));
        return new OrientedBox(segment);
    }

    public FMat44 getOrientation() {
        return FMat44.at(orientation$slice(handle));
    }

    public void setOrientation(FMat44 orientation) {
        orientation.write(orientation$slice(handle));
    }

    public FVec3 getHalfExtent() {
        return FVec3.at(half_extent$slice(handle));
    }

    public void setHalfExtent(FVec3 halfExtent) {
        halfExtent.write(half_extent$slice(handle));
    }
    
    public void read(MemorySegment segment) {
        setOrientation(FMat44.at(orientation$slice(segment)));
        setHalfExtent(FVec3.at(half_extent$slice(segment)));
    }

    public void read(OrientedBox b) {
        read(b.handle);
    }

    public void write(MemorySegment segment) {
        getOrientation().write(orientation$slice(segment));
        getHalfExtent().write(half_extent$slice(segment));
    }

    @Override
    public String toString() {
        return "OrientedBox(orientation=%s, halfExtent=%s)".formatted(getOrientation(), getHalfExtent());
    }
}
