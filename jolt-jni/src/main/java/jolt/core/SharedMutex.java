package jolt.core;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;

@JniInclude("<Jolt/Core/Mutex.h>")
@JniTypeMapping("SharedMutex")
public class SharedMutex extends JoltNative {
    private SharedMutex(long address) { super(address); }
    public static SharedMutex ref(long address) { return address == 0 ? null : new SharedMutex(address); }
}
