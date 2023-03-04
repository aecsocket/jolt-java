package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.geometry.AABox;
import jolt.geometry.AABoxCast;
import jolt.geometry.OrientedBox;
import jolt.math.FVec3;
import jolt.physics.collision.FRayCast;
import jolt.physics.collision.ObjectLayerFilter;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class BroadPhaseQuery extends AddressedJoltNative {
    //region Jolt-Pointer
    private BroadPhaseQuery(MemoryAddress handle) {
        super(handle);
    }

    public static BroadPhaseQuery at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new BroadPhaseQuery(addr);
    }
    //endregion Jolt-Pointer

    public void castRay(
            FRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CastRay(handle,
                ray.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }

    public void collideAABox(
            AABox box,
            CollideShapeBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CollideAABox(handle,
                box.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }

    public void collideSphere(
            FVec3 center,
            float radius,
            CollideShapeBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CollideSphere(handle,
                center.address(),
                radius,
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }

    public void collidePoint(
            FVec3 point,
            CollideShapeBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CollidePoint(handle,
                point.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }

    public void collideOrientedBox(
            OrientedBox box,
            CollideShapeBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CollideOrientedBox(handle,
                box.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }

    public void castAABox(
            AABoxCast box,
            CastShapeBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CastAABox(handle,
                box.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }
}
