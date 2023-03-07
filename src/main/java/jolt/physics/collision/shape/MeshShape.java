package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

public final class MeshShape extends Shape {
    //region Jolt-Pointer
    private MeshShape(MemoryAddress handle) {
        super(handle);
    }

    public static MeshShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MeshShape(addr);
    }
    //endregion Jolt-Pointer
}
