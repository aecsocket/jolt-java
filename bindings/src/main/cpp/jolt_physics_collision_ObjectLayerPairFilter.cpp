#include "JoltJNI.h"
#include <Jolt/Physics/Collision/ObjectLayer.h>

using namespace JPH;

class ObjectLayerPairFilterImpl : ObjectLayerPairFilter {
    public:
        ObjectLayerPairFilterImpl(JNIEnv* env, jobject localRef) {
            this->env = env;
            globalRef = env->NewGlobalRef(localRef);
        }

        ~ObjectLayerPairFilterImpl() {
            env->DeleteGlobalRef(globalRef);
        }

        virtual bool ShouldCollide(ObjectLayer inLayer1, ObjectLayer inLayer2) const override {
            return env->CallBooleanMethod(globalRef, joltJni.ObjectLayerPairFilter_shouldCollide,
                (jint) inLayer1, (jint) inLayer2);
        }

    private:
        jobject globalRef;
        JNIEnv* env;
};

extern "C" {
/*
 * Class:     jolt_physics_collision_ObjectLayerPairFilter
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_collision_ObjectLayerPairFilter__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new ObjectLayerPairFilterImpl(env, obj);
}
}
