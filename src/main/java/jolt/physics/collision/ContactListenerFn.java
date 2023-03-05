package jolt.physics.collision;

import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.body.Body;
import jolt.physics.collision.shape.SubShapeIdPair;

public interface ContactListenerFn {
    void onContactAdded(Body body1, Body body2, ContactManifold manifold, ContactSettings settings);

    void onContactPersisted(Body body1, Body body2, ContactManifold manifold, ContactSettings settings);

    void onContactRemoved(SubShapeIdPair subShapeIdPair);

    interface F extends ContactListenerFn {
        ValidateResult onContactValidate(Body body1, Body body2, FVec3 baseOffset, CollideShapeResult collisionResult);
    }

    interface D extends ContactListenerFn {
        ValidateResult onContactValidate(Body body1, Body body2, DVec3 baseOffset, CollideShapeResult collisionResult);
    }
}
