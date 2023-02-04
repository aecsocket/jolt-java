package jolt.core;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Core/JobSystem.h>")
public class JobSystem extends JoltNative {
    protected JobSystem(long address) { super(address); }
    public static JobSystem ref(long address) { return address == 0 ? null : new JobSystem(address); }

    protected JobSystem() {}
}
