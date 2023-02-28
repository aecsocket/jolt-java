package jolt.physics.collision;

import jolt.physics.body.Body;

public interface CollisionCollectorFunctions<R> {
    void onBody(Body body);

    void addHit(R result);
}
