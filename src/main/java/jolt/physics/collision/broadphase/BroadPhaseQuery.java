package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.geometry.AABox;
import jolt.geometry.AABoxCast;
import jolt.geometry.OrientedBox;
import jolt.math.FVec3;
import jolt.physics.collision.FRayCast;
import jolt.physics.collision.ObjectLayerFilter;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public final class BroadPhaseQuery extends AddressedJoltNative {
    public static BroadPhaseQuery at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new BroadPhaseQuery(address);
    }

    private BroadPhaseQuery(MemoryAddress address) {
        super(address);
    }

    public void castRay(
            FRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CastRay(address,
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
        JPC_BroadPhaseQuery_CollideAABox(address,
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
        JPC_BroadPhaseQuery_CollideSphere(address,
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
        JPC_BroadPhaseQuery_CollidePoint(address,
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
        JPC_BroadPhaseQuery_CollideOrientedBox(address,
                box.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }

    public void castAABox(
            AABoxCast box,
            CollideShapeBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    ) {
        JPC_BroadPhaseQuery_CastAABox(address,
                box.address(),
                collector.address(),
                broadPhaseLayerFilter.address(),
                objectLayerFilter.address()
        );
    }
}
