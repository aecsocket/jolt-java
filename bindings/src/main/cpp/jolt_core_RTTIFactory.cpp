#include "JoltJNI.h"
#include <Jolt/Core/Factory.h>

using namespace JPH;

extern "C" {
/*
 * Class:     jolt_core_RTTIFactory
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_core_RTTIFactory__1create
  (JNIEnv *, jclass) {
    return (jlong) new Factory();
}

/*
 * Class:     jolt_core_RTTIFactory
 * Method:    _instance
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_core_RTTIFactory__1instance__
  (JNIEnv *, jclass) {
    return (jlong) Factory::sInstance;
}

/*
 * Class:     jolt_core_RTTIFactory
 * Method:    _instance
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jolt_core_RTTIFactory__1instance__J
  (JNIEnv *, jclass, jlong value) {
    Factory::sInstance = (Factory*) value;
}
}
