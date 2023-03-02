package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_RayCast.*;

public final class FRayCast extends SegmentedJoltNative {
    public static FRayCast at(MemorySegment segment) {
        return new FRayCast(segment);
    }

    public static FRayCast at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    public static FRayCast create(MemorySession session, FVec3 origin, FVec3 direction) {
        var segment = allocate(session);
        origin.write(origin$slice(segment));
        direction.write(direction$slice(segment));
        return new FRayCast(segment);
    }

    private FRayCast(MemorySegment segment) {
        super(segment);
    }

    public FVec3 getOrigin() {
        return FVec3.at(origin$slice(segment));
    }

    public FVec3 getDirection() {
        return FVec3.at(direction$slice(segment));
    }

    public void set(FRayCast r) {
        getOrigin().set(r.getOrigin());
        getDirection().set(r.getDirection());
    }

    @Override
    public String toString() {
        return "FRayCast(origin=%s, direction=%s)".formatted(getOrigin(), getDirection());
    }
}
