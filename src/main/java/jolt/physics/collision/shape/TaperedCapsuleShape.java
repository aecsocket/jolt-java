package jolt.physics.collision.shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

public final class TaperedCapsuleShape extends ConvexShape {
    public static TaperedCapsuleShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new TaperedCapsuleShape(address);
    }

    private TaperedCapsuleShape(MemoryAddress address) {
        super(address);
    }
}
