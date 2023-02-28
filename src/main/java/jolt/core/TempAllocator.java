package jolt.core;

import jolt.AbstractJoltNative;

import static jolt.headers.JoltPhysicsC.*;

public final class TempAllocator extends AbstractJoltNative {
    public TempAllocator(int size) {
        super(JPC_TempAllocator_Create(size));
    }

    @Override
    protected void destroyInternal() {
        JPC_TempAllocator_Destroy(address);
    }
}
