package jolt;

import java.lang.foreign.MemoryAddress;

public abstract class AddressedJoltNative implements JoltNative {
    protected final MemoryAddress address;

    protected AddressedJoltNative(MemoryAddress address) {
        this.address = address;
    }

    @Override
    public MemoryAddress address() {
        return address;
    }
}
