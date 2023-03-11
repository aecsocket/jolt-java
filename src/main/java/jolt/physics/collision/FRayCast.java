package jolt.physics.collision;

import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.*;

import static jolt.cheaders.JPC_RayCast.*;

public final class FRayCast extends SegmentedJoltNative {
    //region Jolt-Value
    private FRayCast(MemorySegment handle) {
        super(handle);
    }

    public static FRayCast at(MemorySegment segment) {
        return new FRayCast(segment);
    }

    public static FRayCast at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new FRayCast(ofAddress(addr, alloc));
    }

    public static FRayCast of(SegmentAllocator alloc) {
        return new FRayCast(allocate(alloc));
    }
    //endregion Jolt-Value

    public static FRayCast of(MemorySession session, FVec3 origin, FVec3 direction) {
        var segment = allocate(session);
        origin.write(origin$slice(segment));
        direction.write(direction$slice(segment));
        return new FRayCast(segment);
    }

    public FVec3 getOrigin() {
        return FVec3.at(origin$slice(handle));
    }

    public void setOrigin(FVec3 origin) {
        origin.write(origin$slice(handle));
    }

    public FVec3 getDirection() {
        return FVec3.at(direction$slice(handle));
    }

    public void setDirection(FVec3 direction) {
        direction.write(direction$slice(handle));
    }

    public void read(MemorySegment segment) {
        setOrigin(FVec3.at(origin$slice(segment)));
        setDirection(FVec3.at(direction$slice(segment)));
    }

    public void read(FRayCast r) {
        read(r.handle);
    }

    public void write(MemorySegment segment) {
        getOrigin().write(origin$slice(segment));
        getDirection().write(direction$slice(segment));
    }

    @Override
    public String toString() {
        return "FRayCast(origin=%s, direction=%s)".formatted(getOrigin(), getDirection());
    }
}
