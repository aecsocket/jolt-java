package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.SegmentedJoltNative;
import jolt.headers.JPC_BodyActivationListenerVTable;
import jolt.headers.JPJ_BodyActivationListener;

import java.lang.foreign.*;

import static jolt.headers.JPC_BodyActivationListenerVTable.*;
import static jolt.headers.JPJ_BodyActivationListener.*;

public final class BodyActivationListener extends AddressedJoltNative {
    //region Jolt-Pointer
    private BodyActivationListener(MemoryAddress handle) {
        super(handle);
    }

    public static BodyActivationListener at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyActivationListener(addr);
    }
    //endregion Jolt-Pointer

    public static BodyActivationListener of(MemorySession arena, BodyActivationListenerFn impl) {
        var vtable = JPC_BodyActivationListenerVTable.allocate(arena);
        MemorySegment onBodyActivated = OnBodyActivated.allocate((v0, v1, v2) ->
                impl.onBodyActivated(v1, v2), arena);
        OnBodyActivated$set(vtable, onBodyActivated.address());
        MemorySegment onBodyDeactivated = OnBodyDeactivated.allocate((v0, v1, v2) ->
                impl.onBodyDeactivated(v1, v2), arena);
        OnBodyDeactivated$set(vtable, onBodyDeactivated.address());

        var segment = JPJ_BodyActivationListener.allocate(arena);
        vtable$set(segment, vtable.address());
        return new BodyActivationListener(segment.address());
    }
}
