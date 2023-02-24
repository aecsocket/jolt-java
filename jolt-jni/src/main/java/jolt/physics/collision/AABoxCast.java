package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniNative;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import jolt.geometry.AABox;
import jolt.math.JtVec3f;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniInclude("<Jolt/Physics/Collision/AABoxCast.h>")
@JniReferenced
public final class AABoxCast {
    public AABox box = new AABox();
    public JtVec3f direction = new JtVec3f();

    public AABoxCast() {}
    public AABoxCast(AABox box, JtVec3f direction) {
        set(box, direction);
    }

    @JniCallback
    public void set(
            float boxMinX, float boxMinY, float boxMinZ, float boxMaxX, float boxMaxY, float boxMaxZ,
            float directionX, float directionY, float directionZ
    ) {
        box.set(boxMinX, boxMinY, boxMinZ, boxMaxX, boxMaxY, boxMaxZ);
        direction.set(directionX, directionY, directionZ);
    }

    public void set(AABox box, JtVec3f direction) {
        this.box.set(box);
        this.direction.set(direction);
    }

    @Override
    public String toString() { return "AABoxCast(" + box + ", " + direction + ")"; }
}
