package jolt.physics.collision;

import jolt.math.DVec3;
import jolt.math.FVec3;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

public record DRayCast(DVec3 origin, FVec3 direction) {
    public static DRayCast read(MemorySession session, MemoryAddress address) {
        MemorySegment segment = jolt.headers_d.JPC_RRayCast.ofAddress(address, session);
        return new DRayCast(
                DVec3.read(jolt.headers_d.JPC_RRayCast.origin$slice(segment).address()),
                FVec3.read(jolt.headers_d.JPC_RRayCast.direction$slice(segment).address())
        );
    }

    public void write(MemorySegment segment) {
        origin.write(jolt.headers_d.JPC_RRayCast.origin$slice(segment));
        direction.write(jolt.headers_d.JPC_RRayCast.direction$slice(segment));
    }

    public MemorySegment allocate(MemorySession session) {
        MemorySegment segment = jolt.headers_d.JPC_RRayCast.allocate(session);
        write(segment);
        return segment;
    }
}
