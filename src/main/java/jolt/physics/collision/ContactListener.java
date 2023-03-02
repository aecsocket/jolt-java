package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.headers.JPC_ContactListenerVTable;
import jolt.headers.JPJ_ContactListener;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.body.BodyIds;
import jolt.physics.collision.shape.SubShapeIdPair;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ContactListenerVTable.*;
import static jolt.headers.JPJ_ContactListener.*;

public final class ContactListener extends AddressedJoltNative {
    public static ContactListener at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ContactListener(address);
    }

    private static ContactListener of(MemorySession session, MemorySegment vtable, ContactListenerFn impl) {
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactAdded = OnContactAdded.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactAdded(BodyIds.read(v1), BodyIds.read(v2), ContactManifold.at(s, v3), ContactSettings.at(s, v4));
            }
        }, session);
        OnContactAdded$set(vtable, onContactAdded.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactPersisted = OnContactPersisted.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactPersisted(BodyIds.read(v1), BodyIds.read(v2), ContactManifold.at(s, v3), ContactSettings.at(s, v4));
            }
        }, session);
        OnContactPersisted$set(vtable, onContactPersisted.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactRemoved = OnContactRemoved.allocate((v0, v1) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactRemoved(SubShapeIdPair.at(s, v1));
            }
        }, session);
        OnContactRemoved$set(vtable, onContactRemoved.address());

        var segment = JPJ_ContactListener.allocate(session);
        vtable$set(segment, vtable.address());
        return new ContactListener(segment.address());
    }

    public static ContactListener of(MemorySession session, ContactListenerFn.F impl) {
        Jolt.assertSinglePrecision();
        var vtable = JPC_ContactListenerVTable.allocate(session);
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIds.read(v1), BodyIds.read(v2), FVec3.at(s, v3), CollideShapeResult.at(s, v4)).ordinal();
            }
        }, session);
        OnContactValidate$set(vtable, onContactValidate.address());
        return of(session, vtable, impl);
    }

    public static ContactListener of(MemorySession session, ContactListenerFn.D impl) {
        Jolt.assertDoublePrecision();
        var vtable = JPC_ContactListenerVTable.allocate(session);
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIds.read(v1), BodyIds.read(v2), DVec3.at(s, v3), CollideShapeResult.at(s, v4)).ordinal();
            }
        }, session);
        OnContactValidate$set(vtable, onContactValidate.address());
        return of(session, vtable, impl);
    }

    private ContactListener(MemoryAddress address) {
        super(address);
    }
}
