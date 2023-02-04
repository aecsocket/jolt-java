package jolt.core;

import jolt.jni.JniBind;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Core/JobSystemThreadPool.h>")
@JniType("JobSystemThreadPool")
public final class JobSystemThreadPool extends JobSystem {
    private JobSystemThreadPool(long address) { super(address); }
    public static JobSystemThreadPool ref(long address) { return address == 0 ? null : new JobSystemThreadPool(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long address);

    public JobSystemThreadPool(int maxJobs, int maxBarriers, int numThreads) {
        address = _ctor(maxJobs, maxBarriers, numThreads);
    }
    @JniBind("return (long) new JobSystemThreadPool(maxJobs, maxBarriers, numThreads);")
    private static native long _ctor(int maxJobs, int maxBarriers, int numThreads);
}
