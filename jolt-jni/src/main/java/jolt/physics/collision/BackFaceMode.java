package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/BackFaceMode.h>")
public enum BackFaceMode {
    IGNORE_BACK_FACES,
    COLLIDE_WITH_BACK_FACES;
}
