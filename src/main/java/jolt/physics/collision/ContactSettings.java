package jolt.physics.collision;

import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.FVec3;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JPC_ContactSettings.*;

public final class ContactSettings extends SegmentedJoltNative {
    public static ContactSettings at(MemorySegment segment) {
        return new ContactSettings(segment);
    }

    public static ContactSettings at(MemorySession session, Addressable ptr) {
        return ptr.address() == null ? null : at(ofAddress(ptr.address(), session));
    }

    private ContactSettings(MemorySegment segment) {
        super(segment);
    }

    public float getCombinedFriction() {
        return combined_friction$get(segment);
    }

    public void setCombinedFriction(float combinedFriction) {
        combined_friction$set(segment, combinedFriction);
    }

    public float getCombinedRestitution() {
        return combined_restitution$get(segment);
    }

    public void setCombinedRestitution(float combinedRestitution) {
        combined_restitution$set(segment, combinedRestitution);
    }
}
