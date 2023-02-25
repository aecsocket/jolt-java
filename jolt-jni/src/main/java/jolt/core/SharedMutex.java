package jolt.core;

import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Core/Mutex.h>")
@JniTypeMapping("SharedMutex")
public final class SharedMutex extends JoltNativeImpl {
    private SharedMutex(long address) { super(address); }
    public static SharedMutex ref(long address) { return address == 0 ? null : new SharedMutex(address); }
}
