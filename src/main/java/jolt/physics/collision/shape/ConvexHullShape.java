package jolt.physics.collision.shape;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class ConvexHullShape extends ConvexShape {
    public static ConvexHullShape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new ConvexHullShape(address);
    }

    private ConvexHullShape(MemoryAddress address) {
        super(address);
    }
}
