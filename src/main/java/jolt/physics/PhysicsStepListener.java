package jolt.physics;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_PhysicsStepListenerVTable;
import jolt.headers.JPJ_PhysicsStepListener;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_PhysicsStepListenerVTable.*;
import static jolt.headers.JPJ_PhysicsStepListener.*;

public final class PhysicsStepListener extends AddressedJoltNative {
    //region Jolt-Pointer
    private PhysicsStepListener(MemoryAddress handle) {
        super(handle);
    }

    public static PhysicsStepListener at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new PhysicsStepListener(addr);
    }
    //endregion Jolt-Pointer

    public static PhysicsStepListener of(MemorySession arena, PhysicsStepListenerFn impl) {
        var vtable = JPC_PhysicsStepListenerVTable.allocate(arena);
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onStep = OnStep.allocate((v0, v1, v2) ->
                impl.onStep(v1, PhysicsSystem.at(v2)), arena);
        OnStep$set(vtable, onStep.address());

        var segment = JPJ_PhysicsStepListener.allocate(arena);
        vtable$set(segment, vtable.address());
        return new PhysicsStepListener(segment.address());
    }
}
