package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/Collision/ObjectLayer.h>")
@JniType("ObjectLayerPairFilterImpl")
@JniHeader("""
        class ObjectLayerPairFilterImpl : JNINative, ObjectLayerPairFilter {
        public:
            ObjectLayerPairFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(ObjectLayer inLayer1, ObjectLayer inLayer2) const override {
                JNIEnv* env = jniThread.getEnv();
                return env->CallBooleanMethod(obj, ObjectLayerPairFilter_shouldCollide,
                    inLayer1, inLayer2);
            }
        };""")
public class ObjectLayerPairFilter extends JoltNative {
    private ObjectLayerPairFilter(long address) { super(address); }
    public static ObjectLayerPairFilter ref(long address) { return address == 0 ? null : new ObjectLayerPairFilter(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public ObjectLayerPairFilter() { address = _ctor(); }
    @JniBind("return (jlong) new ObjectLayerPairFilterImpl(env, obj);")
    private native long _ctor();

    public boolean shouldCollide(int layer1, int layer2) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(int layer1, int layer2) { return shouldCollide(layer1, layer2); }
}
