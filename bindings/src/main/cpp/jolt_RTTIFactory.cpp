#include "JoltJNI.cpp"
#include <Jolt/Core/Factory.h>

extern "C" {
/*
 * Class:     jolt_RTTIFactory
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_RTTIFactory__1create
  (JNIEnv *, jclass) {
    return (jlong) new Factory();
}

/*
 * Class:     jolt_RTTIFactory
 * Method:    _instance
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_RTTIFactory__1instance__
  (JNIEnv *, jclass) {
    return (jlong) Factory::sInstance;
}

/*
 * Class:     jolt_RTTIFactory
 * Method:    _instance
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jolt_RTTIFactory__1instance__J
  (JNIEnv *, jclass, jlong factory) {
    Factory::sInstance = (Factory*) factory;
}
}
