package jolt.core;

import jolt.AbstractJoltNative;

import static jolt.headers.JoltPhysicsC.*;

public final class JobSystem extends AbstractJoltNative {
    public static final int MAX_PHYSICS_JOBS = JPC_MAX_PHYSICS_JOBS();
    public static final int MAX_PHYSICS_BARRIERS = JPC_MAX_PHYSICS_BARRIERS();

    public JobSystem(int maxJobs, int maxBarriers, int numThreads) {
        super(JPC_JobSystem_Create(maxJobs, maxBarriers, numThreads));
    }

    @Override
    protected void deleteInternal() {
        JPC_JobSystem_Destroy(address);
    }
}
