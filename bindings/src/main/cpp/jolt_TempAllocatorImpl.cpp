#include "JoltJNI.cpp"
#include <Jolt/Core/TempAllocator.h>

extern "C" {
/*
 * Class:     jolt_TempAllocatorImpl
 * Method:    _create
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jolt_TempAllocatorImpl__1create
  (JNIEnv *env, jclass, jlong size) {
    return (jlong) new TempAllocatorImpl((uint) size);
}
}
