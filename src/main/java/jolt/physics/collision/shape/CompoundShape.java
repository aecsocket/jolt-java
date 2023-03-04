package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class CompoundShape extends Shape
        permits StaticCompoundShape, MutableCompoundShape {
    //region Jolt-Pointer-Protected
    protected CompoundShape(MemoryAddress handle) {
        super(handle);
    }

    public static CompoundShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new CompoundShape(addr);
    }
    //endregion Jolt-Pointer-Protected

    // TODO getSubShapes
}
