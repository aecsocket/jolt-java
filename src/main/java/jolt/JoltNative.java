package jolt;

import java.lang.foreign.MemoryAddress;

public interface JoltNative {
    MemoryAddress address();

    void delete();
}
