#include "JoltJNI.h"
#include <Jolt/RegisterTypes.h>

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_JoltEnvironment
 * Method:    _registerDefaultAllocator
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jolt_JoltEnvironment__1registerDefaultAllocator
  (JNIEnv *, jclass) {
    RegisterDefaultAllocator();
}

/*
 * Class:     jolt_JoltEnvironment
 * Method:    _registerTypes
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jolt_JoltEnvironment__1registerTypes
  (JNIEnv *, jclass) {
    RegisterTypes();
}
}
