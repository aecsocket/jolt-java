package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;
import jolt.math.JtVec3f;
import jolt.physics.body.Body;
import jolt.physics.collision.shape.SubShapeIdPair;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
@JniType("ContactListenerImpl")
@JniHeader("""
        class ContactListenerImpl : JNINative, ContactListener {
        public:
            ContactListenerImpl(JNIEnv* env, jobject obj) : JNINative(env, obj) {}
            
            ValidateResult OnContactValidate(const Body& inBody1, const Body& inBody2, RVec3Arg inBaseOffset, const CollideShapeResult &inCollisionResult) override {
                JNIEnv* env = jniThread.getEnv();
                return (ValidateResult) env->CallIntMethod(obj, ContactListener_onContactValidate,
                    &inBody1, &inBody2, inBaseOffset.GetX(), inBaseOffset.GetY(), inBaseOffset.GetZ(), &inCollisionResult);
            }
            
            void OnContactAdded(const Body& inBody1, const Body& inBody2, const ContactManifold& inManifold, ContactSettings &ioSettings) override {
                JNIEnv* env = jniThread.getEnv();
                env->CallVoidMethod(obj, ContactListener_onContactAdded,
                    &inBody1, &inBody2, &inManifold, &ioSettings);
            }
            
            void OnContactPersisted(const Body& inBody1, const Body& inBody2, const ContactManifold& inManifold, ContactSettings &ioSettings) override {
                JNIEnv* env = jniThread.getEnv();
                env->CallVoidMethod(obj, ContactListener_onContactPersisted,
                    &inBody1, &inBody2, &inManifold, &ioSettings);
            }
            
            void OnContactRemoved(const SubShapeIDPair& inSubShapePair) override {
                JNIEnv* env = jniThread.getEnv();
                env->CallVoidMethod(obj, ContactListener_onContactAdded,
                    &inSubShapePair);
            }
        };""")
public class ContactListener extends JoltNative {
    private ContactListener(long address) { super(address); }
    public static ContactListener ref(long address) { return address == 0 ? null : new ContactListener(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public ContactListener() { address = _ctor(); }
    @JniBind("return (jlong) new ContactListenerImpl(env, obj);")
    private native long _ctor();

    public ValidateResult onContactValidate(Body body1, Body body2, JtVec3f baseOffset, CollideShapeResult collisionResult) { throw unimplemented(); }
    @JniCallback
    private int _onContactValidate(long body1, long body2, float baseOffsetX, float baseOffsetY, float baseOffsetZ, long collisionResult) { return onContactValidate(Body.ref(body1), Body.ref(body2), new JtVec3f(baseOffsetX, baseOffsetY, baseOffsetZ), CollideShapeResult.ref(collisionResult)).ordinal(); }

    public void onContactAdded(Body body1, Body body2, ContactManifold manifold, ContactSettings settings) {}
    @JniCallback
    private void _onContactAdded(long body1, long body2, long manifold, long settings) { onContactAdded(Body.ref(body1), Body.ref(body2), ContactManifold.ref(manifold), ContactSettings.ref(settings)); }

    public void onContactPersisted(Body body1, Body body2, ContactManifold manifold, ContactSettings settings) {}
    @JniCallback
    private void _onContactPersisted(long body1, long body2, long manifold, long settings) { onContactPersisted(Body.ref(body1), Body.ref(body2), ContactManifold.ref(manifold), ContactSettings.ref(settings)); }

    public void onContactRemoved(SubShapeIdPair subShapePair) {}
    @JniCallback
    private void _onContactRemoved(long subShapePair) { onContactRemoved(SubShapeIdPair.ref(subShapePair)); }
}
