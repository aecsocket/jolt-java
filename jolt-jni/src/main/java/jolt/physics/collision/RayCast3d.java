package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniNative;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
public final class RayCast3d {
    public JtVec3d origin = new JtVec3d();
    public JtVec3f direction = new JtVec3f();

    public RayCast3d() {}
    public RayCast3d(JtVec3d origin, JtVec3f direction) { set(origin, direction); }

    @JniCallback
    public void set(
            double originX, double originY, double originZ,
            float directionX, float directionY, float directionZ
    ) {
        origin.set(originX, originY, originZ);
        direction.set(directionX, directionY, directionZ);
    }

    public void set(JtVec3d origin, JtVec3f direction) {
        this.origin.set(origin);
        this.direction.set(direction);
    }

    public void set(RayCast3d r) {
        set(r.origin, r.direction);
    }

    @Override
    public String toString() { return String.format("RayCast3d(%s, %s)", origin, direction); }
}
