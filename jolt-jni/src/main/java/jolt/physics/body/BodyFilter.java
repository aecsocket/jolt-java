package jolt.physics.body;

import io.github.aecsocket.jniglue.*;
import jolt.JoltEnvironment;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/Body/BodyFilter.h>")
@JniReferenced
@JniTypeMapping("BodyFilterImpl")
@JniHeader("""
        class BodyFilterImpl : JNINative, BodyFilter {
        public:
            BodyFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(const BodyID &inBodyID) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_BodyFilter_shouldCollide(env, obj,
                    inBodyID.GetIndexAndSequenceNumber());
            }
            
            bool ShouldCollideLocked(const Body &inBody) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_BodyFilter_shouldCollideLocked(env, obj,
                    (jlong) &inBody);
            }
        };""")
public class BodyFilter extends JoltNative {
    private BodyFilter(long address) { super(address); }
    public static BodyFilter ref(long address) { return address == 0 ? null : new BodyFilter(address); }

    private static BodyFilter passthrough;
    public static BodyFilter passthrough() {
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

    public BodyFilter() { address = _ctor(); }
    @JniBind("return (jlong) new BodyFilterImpl(env, obj);")
    private native long _ctor();

    public boolean shouldCollide(int bodyId) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(int bodyId) { return shouldCollide(bodyId); }

    public boolean shouldCollideLocked(Body body) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollideLocked(long body) { return shouldCollideLocked(Body.ref(body)); }

    private static class Passthrough extends BodyFilter {
        @Override
        public void delete() { throw new IllegalStateException(DELETING_GLOBAL); }

        @Override
        public boolean shouldCollide(int bodyId) {
            return true;
        }

        @Override
        public boolean shouldCollideLocked(Body body) {
            return true;
        }
    }
}
