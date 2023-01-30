#include "JoltJNI.cpp"
#include <iostream>

extern "C" {
/*
 * Class:     jolt_JoltNativeLoader
 * Method:    _initThreadManager
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_jolt_JoltNativeLoader__1initThreadManager
  (JNIEnv *env, jclass) {
    if (env->GetJavaVM(&javaVm) != 0) {
        return false;
    }
    jniThreadEnv = JniThreadEnv(env);
    std::cout << "jni env in thingy = " << (long) &jniThreadEnv << std::endl;
    return true;
}
}
