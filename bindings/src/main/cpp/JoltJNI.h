#include <cstdint>
#include <jni.h>
#include <Jolt/Jolt.h>

using uint = unsigned int;
using uint8 = uint8_t;
using uint16 = uint16_t;
using uint32 = uint32_t;
using uint64 = uint64_t;

class JoltJNI {
    public:
        void Init(JNIEnv*);
        JavaVM* vm;

        jmethodID BodyActivationListener_onBodyActivated;
        jmethodID BodyActivationListener_onBodyDeactivated;

        jmethodID BroadPhaseLayerInterface_getNumBroadPhaseLayers;
        jmethodID BroadPhaseLayerInterface_getBroadPhaseLayer;
        jmethodID BroadPhaseLayerInterface_getBroadPhaseLayerName;

        jmethodID ObjectVsBroadPhaseLayerFilter_shouldCollide;

        jmethodID ContactListener_onContactValidate;
        jmethodID ContactListener_onContactAdded;
        jmethodID ContactListener_onContactPersisted;
        jmethodID ContactListener_onContactRemoved;

        jmethodID ObjectLayerPairFilter_shouldCollide;
};

static JoltJNI joltJni;
