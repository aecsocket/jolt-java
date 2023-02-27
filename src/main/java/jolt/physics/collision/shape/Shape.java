package jolt.physics.collision.shape;

import jolt.AbstractJoltNative;

import java.lang.foreign.MemoryAddress;

public class Shape extends AbstractJoltNative {
    public static Shape at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : new Shape(address);
    }

    protected Shape(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void deleteInternal() { /* todo */ }
}
