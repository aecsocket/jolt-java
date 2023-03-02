package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_AABox.*;

public final class AABox extends SegmentedJoltNative {
    public static AABox at(MemorySegment segment) {
        return new AABox(segment);
    }

    public static AABox at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    public static AABox create(MemorySession session, FVec3 min, FVec3 max) {
        var segment = allocate(session);
        min.write(min$slice(segment));
        max.write(max$slice(segment));
        return new AABox(segment);
    }

    private AABox(MemorySegment segment) {
        super(segment);
    }

    public FVec3 getMin() {
        return FVec3.at(min$slice(segment));
    }

    public FVec3 getMax() {
        return FVec3.at(max$slice(segment));
    }

    public void set(AABox b) {
        getMin().set(b.getMin());
        getMax().set(b.getMax());
    }

    @Override
    public String toString() {
        return "AABox(min=%s, max=%s)".formatted(getMin(), getMax());
    }
}
