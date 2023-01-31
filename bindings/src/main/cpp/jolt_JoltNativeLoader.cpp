#include "JoltJNI.h"

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_JoltNativeLoader
 * Method:    _init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jolt_JoltNativeLoader__1init
  (JNIEnv *env, jclass) {
    joltJni.Init(env);
}
}
