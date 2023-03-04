package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_BodyFilterVTable;
import jolt.headers.JPJ_BodyFilter;
import jolt.headers.JPJ_ShapeFilter;
import jolt.physics.body.Body;
import jolt.physics.body.BodyIds;
import jolt.physics.collision.shape.SubShapeIds;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ShapeFilterVTable.*;
import static jolt.headers.JPJ_ShapeFilter.*;

public final class ShapeFilter extends AddressedJoltNative {
    //region Jolt-Pointer
    private ShapeFilter(MemoryAddress handle) {
        super(handle);
    }

    public static ShapeFilter at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ShapeFilter(addr);
    }
    //endregion Jolt-Pointer

    public static ShapeFilter of(MemorySession arena, ShapeFilterFn impl) {
        var vtable = JPC_BodyFilterVTable.allocate(arena);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1) ->
                impl.shouldCollide(v1), arena);
        ShouldCollide$set(vtable, shouldCollide.address());
        MemorySegment shouldPairCollide = ShouldPairCollide.allocate((v0, v1, v2) ->
                impl.shouldCollide(v1, v2), arena);
        ShouldPairCollide$set(vtable, shouldPairCollide.address());

        var segment = JPJ_ShapeFilter.allocate(arena);
        vtable$set(segment, vtable.address());
        return new ShapeFilter(segment.address());
    }

    private static ShapeFilter passthrough;

    public static ShapeFilter passthrough() {
        if (passthrough == null) {
            passthrough = ShapeFilter.of(MemorySession.global(), new ShapeFilterFn() {
                @Override
                public boolean shouldCollide(int subShapeId) {
                    return true;
                }

                @Override
                public boolean shouldCollide(int subShapeId1, int subShapeId2) {
                    return true;
                }
            });
        }
        return passthrough;
    }
}
