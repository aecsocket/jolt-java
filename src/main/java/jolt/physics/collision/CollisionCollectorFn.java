package jolt.physics.collision;

import jolt.physics.body.Body;

public interface CollisionCollectorFn<R> {
    default void onBody(Body body) {}

    void addHit(R result);
}
