package jolt.physics.body;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SegmentAllocator;

import static jolt.headers.JPC_BodyLockRead.*;

public final class BodyLockWrite extends BodyLock {
    //region Jolt-Value
    private BodyLockWrite(MemorySegment handle) {
        super(handle);
    }

    public static BodyLockWrite at(MemorySegment segment) {
        return new BodyLockWrite(segment);
    }

    public static BodyLockWrite at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyLockWrite(ofAddress(addr, alloc));
    }

    public static BodyLockWrite of(SegmentAllocator alloc) {
        return new BodyLockWrite(allocate(alloc));
    }
    //endregion Jolt-Value

    public @Nullable MutableBody getBody() {
        return MutableBody.at(body$get(handle));
    }
}
