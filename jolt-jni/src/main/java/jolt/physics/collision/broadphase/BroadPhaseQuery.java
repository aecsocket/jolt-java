package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import jolt.physics.collision.ObjectLayerFilter;
import jolt.physics.collision.RayCast3f;

@JniInclude("""
        <Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>
        <Jolt/Physics/Collision/RayCast.h>""")
@JniTypeMapping("BroadPhaseQuery")
public class BroadPhaseQuery extends JoltNative {
    protected BroadPhaseQuery(long address) { super(address); }
    public static BroadPhaseQuery ref(long address) { return address == 0 ? null : new BroadPhaseQuery(address); }

    public void castRay(RayCast3f ray, RayCastBodyCollector collector, BroadPhaseLayerFilter broadPhaseLayerFilter, ObjectLayerFilter objectLayerFilter) {
        _castRay(address,
                ray.origin.x, ray.origin.y, ray.origin.z, ray.direction.x, ray.direction.y, ray.direction.z,
                collector.getAddress(), broadPhaseLayerFilter.getAddress(), objectLayerFilter.getAddress()
        );
    }
    @JniBindSelf("""
            RayCast ray;
            ray.mOrigin = Vec3(rayOriginX, rayOriginY, rayOriginZ);
            ray.mDirection = Vec3(rayDirectionX, rayDirectionY, rayDirectionZ);
            self->CastRay(
                ray,
                *((RayCastBodyCollector*) collector),
                *((BroadPhaseLayerFilter*) broadPhaseLayerFilter),
                *((ObjectLayerFilter*) objectLayerFilter)
            );""")
    private static native void _castRay(long _a,
            float rayOriginX, float rayOriginY, float rayOriginZ, float rayDirectionX, float rayDirectionY, float rayDirectionZ,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );
}
