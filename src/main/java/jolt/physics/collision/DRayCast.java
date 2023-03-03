package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.*;

import static jolt.headers_d.JPC_RRayCast.*;

public final class DRayCast extends SegmentedJoltNative {
    // START Jolt-Value
    private DRayCast(MemorySegment handle) {
        super(handle);
    }

    public static DRayCast at(MemorySegment segment) {
        return new DRayCast(segment);
    }

    public static DRayCast at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new DRayCast(ofAddress(addr, alloc));
    }

    public static DRayCast of(SegmentAllocator alloc) {
        return new DRayCast(allocate(alloc));
    }
    // END Jolt-Value

    public static DRayCast of(MemorySession session, DVec3 origin, FVec3 direction) {
        var segment = allocate(session);
        origin.write(origin$slice(segment));
        direction.write(direction$slice(segment));
        return new DRayCast(segment);
    }

    public DVec3 getOrigin() {
        return DVec3.at(origin$slice(handle));
    }

    public void setOrigin(DVec3 origin) {
        origin.write(origin$slice(handle));
    }

    public FVec3 getDirection() {
        return FVec3.at(direction$slice(handle));
    }

    public void setDirection(FVec3 direction) {
        direction.write(direction$slice(handle));
    }

    public void read(MemorySegment segment) {
        setOrigin(DVec3.at(origin$slice(segment)));
        setDirection(FVec3.at(direction$slice(segment)));
    }

    public void read(DRayCast r) {
        read(r.handle);
    }

    public void write(MemorySegment segment) {
        getOrigin().write(origin$slice(segment));
        getDirection().write(direction$slice(segment));
    }

    @Override
    public String toString() {
        return "DRayCast(origin=%s, direction=%s)".formatted(getOrigin(), getDirection());
    }
}
