package jolt.geometry;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_AABoxCast.*;

public final class AABoxCast extends SegmentedJoltNative {
    public static AABoxCast at(MemorySegment segment) {
        return new AABoxCast(segment);
    }

    public static AABoxCast at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    public static AABoxCast create(MemorySession session, AABox box, FVec3 direction) {
        var segment = allocate(session);
        AABox.at(box$slice(segment)).set(box);
        FVec3.at(direction$slice(segment)).set(direction);
        return new AABoxCast(segment);
    }

    private AABoxCast(MemorySegment segment) {
        super(segment);
    }

    public AABox getBox() {
        return AABox.at(box$slice(segment));
    }

    public FVec3 getDirection() {
        return FVec3.at(direction$slice(segment));
    }

    public void set(AABoxCast b) {
        getBox().set(b.getBox());
        getDirection().set(b.getDirection());
    }

    @Override
    public String toString() {
        return "AABoxCast(box=%s, direction=%s)".formatted(getBox(), getDirection());
    }
}
