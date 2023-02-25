package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.physics.body.Body;

@JniInclude("<Jolt/Physics/Collision/CollisionCollector.h>")
public interface CollisionCollector<R> {
    void onBody(Body body);

    void addHit(R result);
}
