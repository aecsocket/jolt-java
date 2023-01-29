#include <stdio.h>
#include <jni.h>

JNIEXPORT jint Java_jolt_TestNatives_addTen(JNIEnv *env, jobject obj, jint x)
{
    return x + 10;
}
