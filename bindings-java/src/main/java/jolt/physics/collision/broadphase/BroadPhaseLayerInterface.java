package jolt.physics.collision.broadphase;

import jolt.JoltNative;
import jolt.jni.*;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
@JniType("BroadPhaseLayerInterfaceImpl")
@JniHeader("""
        class BroadPhaseLayerInterfaceImpl : JNINative, BroadPhaseLayerInterface {
        public:
            BroadPhaseLayerInterfaceImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            uint GetNumBroadPhaseLayers() const override {
                JNIEnv* env = jniThread.getEnv();
                uint res = env->CallIntMethod(obj, BroadPhaseLayerInterface_getNumBroadPhaseLayers);
                if (env->ExceptionCheck()) return 0;
                return res;
            }
            
            BroadPhaseLayer GetBroadPhaseLayer(ObjectLayer inLayer) const override {
                JNIEnv* env = jniThread.getEnv();
                BroadPhaseLayer res = *((BroadPhaseLayer*) env->CallLongMethod(obj, BroadPhaseLayerInterface_getBroadPhaseLayer,
                    inLayer));
                if (env->ExceptionCheck()) throw std::runtime_error("abc");
                return res;
            }
        
        #if defined(JPH_EXTERNAL_PROFILE) || defined(JPH_PROFILE_ENABLED)
            const char* GetBroadPhaseLayerName(BroadPhaseLayer inLayer) const override {
                JNIEnv* env = jniThread.getEnv();
                jstring res = (jstring) env->CallObjectMethod(obj, BroadPhaseLayerInterface_getBroadPhaseLayerName,
                    &inLayer);
                if (env->ExceptionCheck()) return nullptr;
                return env->GetStringUTFChars(res, 0);
            }
        #endif
        };""")
public class BroadPhaseLayerInterface extends JoltNative {
    private BroadPhaseLayerInterface(long address) { super(address); }
    public static BroadPhaseLayerInterface ref(long address) { return address == 0 ? null : new BroadPhaseLayerInterface(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public BroadPhaseLayerInterface() { address = _ctor(); }
    @JniBind("return (jlong) new BroadPhaseLayerInterfaceImpl(env, obj);")
    private native long _ctor();

    public int getNumBroadPhaseLayers() { throw unimplemented(); }
    @JniCallback
    private int _getNumBroadPhaseLayers() { return getNumBroadPhaseLayers(); }

    public BroadPhaseLayer getBroadPhaseLayer(int layer) { throw unimplemented(); }
    @JniCallback
    private long _getBroadPhaseLayer(int layer) { return getBroadPhaseLayer(layer).getAddress(); }

    public String getBroadPhaseLayerName(BroadPhaseLayer layer) { throw unimplemented(); }
    @JniCallback
    private String _getBroadPhaseLayerName(long layer) { return getBroadPhaseLayerName(BroadPhaseLayer.ref(layer)); }
}
