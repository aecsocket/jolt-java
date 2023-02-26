package jolt.physics;

import jolt.AbstractJoltNative;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;

import static jolt.headers.JoltPhysicsC.*;

public final class PhysicsSystem extends AbstractJoltNative {
    public PhysicsSystem(
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            BroadPhaseLayerInterface broadPhaseLayerInterface,
            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
            ObjectLayerPairFilter objectLayerPairFilter
    ) {
        super(JPC_PhysicsSystem_Create(
                maxBodies,
                numBodyMutexes,
                maxBodyPairs,
                maxContactConstraints,
                broadPhaseLayerInterface.address(),
                objectVsBroadPhaseLayerFilter.address(),
                objectLayerPairFilter.address()
        ));
    }

    @Override
    protected void deleteInternal() {
        JPC_PhysicsSystem_Destroy(address);
    }
}
