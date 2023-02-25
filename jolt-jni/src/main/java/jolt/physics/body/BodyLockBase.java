package jolt.physics.body;

import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;

public sealed interface BodyLockBase permits BodyLockRead, BodyLockWrite {
    boolean succeeded();

    boolean succeededAndIsInBroadPhase();

    Body getBody();
}
