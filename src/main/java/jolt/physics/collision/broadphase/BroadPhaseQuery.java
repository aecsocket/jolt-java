package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.physics.collision.FRayCast;
import jolt.physics.collision.DRayCast;
import jolt.physics.collision.ObjectLayerFilter;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

public abstract sealed class BroadPhaseQuery extends AddressedJoltNative
        permits BroadPhaseQuery.F, BroadPhaseQuery.D {
    public static BroadPhaseQuery at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(address) : new F(address);
    }

    private BroadPhaseQuery(MemoryAddress address) {
        super(address);
    }

    public abstract void castRayF(
            FRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    );

    public abstract void castRayD(
            DRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    );

    static final class F extends BroadPhaseQuery {
        private F(MemoryAddress address) {
            super(address);
        }

        @Override
        public void castRayF(
                FRayCast ray,
                RayCastBodyCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter
        ) {
            try (var session = MemorySession.openConfined()) {
                jolt.headers_f.JoltPhysicsC.JPC_BroadPhaseQuery_CastRay(address,
                        ray.allocate(session),
                        collector.address(),
                        broadPhaseLayerFilter.address(),
                        objectLayerFilter.address()
                );
            }
        }

        @Override
        public void castRayD(
            DRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
        ) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends BroadPhaseQuery {
        private D(MemoryAddress address) {
            super(address);
        }

        @Override
        public void castRayF(
                FRayCast ray,
                RayCastBodyCollector collector,
                BroadPhaseLayerFilter broadPhaseLayerFilter,
                ObjectLayerFilter objectLayerFilter
        ) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void castRayD(
            DRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
        ) {
            try (var session = MemorySession.openConfined()) {
                jolt.headers_d.JoltPhysicsC.JPC_BroadPhaseQuery_CastRay(address,
                        ray.allocate(session),
                        collector.address(),
                        broadPhaseLayerFilter.address(),
                        objectLayerFilter.address()
                );
            }
        }
    }
}
