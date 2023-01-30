package jolt;

public final class JobSystemThreadPool extends JobSystem {
    private JobSystemThreadPool(long address) { super(address); }
    public static JobSystemThreadPool ofPointer(long address) { return new JobSystemThreadPool(address); }

    public JobSystemThreadPool(int maxJobs, int maxBarriers, int numThreads) {
        address = _create(maxJobs, maxBarriers, numThreads);
    }
    private static native long _create(int maxJobs, int maxBarriers, int numThreads);
}
