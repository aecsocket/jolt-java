package jolt.physics.collision;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_ObjectLayerPairFilterVTable;
import jolt.headers.JPJ_ObjectLayerPairFilter;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectLayerPairFilterVTable.*;
import static jolt.headers.JPJ_ObjectLayerPairFilter.*;

public final class ObjectLayerPairFilter extends AbstractJoltNative {
    public static ObjectLayerPairFilter at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ObjectLayerPairFilter(address);
    }

    public static ObjectLayerPairFilter of(MemorySession memory, ObjectLayerPairFilterFunctions impl) {
        var vtable = JPC_ObjectLayerPairFilterVTable.allocate(memory);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) -> impl.shouldCollide(v1, v2), memory);
        ShouldCollide$set(vtable, shouldCollide.address());

        var handle = JPJ_ObjectLayerPairFilter.allocate(memory);
        vtable$set(handle, vtable.address());
        return new ObjectLayerPairFilter(handle.address());
    }

    private ObjectLayerPairFilter(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() {}
}
