#include "JoltJNI.cpp"
#include <Jolt/Core/JobSystemThreadPool.h>

extern "C" {
/*
 * Class:     jolt_JobSystemThreadPool
 * Method:    _create
 * Signature: (III)J
 */
JNIEXPORT jlong JNICALL Java_jolt_JobSystemThreadPool__1create
  (JNIEnv *, jclass, jint maxJobs, jint maxBarriers, jint numThreads) {
    return (jlong) new JobSystemThreadPool(maxJobs, maxBarriers, numThreads);
}
}
