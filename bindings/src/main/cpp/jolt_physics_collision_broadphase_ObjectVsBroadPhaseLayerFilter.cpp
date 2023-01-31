#include "JoltJNI.h"
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>

using namespace JPH;

class ObjectVsBroadPhaseLayerFilterImpl : ObjectVsBroadPhaseLayerFilter {
    public:
        ObjectVsBroadPhaseLayerFilterImpl(JNIEnv* env, jobject localRef) {
            this->env = env;
            globalRef = env->NewGlobalRef(localRef);
        }

        ~ObjectVsBroadPhaseLayerFilterImpl() {
            env->DeleteGlobalRef(globalRef);
        }

        virtual bool ShouldCollide(ObjectLayer inLayer1, BroadPhaseLayer inLayer2) const override {
            return env->CallBooleanMethod(globalRef, joltJni.ObjectVsBroadPhaseLayerFilter_shouldCollide,
                (jint) inLayer1, (jlong) &inLayer2);
        }

    private:
        jobject globalRef;
        JNIEnv* env;
};

extern "C" {
/*
 * Class:     jolt_physics_collision_broadphase_ObjectVsBroadPhaseLayerFilter
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_collision_broadphase_ObjectVsBroadPhaseLayerFilter__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new ObjectVsBroadPhaseLayerFilterImpl(env, obj);
}
}
