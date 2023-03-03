package jolt.physics.collision;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_ContactSettings.*;

public final class ContactSettings extends SegmentedJoltNative {
    // START Jolt-Value
    private ContactSettings(MemorySegment handle) {
        super(handle);
    }

    public static ContactSettings at(MemorySegment segment) {
        return new ContactSettings(segment);
    }

    public static ContactSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ContactSettings(ofAddress(addr, alloc));
    }

    public static ContactSettings of(SegmentAllocator alloc) {
        return new ContactSettings(allocate(alloc));
    }
    // END Jolt-Value

    public float getCombinedFriction() {
        return combined_friction$get(handle);
    }

    public void setCombinedFriction(float combinedFriction) {
        combined_friction$set(handle, combinedFriction);
    }

    public float getCombinedRestitution() {
        return combined_restitution$get(handle);
    }

    public void setCombinedRestitution(float combinedRestitution) {
        combined_restitution$set(handle, combinedRestitution);
    }
}
