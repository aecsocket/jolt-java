package jolt;

import javax.annotation.Nullable;
import java.lang.foreign.MemoryAddress;

public interface JoltNative {
    MemoryAddress address();
}
