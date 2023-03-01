package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_BodyActivationListenerVTable;
import jolt.headers.JPJ_BodyActivationListener;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_BodyActivationListenerVTable.*;
import static jolt.headers.JPJ_BodyActivationListener.*;

public final class BodyActivationListener extends AddressedJoltNative {
    public static BodyActivationListener at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new BodyActivationListener(address);
    }

    public static BodyActivationListener of(MemorySession session, BodyActivationListenerFunctions impl) {
        var vtable = JPC_BodyActivationListenerVTable.allocate(session);
        MemorySegment onBodyActivated = OnBodyActivated.allocate((v0, v1, v2) ->
                impl.onBodyActivated(BodyIds.read(v1), v2), session);
        OnBodyActivated$set(vtable, onBodyActivated.address());
        MemorySegment onBodyDeactivated = OnBodyDeactivated.allocate((v0, v1, v2) ->
                impl.onBodyDeactivated(BodyIds.read(v1), v2), session);
        OnBodyDeactivated$set(vtable, onBodyDeactivated.address());

        var segment = JPJ_BodyActivationListener.allocate(session);
        vtable$set(segment, vtable.address());
        return new BodyActivationListener(segment.address());
    }

    private BodyActivationListener(MemoryAddress address) {
        super(address);
    }
}
