package jolt.physics.body;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_BodyActivationListenerVTable;
import jolt.headers.JPJ_BodyActivationListener;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_BodyActivationListenerVTable.*;
import static jolt.headers.JPJ_BodyActivationListener.vtable$set;

public final class BodyInterface extends AbstractJoltNative {
    public static BodyInterface at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new BodyInterface(address);
    }

    private BodyInterface(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() { throw cannotDelete(); }


}
