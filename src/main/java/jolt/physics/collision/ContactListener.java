package jolt.physics.collision.broadphase;

import jolt.*;
import jolt.physics.body.BodyID;

public class ContactListener extends JoltNative {
    protected ContactListener(long address) { super(address); }
    public static ContactListener ofPointer(long address) { return new ContactListener(address); }

    public ContactListener() {
        address = _create();
    }
    private static native long _create();

    public ValidateResult onContactValidate(BodyID body1, BodyID body2, ContactManifold manifold, ContactSettings settings) { throw unimplemented(); }
    private int _onContactValidate(long body1, long body2, long manifold, long settings) { return onContactValidate(BodyID.ofPointer(body1), BodyID.ofPointer(body2), ContactManifold.ofPointer(manifold), ContactSettings.ofPointer(settings)).ordinal(); }

    public void onContactAdded(BodyID body1, BodyID body2, ContactManifold manifold, ContactSettings settings) {}
    private void _onContactAdded(long body1, long body2, long manifold, long settings) { onContactAdded(BodyID.ofPointer(body1), BodyID.ofPointer(body2), ContactManifold.ofPointer(manifold), ContactSettings.ofPointer(settings)); }

    public void onContactPersisted(BodyID body1, BodyID body2, ContactManifold manifold, ContactSettings settings) {}
    private void _onContactPersisted(long body1, long body2, long manifold, long settings) { onContactPersisted(BodyID.ofPointer(body1), BodyID.ofPointer(body2), ContactManifold.ofPointer(manifold), ContactSettings.ofPointer(settings)); }

    public void onContactRemoved(SubShapeIDPair subShapePair) {}
    private void _onContactRemoved(long subShapePair) { onContactRemoved(SubShapeIDPair.ofPointer(subShapePair)); }
}
