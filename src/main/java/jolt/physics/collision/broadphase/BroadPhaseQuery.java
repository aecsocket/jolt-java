package jolt.physics.collision.broadphase;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.physics.collision.FRayCast;

import java.lang.foreign.MemoryAddress;

public abstract sealed class BroadPhaseQuery extends AddressedJoltNative permits BroadPhaseQuery.F, BroadPhaseQuery.D {
    public static BroadPhaseQuery at(MemoryAddress address) {
        return address.address() == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(address) : new F(address);
    }

    private BroadPhaseQuery(MemoryAddress address) {
        super(address);
    }

    public abstract void castRay(
            FRayCast ray,
            RayCastBodyCollector collector,
            BroadPhaseLayerFilter broadPhaseLayerFilter,
            ObjectLayerFilter objectLayerFilter
    );

    static final class F extends BroadPhaseQuery {
        private F(MemoryAddress address) {
            super(address);
        }
    }

    static final class D extends BroadPhaseQuery {
        private D(MemoryAddress address) {
            super(address);
        }
    }
}
