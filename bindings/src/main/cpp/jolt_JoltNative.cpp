#include "JoltJNI.h"

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_JoltNative
 * Method:    _destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jolt_JoltNative__1destroy
  (JNIEnv *, jclass, jlong address) {
    delete (void*) address;
}
}
