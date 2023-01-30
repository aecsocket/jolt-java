package jolt;

public class JobSystem extends JoltNative {
    protected JobSystem() {}
    protected JobSystem(long address) { super(address); }
    public static JobSystem ofPointer(long address) { return new JobSystem(address); }
}
