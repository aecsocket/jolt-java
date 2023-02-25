package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.JoltEnvironment;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Collision/ShapeFilter.h>")
@JniReferenced
@JniTypeMapping("ShapeFilterImpl")
@JniHeader("""
        class ShapeFilterImpl : JNINative, public ShapeFilter {
        public:
            ShapeFilterImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            bool ShouldCollide(const SubShapeID &inSubShapeID2) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_ShapeFilter_shouldCollide(env, obj,
                    inSubShapeID2.GetValue());
            }
            
            bool ShouldCollide(const SubShapeID &inSubShapeID1, const SubShapeID &inSubShapeID2) const override {
                JNIEnv* env = jniThread.getEnv();
                return JNI_ShapeFilter_shouldPairCollide(env, obj,
                    inSubShapeID1.GetValue(), inSubShapeID2.GetValue());
            }
        };""")
public class ShapeFilter extends JoltNativeImpl {
    private ShapeFilter(long address) { super(address); }
    public static ShapeFilter ref(long address) { return address == 0 ? null : new ShapeFilter(address); }

    private static ShapeFilter passthrough;
    public static ShapeFilter passthrough() {
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

    public ShapeFilter() { address = _ctor(); }
    @JniBind("return (jlong) new ShapeFilterImpl(env, obj);")
    private native long _ctor();

    public int getBodyId() { return _getBodyId(address); }
    @JniBindSelf("return self->mBodyID2.GetIndexAndSequenceNumber();")
    private static native int _getBodyId(long _a);

    public boolean shouldCollide(int subShapeId) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldCollide(int subShapeId) { return shouldCollide(subShapeId); }

    public boolean shouldPairCollide(int subShapeId1, int subShapeId2) { throw unimplemented(); }
    @JniCallback
    private boolean _shouldPairCollide(int subShapeId1, int subShapeId2) { return shouldPairCollide(subShapeId1, subShapeId2); }

    private static class Passthrough extends ShapeFilter {
        @Override
        public void delete() { throw new IllegalStateException(DELETING_GLOBAL); }

        @Override
        public boolean shouldCollide(int bodyId) {
            return true;
        }

        @Override
        public boolean shouldPairCollide(int subShapeId1, int subShapeId2) {
            return true;
        }
    }
}
