#include "JoltJNI.cpp"
#include <Jolt/Physics/Collision/ObjectLayer.h>

class ObjectLayerPairFilterImpl : ObjectLayerPairFilter {
    public:
        ObjectLayerPairFilterImpl(JNIEnv *env, jobject javaLocalRef) {
            javaGlobalRef = env->NewGlobalRef(javaLocalRef);
            jclass javaClass = env->GetObjectClass(javaLocalRef);
            shouldCollideId = env->GetMethodID(javaClass, "_shouldCollide", "(II)Z");
        }

        ~ObjectLayerPairFilterImpl() {
            jniThreadEnv.getEnv()->DeleteGlobalRef(javaGlobalRef);
        }

        virtual bool ShouldCollide(ObjectLayer inLayer1, ObjectLayer inLayer2) const override {
            JNIEnv *env = jniThreadEnv.getEnv();
            return env->CallBooleanMethod(javaGlobalRef, shouldCollideId,
                (jint) inLayer1, (jint) inLayer2);
        }

    private:
        jobject javaGlobalRef;
        jmethodID shouldCollideId;
};

extern "C" {
/*
 * Class:     jolt_ObjectLayerPairFilter
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jolt_ObjectLayerPairFilter__1create
  (JNIEnv *env, jobject obj) {
    return (jlong) new ObjectLayerPairFilterImpl(env, obj);
}
}
