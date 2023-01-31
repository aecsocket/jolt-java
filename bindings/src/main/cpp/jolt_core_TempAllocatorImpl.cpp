#include "JoltJNI.h"
#include <Jolt/Core/TempAllocator.h>

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_core_TempAllocatorImpl
 * Method:    _create
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_core_TempAllocatorImpl__1create
  (JNIEnv *env, jclass, jlong size) {
    return (jlong) new TempAllocatorImpl((uint) size);
}
}
