package jolt;

import java.lang.foreign.MemoryAddress;

public abstract class DeletableJoltNative extends AddressedJoltNative implements Deletable {
    protected boolean deleted;

    protected DeletableJoltNative(MemoryAddress address) {
        super(address);
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    protected abstract void deleteInternal();

    @Override
    public void delete() {
        if (deleted) throw new IllegalStateException("Object is already deleted");
        deleteInternal();
        deleted = true;
    }
}
