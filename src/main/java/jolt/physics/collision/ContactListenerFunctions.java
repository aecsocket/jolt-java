package jolt.physics.collision;

import jolt.math.RVec3;
import jolt.physics.collision.shape.SubShapeIDPair;

public interface ContactListenerFunctions {
    ValidateResult onContactValidate(int body1, int body2, RVec3 baseOffset, CollideShapeResult collisionResult);

    void onContactAdded(int body1, int body2, ContactManifold manifold, ContactSettings settings);

    void onContactPersisted(int body1, int body2, ContactManifold manifold, ContactSettings settings);

    void onContactRemoved(SubShapeIDPair subShapeIdPair);
}
