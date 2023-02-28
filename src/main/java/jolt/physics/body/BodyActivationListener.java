package jolt.physics.body;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_BodyActivationListenerVTable;
import jolt.headers.JPC_BodyActivationListener;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_BodyActivationListenerVTable.*;
import static jolt.headers.JPC_BodyActivationListener.*;

public final class BodyActivationListener extends AbstractJoltNative {
    public static BodyActivationListener at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BodyActivationListener(address);
    }

    public static BodyActivationListener of(MemorySession session, BodyActivationListenerFunctions impl) {
        var vtable = JPC_BodyActivationListenerVTable.allocate(session);
        MemorySegment onBodyActivated = OnBodyActivated.allocate((v0, v1, v2) ->
                impl.onBodyActivated(readBodyId(v1), v2), session);
        OnBodyActivated$set(vtable, onBodyActivated.address());
        MemorySegment onBodyDeactivated = OnBodyDeactivated.allocate((v0, v1, v2) ->
                impl.onBodyDeactivated(readBodyId(v1), v2), session);
        OnBodyDeactivated$set(vtable, onBodyDeactivated.address());

        var segment = JPC_BodyActivationListener.allocate(session);
        vtable$set(segment, vtable.address());
        return new BodyActivationListener(segment.address());
    }

    private BodyActivationListener(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void destroyInternal() {}
}
