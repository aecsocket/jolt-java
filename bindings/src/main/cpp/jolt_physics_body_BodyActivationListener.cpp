#include "JoltJNI.h"
#include <Jolt/Physics/Body/BodyActivationListener.h>
#include <Jolt/Physics/Body/BodyID.h>

using namespace JPH;

class BodyActivationListenerImpl : BodyActivationListener {
    public:
        BodyActivationListenerImpl(JNIEnv* env, jobject localRef) : env(env) {
            globalRef = env->NewGlobalRef(localRef);
        }

        ~BodyActivationListenerImpl() {
            env->DeleteGlobalRef(globalRef);
        }

        virtual void OnBodyActivated(const BodyID &inBodyID, uint64 inBodyUserData) override {
            return env->CallVoidMethod(globalRef, joltJni.BodyActivationListener_onBodyActivated,
                (jlong) &inBodyID, (jlong) inBodyUserData);
        }

        virtual void OnBodyDeactivated(const BodyID &inBodyID, uint64 inBodyUserData) override {
            return env->CallVoidMethod(globalRef, joltJni.BodyActivationListener_onBodyDeactivated,
                (jlong) &inBodyID, (jlong) inBodyUserData);
        }

    private:
        jobject globalRef;
        JNIEnv* env;
};

extern "C" {
/*
 * Class:     jolt_physics_body_BodyActivationListener
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_body_BodyActivationListener__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new BodyActivationListenerImpl(env, obj);
}
}
