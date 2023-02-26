package jolt.physics.collision;

import jolt.AbstractJoltNative;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilterFunctions;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectLayerPairFilterVTable.*;

public final class ObjectLayerPairFilter extends AbstractJoltNative implements ObjectLayerPairFilterFunctions {

    public static ObjectLayerPairFilter of(MemorySession memory, ObjectLayerPairFilterFunctions impl) {
        var handle = allocate(memory);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) -> impl.shouldCollide(v1, v2), memory);
        ShouldCollide$set(handle, shouldCollide.address());
        return new ObjectLayerPairFilter(handle.address(), impl);
    }

    private final ObjectLayerPairFilterFunctions impl;

    private ObjectLayerPairFilter(MemoryAddress address, ObjectLayerPairFilterFunctions impl) {
        super(address);
        this.impl = impl;
    }

    @Override
    protected void deleteInternal() {}

    @Override
    public boolean shouldCollide(short layer1, short layer2) { return impl.shouldCollide(layer1, layer2); }
}
