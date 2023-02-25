package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.JoltEnvironment;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/ObjectLayer.h>")
@JniReferenced
@JniTypeMapping("ObjectLayerFilterImpl")
@JniHeader("""
        class ObjectLayerFilterImpl : JNINative, ObjectLayerFilter {
        public:
            ObjectLayerFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(ObjectLayer inLayer) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_ObjectLayerFilter_shouldCollide(env, obj,
                    inLayer);
            }
        };""")
public class ObjectLayerFilter extends JoltNativeImpl {
    private ObjectLayerFilter(long address) { super(address); }
    public static ObjectLayerFilter ref(long address) { return address == 0 ? null : new ObjectLayerFilter(address); }

    private static ObjectLayerFilter passthrough;
    public static ObjectLayerFilter passthrough() {
        if (passthrough != null) return passthrough;
        if (!JoltEnvironment.isLoaded()) throw new IllegalStateException(NOT_LOADED);
        passthrough = new Passthrough();
        return passthrough;
    }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public ObjectLayerFilter() { address = _ctor(); }
    @JniBind("return (jlong) new BroadPhaseLayerFilterImpl(env, obj);")
    private native long _ctor();

    public boolean shouldCollide(short layer) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(short layer) { return shouldCollide(layer); }

    private static class Passthrough extends ObjectLayerFilter {
        @Override
        public void delete() { throw new IllegalStateException(DELETING_GLOBAL); }

        @Override
        public boolean shouldCollide(short layer) {
            return true;
        }
    }
}
