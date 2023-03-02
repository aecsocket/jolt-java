package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.DVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers_d.JPC_RRayCast.*;

public final class DRayCast extends SegmentedJoltNative {
    public static DRayCast at(MemorySegment segment) {
        return new DRayCast(segment);
    }

    public static DRayCast at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    public static DRayCast create(MemorySession session, DVec3 origin, DVec3 direction) {
        var segment = allocate(session);
        origin.write(origin$slice(segment));
        direction.write(direction$slice(segment));
        return new DRayCast(segment);
    }

    private DRayCast(MemorySegment segment) {
        super(segment);
    }

    public DVec3 getOrigin() {
        return DVec3.at(origin$slice(segment));
    }

    public DVec3 getDirection() {
        return DVec3.at(direction$slice(segment));
    }

    public void set(DRayCast r) {
        getOrigin().set(r.getOrigin());
        getDirection().set(r.getDirection());
    }

    @Override
    public String toString() {
        return "FRayCast(origin=%s, direction=%s)".formatted(getOrigin(), getDirection());
    }
}
