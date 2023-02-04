package jolt.core;

import jolt.JoltNative;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Core/JobSystem.h>")
@JniType("JobSystem")
public class JobSystem extends JoltNative {
    protected JobSystem(long address) { super(address); }
    public static JobSystem ref(long address) { return address == 0 ? null : new JobSystem(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    protected JobSystem() {}
}
