package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.headers.JPC_BroadPhaseCastResult;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_BroadPhaseCastResult.*;

public final class BroadPhaseCastResult extends SegmentedJoltNative {
    public static BroadPhaseCastResult at(MemorySegment segment) {
        return segment.address() == MemoryAddress.NULL ? null : new BroadPhaseCastResult(segment);
    }

    public static BroadPhaseCastResult at(MemorySession session, Addressable ptr) {
        return at(ofAddress(ptr.address(), session));
    }

    public static BroadPhaseCastResult allocate(MemorySession session) {
        return new BroadPhaseCastResult(JPC_BroadPhaseCastResult.allocate(session));
    }

    private BroadPhaseCastResult(MemorySegment segment) {
        super(segment);
    }

    public int getBodyId() {
        return body_id$get(segment);
    }

    public void setBodyId(int bodyId) {
        body_id$set(segment, bodyId);
    }

    public float getFraction() {
        return fraction$get(segment);
    }

    public void setFraction(float fraction) {
        fraction$set(segment, fraction);
    }
}
