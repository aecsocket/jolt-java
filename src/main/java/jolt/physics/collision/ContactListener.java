package jolt.physics.collision;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.headers.JPC_ContactListenerVTable;
import jolt.headers.JPJ_ContactListener;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.body.BodyIds;
import jolt.physics.collision.shape.SubShapeIdPair;

import java.lang.foreign.*;

import static jolt.headers.JPC_ContactListenerVTable.*;
import static jolt.headers.JPJ_ContactListener.*;

public final class ContactListener extends AddressedJoltNative {
    // START Jolt-Pointer
    private ContactListener(MemoryAddress handle) {
        super(handle);
    }

    public static ContactListener at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ContactListener(addr);
    }
    // END Jolt-Pointer

    private static ContactListener of(MemorySession arena, MemorySegment vtable, ContactListenerFn impl) {
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactAdded = OnContactAdded.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactAdded(BodyIds.at(v1), BodyIds.at(v2), ContactManifold.at(s, v3), ContactSettings.at(s, v4));
            }
        }, arena);
        OnContactAdded$set(vtable, onContactAdded.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactPersisted = OnContactPersisted.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactPersisted(BodyIds.at(v1), BodyIds.at(v2), ContactManifold.at(s, v3), ContactSettings.at(s, v4));
            }
        }, arena);
        OnContactPersisted$set(vtable, onContactPersisted.address());
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactRemoved = OnContactRemoved.allocate((v0, v1) -> {
            try (var s = MemorySession.openConfined()) {
                impl.onContactRemoved(SubShapeIdPair.at(s, v1));
            }
        }, arena);
        OnContactRemoved$set(vtable, onContactRemoved.address());

        var segment = JPJ_ContactListener.allocate(arena);
        vtable$set(segment, vtable.address());
        return new ContactListener(segment.address());
    }

    public static ContactListener of(MemorySession arena, ContactListenerFn.F impl) {
        Jolt.assertSinglePrecision();
        var vtable = JPC_ContactListenerVTable.allocate(arena);
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIds.at(v1), BodyIds.at(v2), FVec3.at(s, v3), CollideShapeResult.at(s, v4)).ordinal();
            }
        }, arena);
        OnContactValidate$set(vtable, onContactValidate.address());
        return of(arena, vtable, impl);
    }

    public static ContactListener of(MemorySession arena, ContactListenerFn.D impl) {
        Jolt.assertDoublePrecision();
        var vtable = JPC_ContactListenerVTable.allocate(arena);
        @SuppressWarnings("DataFlowIssue")
        MemorySegment onContactValidate = OnContactValidate.allocate((v0, v1, v2, v3, v4) -> {
            try (var s = MemorySession.openConfined()) {
                return impl.onContactValidate(BodyIds.at(v1), BodyIds.at(v2), DVec3.at(s, v3), CollideShapeResult.at(s, v4)).ordinal();
            }
        }, arena);
        OnContactValidate$set(vtable, onContactValidate.address());
        return of(arena, vtable, impl);
    }
}
