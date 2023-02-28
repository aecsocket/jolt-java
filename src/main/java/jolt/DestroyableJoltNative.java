package jolt;

import java.lang.foreign.MemoryAddress;

public abstract class DestroyableJoltNative extends AddressedJoltNative implements Destroyable {
    protected boolean destroyed;

    protected DestroyableJoltNative(MemoryAddress address) {
        super(address);
    }

    protected abstract void destroyInternal();

    @Override
    public void destroy() {
        if (destroyed) throw new IllegalStateException("Object is already destroyed");
        destroyInternal();
        destroyed = true;
    }
}
