package jolt.physics.collision.broadphase;

import jolt.AbstractJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ObjectVsBroadPhaseLayerFilterVTable.*;

public final class ObjectVsBroadPhaseLayerFilter extends AbstractJoltNative implements ObjectVsBroadPhaseLayerFilterFunctions {

    public static ObjectVsBroadPhaseLayerFilter of(MemorySession memory, ObjectVsBroadPhaseLayerFilterFunctions impl) {
        var handle = allocate(memory);
        MemorySegment shouldCollide = ShouldCollide.allocate((v0, v1, v2) -> impl.shouldCollide(v1, v2), memory);
        ShouldCollide$set(handle, shouldCollide.address());
        return new ObjectVsBroadPhaseLayerFilter(handle.address(), impl);
    }

    private final ObjectVsBroadPhaseLayerFilterFunctions impl;

    private ObjectVsBroadPhaseLayerFilter(MemoryAddress address, ObjectVsBroadPhaseLayerFilterFunctions impl) {
        super(address);
        this.impl = impl;
    }

    @Override
    protected void deleteInternal() {}

    @Override
    public boolean shouldCollide(short layer1, byte layer2) { return impl.shouldCollide(layer1, layer2); }
}
