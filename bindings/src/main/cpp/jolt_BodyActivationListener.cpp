#include "jolt_BodyActivationListener.h"
#include "JoltJNI.cpp"
#include <Jolt/Jolt.h>
#include <Jolt/Physics/Body/BodyActivationListener.h>
#include <Jolt/Physics/Body/BodyID.h>

using namespace JPH;

class BodyActivationListenerImpl : BodyActivationListener {
    public:
        BodyActivationListenerImpl(JNIEnv *env, jobject javaLocalRef) {
            javaGlobalRef = env->NewGlobalRef(javaLocalRef);
            jclass javaClass = env->GetObjectClass(javaLocalRef);
            onBodyActivatedId = env->GetMethodID(javaClass, "_onBodyActivated", "(LL)V");
            onBodyDeactivatedId = env->GetMethodID(javaClass, "_onBodyDeactivated", "(LL)V");
        }

        ~BodyActivationListenerImpl() {
            jniThreadEnv.getEnv()->DeleteGlobalRef(javaGlobalRef);
        }

        virtual void OnBodyActivated(const BodyID &inBodyID, uint64 inBodyUserData) override {
            JNIEnv *env = jniThreadEnv.getEnv();
            return env->CallVoidMethod(javaGlobalRef, onBodyActivatedId,
                (jlong) &inBodyID, (jlong) inBodyUserData);
        }

        virtual void OnBodyDeactivated(const BodyID &inBodyID, uint64 inBodyUserData) override {
            JNIEnv *env = jniThreadEnv.getEnv();
            return env->CallVoidMethod(javaGlobalRef, onBodyDeactivatedId,
                (jlong) &inBodyID, (jlong) inBodyUserData);
        }

    private:
        jobject javaGlobalRef;
        jmethodID onBodyActivatedId;
        jmethodID onBodyDeactivatedId;
};

extern "C" {
//    JNIEXPORT jlong JNICALL Java_jolt_BodyActivationListener__1create(JNIEnv *env, jclass) {
//        JPH::BodyActivationListener * const ptr = new BodyActivationListenerImpl();
//    }
//
//    JNIEXPORT void JNICALL Java_jolt_JoltEnvironment__1registerTypes(JNIEnv *env, jclass) {
//        JPH::RegisterTypes();
//    }
}
