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
                env->ExceptionCheck();
                return res;
            }
            
            BroadPhaseLayer GetBroadPhaseLayer(ObjectLayer inLayer) const override {
                JNIEnv* env = jniThread.getEnv();
                uint8 res = env->CallByteMethod(obj, BroadPhaseLayerInterface_getBroadPhaseLayer,
                    inLayer);
                env->ExceptionCheck();
                return BroadPhaseLayer(res);
            }
        
        #if defined(JPH_EXTERNAL_PROFILE) || defined(JPH_PROFILE_ENABLED)
            const char* GetBroadPhaseLayerName(BroadPhaseLayer inLayer) const override {
                JNIEnv* env = jniThread.getEnv();
                jstring res = (jstring) env->CallObjectMethod(obj, BroadPhaseLayerInterface_getBroadPhaseLayerName,
                    (BroadPhaseLayer::Type) inLayer);
                env->ExceptionCheck();
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

    public byte getBroadPhaseLayer(int layer) { throw unimplemented(); }
    @JniCallback
    private byte _getBroadPhaseLayer(int layer) { return getBroadPhaseLayer(layer); }

    public String getBroadPhaseLayerName(byte layer) { throw unimplemented(); }
    @JniCallback
    private String _getBroadPhaseLayerName(byte layer) { return getBroadPhaseLayerName(layer); }
}
