package jolt.physics.collision.shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class ConvexHullShape extends ConvexShape {
    //region Jolt-Pointer
    private ConvexHullShape(MemoryAddress handle) {
        super(handle);
    }

    public static ConvexHullShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new ConvexHullShape(addr);
    }
    //endregion Jolt-Pointer
}
