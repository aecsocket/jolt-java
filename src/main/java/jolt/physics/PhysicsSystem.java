package jolt.physics;

import jolt.DestroyableJoltNative;
import jolt.Jolt;
import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.math.FVec3;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.NarrowPhaseQuery;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.BroadPhaseQuery;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;

import javax.annotation.Nullable;

import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public final class PhysicsSystem extends DestroyableJoltNative {
    //region Jolt-Pointer
    private PhysicsSystem(MemoryAddress handle) {
        super(handle);
    }

    public static PhysicsSystem at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new PhysicsSystem(addr);
    }
    //endregion Jolt-Pointer

    public static PhysicsSystem of(
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            BroadPhaseLayerInterface broadPhaseLayerInterface,
            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
            ObjectLayerPairFilter objectLayerPairFilter
    ) {
        return new PhysicsSystem(JPC_PhysicsSystem_Create(
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
    protected void destroyInternal() {
        JPC_PhysicsSystem_Destroy(handle);
    }

    public void setBodyActivationListener(@Nullable BodyActivationListener listener) {
        JPC_PhysicsSystem_SetBodyActivationListener(handle, Jolt.ptr(listener));
    }

    public @Nullable BodyActivationListener getBodyActivationListener() {
        return BodyActivationListener.at(JPC_PhysicsSystem_GetBodyActivationListener(handle));
    }

    public void setContactListener(@Nullable ContactListener listener) {
        JPC_PhysicsSystem_SetContactListener(handle, Jolt.ptr(listener));
    }

    public @Nullable ContactListener getContactListener() {
        return ContactListener.at(JPC_PhysicsSystem_GetContactListener(handle));
    }

    // TODO setCombineFriction
    // TODO setCombineRestitution

    public void setPhysicsSettings(PhysicsSettings settings) {
        JPC_PhysicsSystem_SetPhysicsSettings(handle, settings.address());
    }

    public void getPhysicsSettings(PhysicsSettings out) {
        JPC_PhysicsSystem_GetPhysicsSettings(handle, out.address());
    }

    public BodyInterface getBodyInterface() {
        return BodyInterface.at(JPC_PhysicsSystem_GetBodyInterface(handle));
    }

    public BodyInterface getBodyInterfaceNoLock() {
        return BodyInterface.at(JPC_PhysicsSystem_GetBodyInterfaceNoLock(handle));
    }

    public BroadPhaseQuery getBroadPhaseQuery() {
        return BroadPhaseQuery.at(JPC_PhysicsSystem_GetBroadPhaseQuery(handle));
    }

    public NarrowPhaseQuery getNarrowPhaseQuery() {
        return NarrowPhaseQuery.at(JPC_PhysicsSystem_GetNarrowPhaseQuery(handle));
    }

    public NarrowPhaseQuery getNarrowPhaseQueryNoLock() {
        return NarrowPhaseQuery.at(JPC_PhysicsSystem_GetNarrowPhaseQueryNoLock(handle));
    }

    public void optimizeBroadPhase() {
        JPC_PhysicsSystem_OptimizeBroadPhase(handle);
    }

    public void setGravity(FVec3 gravity) {
        JPC_PhysicsSystem_SetGravity(handle, gravity.address());
    }

    public void getGravity(FVec3 out) {
        JPC_PhysicsSystem_GetGravity(handle, out.address());
    }

    public int getNumBodies() {
        return JPC_PhysicsSystem_GetNumBodies(handle);
    }

    public int getNumActiveBodies() {
        return JPC_PhysicsSystem_GetNumActiveBodies(handle);
    }

    public int getMaxBodies() {
        return JPC_PhysicsSystem_GetMaxBodies(handle);
    }

    public void update(
            float deltaTime,
            int collisionSteps,
            int integrationSubSteps,
            TempAllocator tempAllocator,
            JobSystem jobSystem
    ) {
        JPC_PhysicsSystem_Update(handle,
                deltaTime,
                collisionSteps,
                integrationSubSteps,
                tempAllocator.address(),
                jobSystem.address()
        );
    }
}
