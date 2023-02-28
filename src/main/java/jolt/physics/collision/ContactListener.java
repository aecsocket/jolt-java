package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.headers.JPC_ContactListenerVTable;
import jolt.headers.JPC_ContactListener;
import jolt.physics.body.BodyIDs;
import jolt.physics.collision.shape.SubShapeIDPair;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ContactListenerVTable.*;
import static jolt.headers.JPC_ContactListener.*;

public final class ContactListener extends AddressedJoltNative {
    public static ContactListener at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new ContactListener(address);
    }

    public static ContactListener of(MemorySession session, ContactListenerFunctions impl) {
        var vtable = JPC_ContactListenerVTable.allocate(session);
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIDs.read(v1), BodyIDs.read(v2), null /* todo */, CollideShapeResult.at(s, v4)).ordinal();
            }
        }, session);
        OnContactValidate$set(vtable, onContactValidate.address());
        MemorySegment onContactAdded = OnContactAdded.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactAdded(BodyIDs.read(v1), BodyIDs.read(v2), ContactManifold.at(s, v3), ContactSettings.at(s, v4));
            }
        }, session);
        OnContactAdded$set(vtable, onContactAdded.address());
        MemorySegment onContactPersisted = OnContactPersisted.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactPersisted(BodyIDs.read(v1), BodyIDs.read(v2), ContactManifold.at(s, v3), ContactSettings.at(s, v4));
            }
        }, session);
        OnContactPersisted$set(vtable, onContactPersisted.address());
        MemorySegment onContactRemoved = OnContactRemoved.allocate((v0, v1) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactRemoved(SubShapeIDPair.at(s, v1));
            }
        }, session);
        OnContactRemoved$set(vtable, onContactRemoved.address());

        var segment = JPC_ContactListener.allocate(session);
        vtable$set(segment, vtable.address());
        return new ContactListener(segment.address());
    }

    private ContactListener(MemoryAddress address) {
        super(address);
    }
}
