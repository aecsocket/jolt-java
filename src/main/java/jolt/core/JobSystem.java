package jolt.core;

import jolt.JoltNative;

public class JobSystem extends JoltNative {
    protected JobSystem(long address) { super(address); }
    public static JobSystem ref(long address) { return address == 0 ? null : new JobSystem(address); }

    protected JobSystem() {}
}
