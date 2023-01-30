#include <cstdint>
#include <jni.h>

using uint = unsigned int;
using uint8 = uint8_t;
using uint16 = uint16_t;
using uint32 = uint32_t;
using uint64 = uint64_t;

static JavaVM *javaVm = nullptr;

class JniThreadEnv {
    public:
        JniThreadEnv() : shouldDetach(false), env(nullptr) {}
        JniThreadEnv(JNIEnv *env) : shouldDetach(false), env(env) {}
        ~JniThreadEnv() {
            if (shouldDetach) {
                javaVm->DetachCurrentThread();
            }
        }
        JNIEnv* getEnv() {
            if (env == nullptr && javaVm != nullptr) {
                javaVm->AttachCurrentThreadAsDaemon((void**) &env, nullptr);
                shouldDetach = true;
            }
            return env;
        }

    private:
        bool shouldDetach;
        JNIEnv *env;
};

static thread_local JniThreadEnv jniThreadEnv;

class JniThreadManager {
    public:
        static bool init(JNIEnv *env) {
            if (env->GetJavaVM(&javaVm) != 0) {
                return false;
            }
            jniThreadEnv = JniThreadEnv(env);
            return true;
        }
};
