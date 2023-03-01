package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.headers.JPC_ContactListenerVTable;
import jolt.headers.JPJ_ContactListener;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.body.BodyIDs;
import jolt.physics.collision.shape.SubShapeIDPair;
import jolt.physics.collision.shape.TriangleShapeSettings;

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

    private static ContactListener of(MemorySession session, MemorySegment vtable, ContactListenerFunctions impl) {
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

        var segment = JPJ_ContactListener.allocate(session);
        vtable$set(segment, vtable.address());
        return new ContactListener(segment.address());
    }

    public static ContactListener of(MemorySession session, ContactListenerFunctions.F impl) {
        Jolt.assertSinglePrecision();
        var vtable = JPC_ContactListenerVTable.allocate(session);
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIDs.read(v1), BodyIDs.read(v2), FVec3.read(v3), CollideShapeResult.at(s, v4)).ordinal();
            }
        }, session);
        OnContactValidate$set(vtable, onContactValidate.address());
        return of(session, vtable, impl);
    }

    public static ContactListener of(MemorySession session, ContactListenerFunctions.D impl) {
        Jolt.assertDoublePrecision();
        var vtable = JPC_ContactListenerVTable.allocate(session);
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIDs.read(v1), BodyIDs.read(v2), DVec3.read(v3), CollideShapeResult.at(s, v4)).ordinal();
            }
        }, session);
        OnContactValidate$set(vtable, onContactValidate.address());
        return of(session, vtable, impl);
    }

    private ContactListener(MemoryAddress address) {
        super(address);
    }
}
