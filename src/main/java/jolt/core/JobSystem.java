package jolt.core;

import jolt.DeletableJoltNative;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class JobSystem extends DeletableJoltNative {
    public static final int MAX_PHYSICS_JOBS = JPC_MAX_PHYSICS_JOBS();
    public static final int MAX_PHYSICS_BARRIERS = JPC_MAX_PHYSICS_BARRIERS();

    //region Jolt-Pointer
    private JobSystem(MemoryAddress handle) {
        super(handle);
    }

    public static JobSystem at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new JobSystem(addr);
    }
    //endregion Jolt-Pointer

    public static JobSystem of(int maxJobs, int maxBarriers, int numThreads) {
        return new JobSystem(JPC_JobSystem_Create(maxJobs, maxBarriers, numThreads));
    }

    @Override
    protected void deleteInternal() {
        JPC_JobSystem_Destroy(handle);
    }
}
