package jolt.physics.body;

import jolt.SegmentedJoltNative;
import jolt.core.SharedMutex;

import javax.annotation.Nullable;
import java.lang.foreign.MemorySegment;

import static jolt.headers.JPC_BodyLockRead.*;

public abstract sealed class BodyLock extends SegmentedJoltNative
        permits BodyLockRead, BodyLockWrite {
    //region Jolt-Value-Protected
    protected BodyLock(MemorySegment handle) {
        super(handle);
    }
    //endregion Jolt-Value

    public BodyLockInterface getLockInterface() {
        return BodyLockInterface.at(lock_interface$get(handle));
    }

    public SharedMutex getMutex() {
        return SharedMutex.at(mutex$get(handle));
    }

    public abstract @Nullable Body getBody();
}
