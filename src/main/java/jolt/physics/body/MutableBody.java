package jolt.physics.body;

import java.lang.foreign.MemoryAddress;

public sealed interface MutableBody extends Body permits BodyImpl {
    static MutableBody at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BodyImpl(addr);
    }
}
