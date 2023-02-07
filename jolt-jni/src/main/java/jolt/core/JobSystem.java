package jolt.core;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Core/JobSystem.h>")
@JniType("JobSystem")
/**
 *
 * A class that allows units of work (Jobs) to be scheduled across multiple threads.
 * It allows dependencies between the jobs so that the jobs form a graph.
 * <p>
 * The pattern for using this class is:
 * <code>
 * 		// Create job system
 * 		JobSystem *job_system = new JobSystemThreadPool(...);
 * <p>
 * 		// Create some jobs
 * 		JobHandle second_job = job_system->CreateJob("SecondJob", Color::sRed, []() { ... }, 1); // Create a job with 1 dependency
 * 		JobHandle first_job = job_system->CreateJob("FirstJob", Color::sGreen, [second_job]() { ....; second_job.RemoveDependency(); }, 0); // Job can start immediately, will start second job when it's done
 * 		JobHandle third_job = job_system->CreateJob("ThirdJob", Color::sBlue, []() { ... }, 0); // This job can run immediately as well and can run in parallel to job 1 and 2
 * <p>
 * 		// Add the jobs to the barrier so that we can execute them while we're waiting
 * 		Barrier *barrier = job_system->CreateBarrier();
 * 		barrier->AddJob(first_job);
 * 		barrier->AddJob(second_job);
 * 		barrier->AddJob(third_job);
 * 		job_system->WaitForJobs(barrier);
 * <p>
 *   	// Clean up
 *   	job_system->DestroyBarrier(barrier);
 *   	delete job_system;
 * </code>
 * Jobs are guaranteed to be started in the order that their dependency counter becomes zero (in case they're scheduled on a background thread)
 * or in the order they're added to the barrier (when dependency count is zero and when executing on the thread that calls WaitForJobs).
 */
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
