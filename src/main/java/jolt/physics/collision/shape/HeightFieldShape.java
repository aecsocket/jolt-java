package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

public final class HeightFieldShape extends Shape {
    //region Jolt-Pointer
    private HeightFieldShape(MemoryAddress handle) {
        super(handle);
    }

    public static HeightFieldShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new HeightFieldShape(addr);
    }
    //endregion Jolt-Pointer
}
