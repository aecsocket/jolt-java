package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.*;
import jolt.JoltEnvironment;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
@JniReferenced
@JniTypeMapping("BroadPhaseLayerFilterImpl")
@JniHeader("""
        class BroadPhaseLayerFilterImpl : JNINative, BroadPhaseLayerFilter {
        public:
            BroadPhaseLayerFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(BroadPhaseLayer inLayer) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_BroadPhaseLayerFilter_shouldCollide(env, obj,
                    (BroadPhaseLayer::Type) inLayer);
            }
        };""")
public class BroadPhaseLayerFilter extends JoltNativeImpl {
    private BroadPhaseLayerFilter(long address) { super(address); }
    public static BroadPhaseLayerFilter ref(long address) { return address == 0 ? null : new BroadPhaseLayerFilter(address); }

    private static BroadPhaseLayerFilter passthrough;
    public static BroadPhaseLayerFilter passthrough() {
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

    public BroadPhaseLayerFilter() { address = _ctor(); }
    @JniBind("return (jlong) new BroadPhaseLayerFilterImpl(env, obj);")
    private native long _ctor();

    public boolean shouldCollide(byte layer) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(byte layer) { return shouldCollide(layer); }

    private static class Passthrough extends BroadPhaseLayerFilter {
        @Override
        public void delete() { throw new IllegalStateException(DELETING_GLOBAL); }

        @Override
        public boolean shouldCollide(byte layer) {
            return true;
        }
    }
}
