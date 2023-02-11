package jolt;

import cpufeatures.CpuFeatures;
import io.github.aecsocket.jniglue.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniPriority(NativePriority.EARLY)
@JniInclude("""
        <cstdint>
        <Jolt/Jolt.h>
        <Jolt/RegisterTypes.h>""")
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
        
        jclass jni_RuntimeException;""")
@JniOnLoad("""
        javaVm = vm;
        jniThread = JNIThreadEnv(env);
        
        jni_RuntimeException = env->FindClass("java/lang/RuntimeException");
        if (env->ExceptionCheck()) return JNI_ERR;""")
public final class JoltEnvironment {
    private JoltEnvironment() {}

    public static final String JNI_MODEL = "jolt/JoltJNI";
    private static final AtomicBoolean loaded = new AtomicBoolean(false);
    private static int features = -1;

    public static void load() {
        if (loaded.getAndSet(true)) return;

        var platform = JniPlatform.get();
        var libName = platform.mapLibraryName("jolt-jni-bindings");

        String resourcePath = "jolt/" + switch (platform) {
            case LINUX -> "linux/";
            case WINDOWS -> "windows/";
            case MACOS -> "macos/";
            case MACOS_ARM64 -> "macos_arm64/";
        } + libName;
        try (var libIn = CpuFeatures.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (libIn == null)
                throw new IllegalStateException("No JNI library in JAR " + resourcePath);
            var libFile = Files.createTempFile(libName, null);
            Files.copy(libIn, libFile, StandardCopyOption.REPLACE_EXISTING);
            System.load(libFile.toAbsolutePath().toString());
        } catch (IOException ex) {
            throw new IllegalStateException("Could not load JNI library", ex);
        }
    }

    public static int features() {
        if (features == -1)
            features = _features();
        return features;
    }
    @JniBind("""
        int features = 0;
        #ifdef JPH_DOUBLE_PRECISION
        features |= 0x1;
        #endif
        #ifdef JPH_USE_SSE4_1
        features |= 0x2;
        #endif
        #ifdef JPH_USE_SSE4_2
        features |= 0x4;
        #endif
        #ifdef JPH_USE_AVX
        features |= 0x8;
        #endif
        #ifdef JPH_USE_AVX2
        features |= 0x10;
        #endif
        #ifdef JPH_USE_AVX512
        features |= 0x20;
        #endif
        #ifdef JPH_USE_LZCNT
        features |= 0x40;
        #endif
        #ifdef JPH_USE_TZCNT
        features |= 0x80;
        #endif
        #ifdef JPH_USE_F16C
        features |= 0x100;
        #endif
        #ifdef JPH_USE_FMADD
        features |= 0x200;
        #endif
        return features;""")
    private static native int _features();

    public static List<JoltFeature> featureList() {
        return JoltFeature.flagsOf(features());
    }

    public static boolean uses(JoltFeature feature) {
        return feature.isSet(features());
    }

    public static void registerDefaultAllocator() { _registerDefaultAllocator(); }
    @JniBind("RegisterDefaultAllocator();")
    private static native void _registerDefaultAllocator();

    public static void registerTypes() { _registerTypes(); }
    @JniBind("RegisterTypes();")
    private static native void _registerTypes();
}
