#include "JoltJNI.h"
#include <Jolt/Core/JobSystemThreadPool.h>

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_core_JobSystemThreadPool
 * Method:    _create
 * Signature: (III)J
 */
JNIEXPORT jlong JNICALL Java_jolt_core_JobSystemThreadPool__1create
  (JNIEnv *, jclass, jint maxJobs, jint maxBarriers, jint numThreads) {
    return (jlong) new JobSystemThreadPool(maxJobs, maxBarriers, numThreads);
}
}
