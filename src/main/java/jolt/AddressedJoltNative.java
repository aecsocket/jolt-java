package jolt;

import java.lang.foreign.MemoryAddress;

public non-sealed abstract class AddressedJoltNative extends BaseJoltNative {
    protected final MemoryAddress handle;

    protected AddressedJoltNative(MemoryAddress handle) {
        this.handle = handle;
    }

    @Override
    public MemoryAddress address() {
        return handle;
    }
}
