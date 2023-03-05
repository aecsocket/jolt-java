package jolt.physics.body;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_BodyLockRead.*;

public final class BodyLockRead extends BodyLock {
    //region Jolt-Value
    private BodyLockRead(MemorySegment handle) {
        super(handle);
    }

    public static BodyLockRead at(MemorySegment segment) {
        return new BodyLockRead(segment);
    }

    public static BodyLockRead at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyLockRead(ofAddress(addr, alloc));
    }

    public static BodyLockRead of(SegmentAllocator alloc) {
        return new BodyLockRead(allocate(alloc));
    }
    //endregion Jolt-Value

    public @Nullable Body getBody() {
        return Body.at(body$get(handle));
    }
}
