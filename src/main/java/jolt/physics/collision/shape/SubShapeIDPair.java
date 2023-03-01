package jolt.physics.collision.shape;

import jolt.SegmentedJoltNative;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_SubShapeIDPair.*;

public final class SubShapeIDPair extends SegmentedJoltNative {
    public static SubShapeIDPair at(MemorySegment segment) {
        return segment.address() == MemoryAddress.NULL ? null : new SubShapeIDPair(segment);
    }

    public static SubShapeIDPair at(MemorySession session, Addressable ptr) {
        return at(ofAddress(ptr.address(), session));
    }

    private SubShapeIDPair(MemorySegment segment) {
        super(segment);
    }
}
