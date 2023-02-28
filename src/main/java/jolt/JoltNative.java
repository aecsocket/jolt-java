package jolt;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

public interface JoltNative {
    MemoryAddress address();

    static MemoryAddress addr(@Nullable JoltNative obj) {
        return obj == null ? MemoryAddress.NULL : obj.address();
    }
}
