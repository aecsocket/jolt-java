package jolt.physics.collision.broadphase;

import jolt.JoltNative;
import jolt.jni.JniBind;
import jolt.jni.JniCallback;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
@JniHeader("""
        class BroadPhaseLayerInterfaceImpl : JNINative, BroadPhaseLayerInterface {
        public:
            BroadPhaseLayerInterfaceImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            uint GetNumBroadPhaseLayers() const override {
                return env->CallIntMethod(obj, BroadPhaseLayerInterface_getNumBroadPhaseLayers);
            }
            
            BroadPhaseLayer GetBroadPhaseLayer(ObjectLayer inLayer) const override {
                return *((BroadPhaseLayer*) env->CallLongMethod(obj, BroadPhaseLayerInterface_getBroadPhaseLayer,
                    inLayer));
            }
        
        #if defined(JPH_EXTERNAL_PROFILE) || defined(JPH_PROFILE_ENABLED)
            const char* GetBroadPhaseLayerName(BroadPhaseLayer inLayer) const override {
                jstring res = (jstring) env->CallObjectMethod(obj, BroadPhaseLayerInterface_getBroadPhaseLayerName,
                    &inLayer);
                return env->GetStringUTFChars(res, 0);
            }
        #endif
        };""")
public class BroadPhaseLayerInterface extends JoltNative {
    private BroadPhaseLayerInterface(long address) { super(address); }
    public static BroadPhaseLayerInterface ref(long address) { return address == 0 ? null : new BroadPhaseLayerInterface(address); }

    public BroadPhaseLayerInterface() { address = _ctor(); }
    @JniBind("return (long) new BroadPhaseLayerInterfaceImpl(env, obj);")
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
