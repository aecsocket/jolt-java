#include "jolt_JoltEnvironment.h"
#include <Jolt/Jolt.h>

extern "C" {
    JNIEXPORT void JNICALL Java_jolt_JoltEnvironment_registerDefaultAllocator(JNIEnv *env, jclass) {
        JPH::RegisterDefaultAllocator();
    }
}
