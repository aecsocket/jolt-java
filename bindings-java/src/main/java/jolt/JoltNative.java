package jolt;

import jolt.jni.*;

import javax.annotation.Nullable;
import java.util.Objects;

@JniNative(JoltNative.MODEL)
@JniPriority(NativePriority.EARLY)
@JniInclude("""
        <cstdint>
        <Jolt/Jolt.h>""")
@JniHeader("""
        using uint = unsigned int;
        using uint8 = uint8_t;
        using uint16 = uint16_t;
        using uint32 = uint32_t;
        using uint64 = uint64_t;
        using namespace JPH;
        
        static JavaVM* javaVm = nullptr;
        
        class JNIThreadEnv {
        public:
            JNIThreadEnv() : shouldDetach(false), env(nullptr) {}
            JNIThreadEnv(JNIEnv *env) : shouldDetach(false), env(env) {}
            ~JNIThreadEnv() {
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
            JNIEnv* env;
        };
        
        static thread_local JNIThreadEnv jniThread;
        
        jclass runtimeException;
        
        jclass JtVec3f;
        jmethodID JtVec3f_set;
        
        jclass JtQuat;
        jmethodID JtQuat_set;
        
        void JniToJava(JNIEnv* env, const RVec3 from, jobject to) {
            env->CallObjectMethod(to, JtVec3f_set,
                from.GetX(), from.GetY(), from.GetZ());
        }
        
        jobject JniToJava(JNIEnv* env, const RVec3 from) {
            jobject to = env->AllocObject(JtVec3f);
            JniToJava(env, from, to);
            return to;
        }
        
        class JNINative {
        public:
            JNINative(JNIEnv* env, jobject obj) : obj(env->NewGlobalRef(obj)) {}
            ~JNINative() {
                jniThread.getEnv()->DeleteGlobalRef(obj);
            }
        
        protected:
            jobject obj;
        };""")
@JniInit("""
        env->GetJavaVM(&javaVm);
        jniThread = JNIThreadEnv(env);
        runtimeException = env->FindClass("java/lang/RuntimeException");
        
        JtVec3f = env->FindClass("jolt/math/JtVec3f");
        JtVec3f_set = env->GetMethodID(JtVec3f, "set", "(FFF)V");
        JtQuat = env->FindClass("jolt/math/JtQuat");
        JtQuat_set = env->GetMethodID(JtQuat, "set", "(FFFF)V");""")

public class JoltNative implements AutoCloseable {
    public static final String MODEL = "jolt/JoltJNIBindings";
    protected static final String NATIVE_OBJECT_DELETED = "Native object is already deleted";

    protected long address;

    protected JoltNative(long address) {
        if (address == 0)
            throw new IllegalArgumentException("Creating object with null address");
        this.address = address;
    }
    // although this *can* return null, we leave the nullability ambiguous
    public static JoltNative ref(long address) { return address == 0 ? null : new JoltNative(address); }
    public static long ptr(@Nullable JoltNative obj) { return obj == null ? 0 : obj.address; }

    protected static RuntimeException unimplemented() {
        return new UnimplementedMethodException();
    }

    public JoltNative() {}

    public long getAddress() { return address; }

    public void delete() { throw unimplemented(); }
    @Override public void close() { delete(); }

    @Override
    public String toString() {
        return String.format("%s*%xd", getClass().getName(), address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoltNative that = (JoltNative) o;
        return address == that.address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
