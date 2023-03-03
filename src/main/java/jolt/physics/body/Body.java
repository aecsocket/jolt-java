package jolt.physics.body;

import jolt.JoltNative;

import java.lang.foreign.MemoryAddress;

public sealed interface Body extends JoltNative permits MutableBody {
    static Body at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyImpl(addr);
    }

    int getId();
}
