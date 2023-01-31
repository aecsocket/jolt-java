#include "JoltJNI.h"
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>

using namespace JPH;

class BroadPhaseLayerInterfaceImpl : BroadPhaseLayerInterface {
    public:
        BroadPhaseLayerInterfaceImpl(JNIEnv* env, jobject localRef) {
            this->env = env;
            globalRef = env->NewGlobalRef(localRef);
        }

        ~BroadPhaseLayerInterfaceImpl() {
            env->DeleteGlobalRef(globalRef);
        }

        virtual uint GetNumBroadPhaseLayers() const override {
            joltJni.Init(env);
            return env->CallIntMethod(globalRef, joltJni.BroadPhaseLayerInterface_getNumBroadPhaseLayers);
        }

        virtual BroadPhaseLayer GetBroadPhaseLayer(ObjectLayer inLayer) const override {
            joltJni.Init(env);
            return (BroadPhaseLayer) env->CallLongMethod(globalRef, joltJni.BroadPhaseLayerInterface_getBroadPhaseLayer,
                (jlong) inLayer);
        }

#if defined(JPH_EXTERNAL_PROFILE) || defined(JPH_PROFILE_ENABLED)
        virtual const char* GetBroadPhaseLayerName(BroadPhaseLayer inLayer) const override {
            joltJni.Init(env);
            jstring res = (jstring) env->CallObjectMethod(globalRef, joltJni.BroadPhaseLayerInterface_getBroadPhaseLayerName,
                (jlong) &inLayer);
            if (env->ExceptionCheck()) return nullptr;
            return env->GetStringUTFChars(res, 0);
        }
#endif // JPH_EXTERNAL_PROFILE || JPH_PROFILE_ENABLED

    private:
        jobject globalRef;
        JNIEnv* env;
};

extern "C" {
/*
 * Class:     jolt_physics_collision_broadphase_BroadPhaseLayerInterface
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_physics_collision_broadphase_BroadPhaseLayerInterface__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new BroadPhaseLayerInterfaceImpl(env, obj);
}
}
