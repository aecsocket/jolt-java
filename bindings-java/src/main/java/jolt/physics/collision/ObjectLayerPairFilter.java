package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniBind;
import jolt.jni.JniCallback;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/ObjectLayer.h>")
@JniHeader("""
        class ObjectLayerPairFilterImpl : JNINative, ObjectLayerPairFilter {
        public:
            ObjectLayerPairFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(ObjectLayer inLayer1, ObjectLayer inLayer2) const override {
                return env->CallBooleanMethod(obj, ObjectLayerPairFilter_shouldCollide,
                    inLayer1, inLayer2);
            }
        };""")
public class ObjectLayerPairFilter extends JoltNative {
    private ObjectLayerPairFilter(long address) { super(address); }
    public static ObjectLayerPairFilter ref(long address) { return address == 0 ? null : new ObjectLayerPairFilter(address); }

    public ObjectLayerPairFilter() { address = _ctor(); }
    @JniBind("return (long) new ObjectLayerPairFilterImpl(env, obj);")
    private native long _ctor();

    public boolean shouldCollide(int layer1, int layer2) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(int layer1, int layer2) { return shouldCollide(layer1, layer2); }
}
