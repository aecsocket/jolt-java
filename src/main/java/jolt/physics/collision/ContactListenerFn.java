package jolt.physics.collision;

import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.physics.collision.shape.SubShapeIdPair;

public interface ContactListenerFn {
    void onContactAdded(int body1, int body2, ContactManifold manifold, ContactSettings settings);

    void onContactPersisted(int body1, int body2, ContactManifold manifold, ContactSettings settings);

    void onContactRemoved(SubShapeIdPair subShapeIdPair);

    interface F extends ContactListenerFn {
        ValidateResult onContactValidate(int body1, int body2, FVec3 baseOffset, CollideShapeResult collisionResult);
    }

    interface D extends ContactListenerFn {
        ValidateResult onContactValidate(int body1, int body2, DVec3 baseOffset, CollideShapeResult collisionResult);
    }
}
