package jolt;

import java.lang.foreign.MemoryAddress;

public abstract class AddressedJoltNative implements JoltNative {
    protected final MemoryAddress handle;

    protected AddressedJoltNative(MemoryAddress handle) {
        this.handle = handle;
    }

    @Override
    public MemoryAddress address() {
        return handle;
    }
}
