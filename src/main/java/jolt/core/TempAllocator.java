package jolt.core;

import jolt.AddressedJoltNative;
import jolt.DestroyableJoltNative;

import static jolt.headers.JoltPhysicsC.*;

public final class TempAllocator extends DestroyableJoltNative {
    public TempAllocator(int size) {
        super(JPC_TempAllocator_Create(size));
    }

    @Override
    protected void destroyInternal() {
        JPC_TempAllocator_Destroy(address);
    }
}
