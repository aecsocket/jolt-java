//#include "JoltJNI.h"
#include <jni.h>
#include <cstdint>
#include <Jolt/Jolt.h>
#include <Jolt/RegisterTypes.h>
#include <Jolt/Core/Factory.h>
#include <Jolt/Core/TempAllocator.h>
#include <Jolt/Core/JobSystem.h>
#include <Jolt/Core/JobSystemThreadPool.h>
#include <Jolt/Physics/PhysicsSystem.h>
#include <Jolt/Physics/Body/BodyActivationListener.h>

using uint = unsigned int;
using uint8 = uint8_t;
using uint16 = uint16_t;
using uint32 = uint32_t;
using uint64 = uint64_t;
using namespace JPH;
/*using namespace JoltJNI;

class CallbackNative {
public:
    CallbackNative(JNIEnv* env, jobject localRef) : env(env), globalRef(env->NewGlobalRef(localRef)) {}
    ~CallbackNative() {
        env->DeleteGlobalRef(globalRef);
    }

protected:
    jobject globalRef;
    JNIEnv* env;
};

class BodyActivationListenerImpl : CallbackNative, BodyActivationListener {
public:
    BodyActivationListenerImpl(JNIEnv* env, jobject localRef) : CallbackNative(env, localRef) {}

    virtual void OnBodyActivated(const BodyID &inBodyID, uint64 inBodyUserData) override {
        env->CallVoidMethod(globalRef, BodyActivationListener_onBodyActivated,
            (jlong) &inBodyID, (jlong) inBodyUserData);
    }

    virtual void OnBodyDeactivated(const BodyID &inBodyID, uint64 inBodyUserData) override {
        env->CallVoidMethod(globalRef, BodyActivationListener_onBodyDeactivated,
            (jlong) &inBodyID, (jlong) inBodyUserData);
    }
};*/

extern "C" {

}
