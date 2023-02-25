package jolt.physics.collision;

import io.github.aecsocket.jniglue.*;
import jolt.physics.body.BodyImpl;

@JniInclude("<Jolt/Physics/Collision/CollisionCollector.h>")
public interface CollisionCollector<R> {
    void onBody(BodyImpl body);

    void addHit(R result);
}
