#include "jolt_JoltEnvironment.h"
#include <Jolt/Jolt.h>
#include <Jolt/RegisterTypes.h>

extern "C" {
    JNIEXPORT void JNICALL Java_jolt_JoltEnvironment__1registerDefaultAllocator(JNIEnv *env, jclass) {
        JPH::RegisterDefaultAllocator();
    }

    JNIEXPORT void JNICALL Java_jolt_JoltEnvironment__1registerTypes(JNIEnv *env, jclass) {
        JPH::RegisterTypes();
    }
}
