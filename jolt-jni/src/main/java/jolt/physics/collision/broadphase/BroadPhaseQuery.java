package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import jolt.geometry.AABox;
import jolt.geometry.OrientedBox;
import jolt.math.JtVec3f;
import jolt.physics.collision.AABoxCast;
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
        _castRay(
                address,
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
    private static native void _castRay(
            long _a,
            float rayOriginX, float rayOriginY, float rayOriginZ, float rayDirectionX, float rayDirectionY, float rayDirectionZ,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );

    public void collideAABox(AABox box, CollideShapeBodyCollector collector, BroadPhaseLayerFilter broadPhaseLayerFilter, ObjectLayerFilter objectLayerFilter) {
        _collideAABox(
                address,
                box.min.x, box.min.y, box.min.z, box.max.x, box.max.y, box.max.z,
                collector.getAddress(), broadPhaseLayerFilter.getAddress(), objectLayerFilter.getAddress()
        );
    }
    @JniBindSelf("""
            self->CollideAABox(
                AABox(Vec3(boxMinX, boxMinY, boxMinZ), Vec3(boxMaxX, boxMaxY, boxMaxZ)),
                *((CollideShapeBodyCollector*) collector),
                *((BroadPhaseLayerFilter*) broadPhaseLayerFilter),
                *((ObjectLayerFilter*) objectLayerFilter)
            );""")
    private static native void _collideAABox(
            long _a,
            float boxMinX, float boxMinY, float boxMinZ, float boxMaxX, float boxMaxY, float boxMaxZ,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );

    public void collideSphere(JtVec3f center, float radius, CollideShapeBodyCollector collector, BroadPhaseLayerFilter broadPhaseLayerFilter, ObjectLayerFilter objectLayerFilter) {
        _collideSphere(
                address,
                center.x, center.y, center.z,
                radius,
                collector.getAddress(), broadPhaseLayerFilter.getAddress(), objectLayerFilter.getAddress()
        );
    }
    @JniBindSelf("""
            self->CollideSphere(
                Vec3(centerX, centerY, centerZ),
                radius,
                *((CollideShapeBodyCollector*) collector),
                *((BroadPhaseLayerFilter*) broadPhaseLayerFilter),
                *((ObjectLayerFilter*) objectLayerFilter)
            );""")
    private static native void _collideSphere(
            long _a,
            float centerX, float centerY, float centerZ,
            float radius,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );

    public void collidePoint(JtVec3f point, CollideShapeBodyCollector collector, BroadPhaseLayerFilter broadPhaseLayerFilter, ObjectLayerFilter objectLayerFilter) {
        _collidePoint(
                address,
                point.x, point.y, point.z,
                collector.getAddress(), broadPhaseLayerFilter.getAddress(), objectLayerFilter.getAddress()
        );
    }
    @JniBindSelf("""
            self->CollidePoint(
                Vec3(pointX, pointY, pointZ),
                *((CollideShapeBodyCollector*) collector),
                *((BroadPhaseLayerFilter*) broadPhaseLayerFilter),
                *((ObjectLayerFilter*) objectLayerFilter)
            );""")
    private static native void _collidePoint(
            long _a,
            float pointX, float pointY, float pointZ,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );

    public void collideOrientedBox(OrientedBox box, CollideShapeBodyCollector collector, BroadPhaseLayerFilter broadPhaseLayerFilter, ObjectLayerFilter objectLayerFilter) {
        _collideOrientedBox(
                address,
                box.orientation.n00, box.orientation.n01, box.orientation.n02, box.orientation.n03,
                box.orientation.n10, box.orientation.n11, box.orientation.n12, box.orientation.n13,
                box.orientation.n20, box.orientation.n21, box.orientation.n22, box.orientation.n23,
                box.orientation.n30, box.orientation.n31, box.orientation.n32,
                box.halfExtent.x, box.halfExtent.y, box.halfExtent.z,
                collector.getAddress(), broadPhaseLayerFilter.getAddress(), objectLayerFilter.getAddress()
        );
    }
    @JniBindSelf("""
            OrientedBox box(
                Mat44(
                    Vec4(boxOrientation00, boxOrientation01, boxOrientation02, boxOrientation03),
                    Vec4(boxOrientation10, boxOrientation11, boxOrientation12, boxOrientation13),
                    Vec4(boxOrientation20, boxOrientation21, boxOrientation22, boxOrientation23),
                    Vec3(boxOrientation30, boxOrientation31, boxOrientation32)
                ),
                Vec3(boxHalfExtentX, boxHalfExtentY, boxHalfExtentZ)
            );
            self->CollideOrientedBox(
                box,
                *((CollideShapeBodyCollector*) collector),
                *((BroadPhaseLayerFilter*) broadPhaseLayerFilter),
                *((ObjectLayerFilter*) objectLayerFilter)
            );""")
    private static native void _collideOrientedBox(
            long _a,
            float boxOrientation00, float boxOrientation01, float boxOrientation02, float boxOrientation03,
            float boxOrientation10, float boxOrientation11, float boxOrientation12, float boxOrientation13,
            float boxOrientation20, float boxOrientation21, float boxOrientation22, float boxOrientation23,
            float boxOrientation30, float boxOrientation31, float boxOrientation32,
            float boxHalfExtentX, float boxHalfExtentY, float boxHalfExtentZ,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );

    public void castAABox(AABoxCast box, CastShapeBodyCollector collector, BroadPhaseLayerFilter broadPhaseLayerFilter, ObjectLayerFilter objectLayerFilter) {
        _castAABox(
                address,
                box.box.min.x, box.box.min.y, box.box.min.z, box.box.max.x, box.box.max.y, box.box.max.z,
                box.direction.x, box.direction.y, box.direction.z,
                collector.getAddress(), broadPhaseLayerFilter.getAddress(), objectLayerFilter.getAddress()
        );
    }
    @JniBindSelf("""
            AABoxCast cast;
            cast.mBox = AABox(Vec3(castBoxMinX, castBoxMinY, castBoxMinZ), Vec3(castBoxMaxX, castBoxMaxY, castBoxMaxZ));
            cast.mDirection = Vec3(castDirectionX, castDirectionY, castDirectionZ);
            self->CastAABox(
                cast,
                *((CastShapeBodyCollector*) collector),
                *((BroadPhaseLayerFilter*) broadPhaseLayerFilter),
                *((ObjectLayerFilter*) objectLayerFilter)
            );""")
    private static native void _castAABox(
            long _a,
            float castBoxMinX, float castBoxMinY, float castBoxMinZ, float castBoxMaxX, float castBoxMaxY, float castBoxMaxZ,
            float castDirectionX, float castDirectionY, float castDirectionZ,
            long collector, long broadPhaseLayerFilter, long objectLayerFilter
    );
}
