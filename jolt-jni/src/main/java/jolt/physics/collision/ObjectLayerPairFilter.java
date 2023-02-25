package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/ObjectLayer.h>")
@JniReferenced
@JniTypeMapping("ObjectLayerPairFilterImpl")
@JniHeader("""
        class ObjectLayerPairFilterImpl : JNINative, ObjectLayerPairFilter {
        public:
            ObjectLayerPairFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(ObjectLayer inLayer1, ObjectLayer inLayer2) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_ObjectLayerPairFilter_shouldCollide(env, obj,
                    inLayer1, inLayer2);
            }
        };""")
public class ObjectLayerPairFilter extends JoltNativeImpl {
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

    public boolean shouldCollide(short layer1, short layer2) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(short layer1, short layer2) { return shouldCollide(layer1, layer2); }
}
