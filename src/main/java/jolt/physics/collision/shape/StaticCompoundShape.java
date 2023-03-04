package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

public final class StaticCompoundShape extends CompoundShape {
    //region Jolt-Pointer
    private StaticCompoundShape(MemoryAddress handle) {
        super(handle);
    }

    public static StaticCompoundShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new StaticCompoundShape(addr);
    }
    //endregion Jolt-Pointer
}
