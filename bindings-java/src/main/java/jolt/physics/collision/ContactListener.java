package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniCallback;
import jolt.jni.JniHeader;
import jolt.jni.JniInclude;
import jolt.math.JtVec3f;
import jolt.physics.body.BodyID;
import jolt.physics.collision.shape.SubShapeIDPair;

@JniInclude("<Jolt/Physics/Collision/ContactListener.h>")
public class ContactListener extends JoltNative {
    private ContactListener(long address) { super(address); }
    public static ContactListener ref(long address) { return address == 0 ? null : new ContactListener(address); }

    public ContactListener() { address = _ctor(); }
    private native long _ctor();

    public ValidateResult onContactValidate(BodyID body1, BodyID body2, JtVec3f baseOffset, CollideShapeResult collisionResult) { throw unimplemented(); }
    @JniCallback
    private int _onContactValidate(long body1, long body2, float baseOffsetX, float baseOffsetY, float baseOffsetZ, long collisionResult) { return onContactValidate(BodyID.ref(body1), BodyID.ref(body2), new JtVec3f(baseOffsetX, baseOffsetY, baseOffsetZ), CollideShapeResult.ref(collisionResult)).ordinal(); }

    public void onContactAdded(BodyID body1, BodyID body2, ContactManifold manifold, ContactSettings settings) {}
    @JniCallback
    private void _onContactAdded(long body1, long body2, long manifold, long settings) { onContactAdded(BodyID.ref(body1), BodyID.ref(body2), ContactManifold.ref(manifold), ContactSettings.ref(settings)); }

    public void onContactPersisted(BodyID body1, BodyID body2, ContactManifold manifold, ContactSettings settings) {}
    @JniCallback
    private void _onContactPersisted(long body1, long body2, long manifold, long settings) { onContactPersisted(BodyID.ref(body1), BodyID.ref(body2), ContactManifold.ref(manifold), ContactSettings.ref(settings)); }

    public void onContactRemoved(SubShapeIDPair subShapePair) {}
    @JniCallback
    private void _onContactRemoved(long subShapePair) { onContactRemoved(SubShapeIDPair.ref(subShapePair)); }
}
