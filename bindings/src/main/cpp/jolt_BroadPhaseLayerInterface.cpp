#include "JoltJNI.cpp"
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>
#include <memory>
#include <iostream>

class BroadPhaseLayerInterfaceImpl : BroadPhaseLayerInterface {
    public:
        BroadPhaseLayerInterfaceImpl(JNIEnv *env, jobject javaLocalRef) {
            javaGlobalRef = env->NewGlobalRef(javaLocalRef);
            jclass javaClass = env->GetObjectClass(javaLocalRef);
            getNumBroadPhaseLayersId = env->GetMethodID(javaClass, "_getNumBroadPhaseLayers", "()I");
            getBroadPhaseLayerId = env->GetMethodID(javaClass, "_getBroadPhaseLayer", "(I)J");
            long a = (long) jniThreadEnv.getEnv();
            std::cout << "abc env = " << a << std::endl;
        }

        ~BroadPhaseLayerInterfaceImpl() {
            jniThreadEnv.getEnv()->DeleteGlobalRef(javaGlobalRef);
        }

        virtual uint GetNumBroadPhaseLayers() const override {
            long a = (long) &jniThreadEnv;
            std::cout << "env = " << a << std::endl;
            JNIEnv *env = jniThreadEnv.getEnv();
            return env->CallIntMethod(javaGlobalRef, getNumBroadPhaseLayersId);
        }

        virtual BroadPhaseLayer GetBroadPhaseLayer(ObjectLayer inLayer) const override {
            JNIEnv *env = jniThreadEnv.getEnv();
            return (BroadPhaseLayer) env->CallLongMethod(javaGlobalRef, getBroadPhaseLayerId,
                (jlong) &inLayer);
        }

    private:
        jobject javaGlobalRef;
        jmethodID getNumBroadPhaseLayersId;
        jmethodID getBroadPhaseLayerId;
};

extern "C" {
/*
 * Class:     jolt_BroadPhaseLayerInterface
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_BroadPhaseLayerInterface__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new BroadPhaseLayerInterfaceImpl(env, obj);
}
}
