package jolt.physics.collision.shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

public final class TaperedCapsuleShape extends ConvexShape {
    //region Jolt-Pointer
    private TaperedCapsuleShape(MemoryAddress handle) {
        super(handle);
    }

    public static TaperedCapsuleShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new TaperedCapsuleShape(addr);
    }
    //endregion Jolt-Pointer
}
