package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_BodyFilterVTable;
import jolt.headers.JPJ_BodyFilter;
import jolt.physics.body.Body;
import jolt.physics.body.BodyIds;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_BodyFilterVTable.*;
import static jolt.headers.JPJ_BodyFilter.*;

public final class BodyFilter extends AddressedJoltNative {
    //region Jolt-Pointer
    private BodyFilter(MemoryAddress handle) {
        super(handle);
    }

    public static BodyFilter at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyFilter(addr);
    }
    //endregion Jolt-Pointer

    public static BodyFilter of(MemorySession arena, BodyFilterFn impl) {
        var vtable = JPC_BodyFilterVTable.allocate(arena);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1) ->
                impl.shouldCollide(BodyIds.at(v1)), arena);
        ShouldCollide$set(vtable, shouldCollide.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment shouldCollideLocked = ShouldCollideLocked.allocate((v0, v1) ->
                impl.shouldCollideLocked(Body.at(v1)), arena);
        ShouldCollideLocked$set(vtable, shouldCollideLocked.address());

        var segment = JPJ_BodyFilter.allocate(arena);
        vtable$set(segment, vtable.address());
        return new BodyFilter(segment.address());
    }

    private static BodyFilter passthrough;

    public static BodyFilter passthrough() {
        if (passthrough == null) {
            passthrough = BodyFilter.of(MemorySession.global(), new BodyFilterFn() {
                @Override
                public boolean shouldCollide(int bodyId) {
                    return true;
                }

                @Override
                public boolean shouldCollideLocked(Body body) {
                    return false;
                }
            });
        }
        return passthrough;
    }
}
