package jolt.physics.collision.shape;

import java.lang.foreign.MemoryAddress;

public final class MutableCompoundShape extends CompoundShape {
    //region Jolt-Pointer
    private MutableCompoundShape(MemoryAddress handle) {
        super(handle);
    }

    public static MutableCompoundShape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new MutableCompoundShape(addr);
    }
    //endregion Jolt-Pointer

    // TODO addShape
    // TODO removeShape
    // TODO modifyShape
    // TODO modifyShapes
    // TODO adjustCenterOfMass
}
