package jolt.physics.collision.shape;

import jolt.SegmentedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_SubShapeIDPair.*;

public final class SubShapeIdPair extends SegmentedJoltNative {
    public static SubShapeIdPair at(MemorySegment segment) {
        return segment.address() == MemoryAddress.NULL ? null : new SubShapeIdPair(segment);
    }

    public static SubShapeIdPair at(MemorySession session, Addressable ptr) {
        return at(ofAddress(ptr.address(), session));
    }

    private SubShapeIdPair(MemorySegment segment) {
        super(segment);
    }
}
