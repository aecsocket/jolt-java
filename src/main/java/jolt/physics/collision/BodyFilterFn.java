package jolt.physics.collision;

import jolt.physics.body.Body;

public interface BodyFilterFn {
    boolean shouldCollide(int bodyId);

    boolean shouldCollideLocked(Body body);
}
