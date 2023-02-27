package jolt.physics.collision;

import jolt.AbstractJoltNative;
import jolt.headers.JPC_ContactListenerVTable;
import jolt.headers.JPJ_ContactListener;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ContactListenerVTable.*;
import static jolt.headers.JPJ_ContactListener.*;

public final class ContactListener extends AbstractJoltNative {
    public static ContactListener at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ContactListener(address);
    }

    public static ContactListener of(MemorySession session, ContactListenerFunctions impl) {
        var vtable = JPC_ContactListenerVTable.allocate(session);
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) ->
                impl.onContactValidate(readBodyId(v1), readBodyId(v2), null, CollideShapeResult.at(session, v4)).ordinal(), session);
        OnContactValidate$set(vtable, onContactValidate.address());
        MemorySegment onContactAdded = OnContactAdded.allocate((v0, v1, v2, v3, v4) ->
                impl.onContactAdded(readBodyId(v1), readBodyId(v2), null, null), session);
        OnContactAdded$set(vtable, onContactAdded.address());
        MemorySegment onContactPersisted = OnContactPersisted.allocate((v0, v1, v2, v3, v4) ->
                impl.onContactPersisted(readBodyId(v1), readBodyId(v2), null, null), session);
        OnContactPersisted$set(vtable, onContactPersisted.address());
        MemorySegment onContactRemoved = OnContactRemoved.allocate((v0, v1) ->
                impl.onContactRemoved(readSubShapeIdPair(v1)), session);
        OnContactRemoved$set(vtable, onContactRemoved.address());

        var handle = JPJ_ContactListener.allocate(session);
        vtable$set(handle, vtable.address());
        return new ContactListener(handle.address());
    }

    private ContactListener(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() {}
}
