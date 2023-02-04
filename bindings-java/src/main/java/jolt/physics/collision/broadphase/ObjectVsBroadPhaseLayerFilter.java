package jolt.physics.collision.broadphase;

import jolt.JoltNative;
import jolt.jni.JniBind;
import jolt.jni.JniCallback;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
@JniHeader("""
        class ObjectVsBroadPhaseLayerFilterImpl : JNINative, ObjectVsBroadPhaseLayerFilter {
        public:
            ObjectVsBroadPhaseLayerFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(ObjectLayer inLayer1, BroadPhaseLayer inLayer2) const override {
                return env->CallBooleanMethod(obj, ObjectVsBroadPhaseLayerFilter_shouldCollide,
                    inLayer1, &inLayer2);
            }
        };""")
public class ObjectVsBroadPhaseLayerFilter extends JoltNative {
    private ObjectVsBroadPhaseLayerFilter(long address) { super(address); }
    public static ObjectVsBroadPhaseLayerFilter ref(long address) { return address == 0 ? null : new ObjectVsBroadPhaseLayerFilter(address); }

    public ObjectVsBroadPhaseLayerFilter() { address = _ctor(); }
    @JniBind("return (long) new ObjectVsBroadPhaseLayerFilterImpl(env, obj);")
    private native long _ctor();

    public boolean shouldCollide(int layer1, BroadPhaseLayer layer2) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(int layer1, long layer2) { return shouldCollide(layer1, BroadPhaseLayer.ref(layer2)); }
}
