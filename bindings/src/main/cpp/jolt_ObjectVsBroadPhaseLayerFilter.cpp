#include "JoltJNI.cpp"
#include <Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>

class ObjectVsBroadPhaseLayerFilterImpl : ObjectVsBroadPhaseLayerFilter {
    public:
        ObjectVsBroadPhaseLayerFilterImpl(JNIEnv *env, jobject javaLocalRef) {
            javaGlobalRef = env->NewGlobalRef(javaLocalRef);
            jclass javaClass = env->GetObjectClass(javaLocalRef);
            shouldCollideId = env->GetMethodID(javaClass, "_shouldCollide", "(IJ)Z");
        }

        ~ObjectVsBroadPhaseLayerFilterImpl() {
            jniThreadEnv.getEnv()->DeleteGlobalRef(javaGlobalRef);
        }

        virtual bool ShouldCollide(ObjectLayer inLayer1, BroadPhaseLayer inLayer2) const override {
            JNIEnv *env = jniThreadEnv.getEnv();
            return env->CallBooleanMethod(javaGlobalRef, shouldCollideId,
                (jint) inLayer1, (jlong) &inLayer2);
        }

    private:
        jobject javaGlobalRef;
        jmethodID shouldCollideId;
};

extern "C" {
/*
 * Class:     jolt_ObjectVsBroadPhaseLayerFilter
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_ObjectVsBroadPhaseLayerFilter__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new ObjectVsBroadPhaseLayerFilterImpl(env, obj);
}
}
