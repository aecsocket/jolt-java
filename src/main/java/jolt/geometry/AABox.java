package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.*;

import static jolt.headers.JPC_AABox.*;

public final class AABox extends SegmentedJoltNative {
    // START Jolt-Value
    private AABox(MemorySegment handle) {
        super(handle);
    }

    public static AABox at(MemorySegment segment) {
        return new AABox(segment);
    }

    public static AABox at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new AABox(ofAddress(addr, alloc));
    }

    public static AABox of(SegmentAllocator alloc) {
        return new AABox(allocate(alloc));
    }
    // END Jolt-Value

    public static AABox of(SegmentAllocator alloc, FVec3 min, FVec3 max) {
        var segment = allocate(alloc);
        min.write(min$slice(segment));
        max.write(max$slice(segment));
        return new AABox(segment);
    }

    public FVec3 getMin() {
        return FVec3.at(min$slice(handle));
    }

    public void setMin(FVec3 min) {
        min.write(min$slice(handle));
    }

    public FVec3 getMax() {
        return FVec3.at(max$slice(handle));
    }

    public void setMax(FVec3 max) {
        max.write(max$slice(handle));
    }

    public void read(MemorySegment segment) {
        setMin(FVec3.at(min$slice(segment)));
        setMax(FVec3.at(max$slice(segment)));
    }

    public void read(AABox b) {
        read(b.handle);
    }

    public void write(MemorySegment segment) {
        getMin().write(min$slice(segment));
        getMax().write(max$slice(segment));
    }

    @Override
    public String toString() {
        return "AABox(min=%s, max=%s)".formatted(getMin(), getMax());
    }
}
