package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.core.SharedMutex;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class BodyLockInterface extends AddressedJoltNative {
    //region Jolt-Pointer
    private BodyLockInterface(MemoryAddress handle) {
        super(handle);
    }

    public static BodyLockInterface at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyLockInterface(addr);
    }
    //endregion Jolt-Pointer

    public void lockRead(int bodyId, BodyLockRead out) {
        JPC_BodyLockInterface_LockRead(handle, bodyId, out.address());
    }

    public void unlockRead(BodyLockRead lock) {
        JPC_BodyLockInterface_UnlockRead(handle, lock.address());
    }

    public void lockWrite(int bodyId, BodyLockWrite out) {
        JPC_BodyLockInterface_LockWrite(handle, bodyId, out.address());
    }

    public void unlockWrite(BodyLockWrite lock) {
        JPC_BodyLockInterface_UnlockWrite(handle, lock.address());
    }
}
