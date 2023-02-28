package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_ObjectLayerPairFilterVTable;
import jolt.headers.JPC_ObjectLayerPairFilter;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectLayerPairFilterVTable.*;
import static jolt.headers.JPC_ObjectLayerPairFilter.*;

public final class ObjectLayerPairFilter extends AddressedJoltNative {
    public static ObjectLayerPairFilter at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ObjectLayerPairFilter(address);
    }

    public static ObjectLayerPairFilter of(MemorySession session, ObjectLayerPairFilterFunctions impl) {
        var vtable = JPC_ObjectLayerPairFilterVTable.allocate(session);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) -> impl.shouldCollide(v1, v2), session);
        ShouldCollide$set(vtable, shouldCollide.address());

        var handle = JPC_ObjectLayerPairFilter.allocate(session);
        vtable$set(handle, vtable.address());
        return new ObjectLayerPairFilter(handle.address());
    }

    private ObjectLayerPairFilter(MemoryAddress address) {
        super(address);
    }
}
