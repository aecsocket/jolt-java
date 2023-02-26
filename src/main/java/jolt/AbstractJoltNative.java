package jolt;

import java.lang.foreign.MemoryAddress;

public abstract class AbstractJoltNative implements JoltNative {
    protected final MemoryAddress address;
    private boolean deleted;

    protected AbstractJoltNative(MemoryAddress address) {
        this.address = address;
    }

    @Override
    public MemoryAddress address() { return address; }

    protected abstract void deleteInternal();

    @Override
    public void delete() {
        if (deleted) throw new RuntimeException("Native object is already deleted");
        deleteInternal();
        deleted = true;
    }
}
