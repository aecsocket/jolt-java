#include "JoltJNI.h"
#include <Jolt/Physics/Body/BodyActivationListener.h>
#include <Jolt/Physics/Body/BodyID.h>

using namespace JPH;

class ContactListenerImpl : ContactListener {
    public:
        ContactListenerImpl(JNIEnv* env, jobject localRef) : env(env) {
            globalRef = env->NewGlobalRef(localRef);
        }

        ~ContactListenerImpl() {
            env->DeleteGlobalRef(globalRef);
        }

        virtual ValidateResult OnContactValidate(const Body &inBody1, const Body &inBody2, RVec3Arg inBaseOffset, const CollideShapeResult &inCollisionResult) override {
            return (ValidateResult) env->CallIntMethod(globalRef, joltJni.ContactListener_onContactValidate,
                (jlong) &inBody1, (jlong) &inBody2, (jfloat) inBaseOffset.x, (jfloat) inBaseOffset.y, (jfloat) inBaseOffset.z, (jlong) &inCollisionResult);
        }

        virtual void OnContactAdded(const Body &inBody1, const Body &inBody2, const ContactManifold &inManifold, ContactSettings &ioSettings) override {
            return env->CallVoidMethod(globalRef, joltJni.ContactListener_onContactAdded,
                (jlong) &inBody1, (jlong) &inBody2, (jlong) inManifold, (jlong) &ioSettings);
        }

        virtual void OnContactPersisted(const Body &inBody1, const Body &inBody2, const ContactManifold &inManifold, ContactSettings &ioSettings) override {
            return env->CallVoidMethod(globalRef, joltJni.ContactListener_onContactPersisted,
                (jlong) &inBody1, (jlong) &inBody2, (jlong) inManifold, (jlong) &ioSettings);
        }

        virtual void OnContactRemoved(const SubShapeIDPair &inSubShapePair) override {
            return env->CallVoidMethod(globalRef, joltJni.ContactListener_onContactRemoved,
                (jlong) &inSubShapePair);
        }

    private:
        jobject globalRef;
        JNIEnv* env;
};

extern "C" {
/*
 * Class:     jolt_physics_collision_ContactListener
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_collision_ContactListener__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new ContactListenerImpl(env, obj);
}
}
