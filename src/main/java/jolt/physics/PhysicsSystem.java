package jolt.physics;

import jolt.DeletableJoltNative;
import jolt.Jolt;
import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.math.FVec3;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyInterface;
import jolt.physics.body.BodyLockInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.NarrowPhaseQuery;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.BroadPhaseQuery;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.constraint.Constraint;

import javax.annotation.Nullable;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;
import java.util.Collection;

import static jolt.headers.JoltPhysicsC.*;

public final class PhysicsSystem extends DeletableJoltNative {
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
    protected void deleteInternal() {
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

    public void addConstraint(Constraint constraint) {
        JPC_PhysicsSystem_AddConstraint(handle, constraint.address());
    }

    public void addConstraints(Constraint[] constraints) {
        try (var arena = MemorySession.openConfined()) {
            var nConstraints = arena.allocateArray(C_POINTER, constraints.length);
            for (int i = 0; i < constraints.length; i++) {
                nConstraints.setAtIndex(C_POINTER, i, constraints[i].address());
            }
            JPC_PhysicsSystem_AddConstraints(handle, nConstraints, constraints.length);
        }
    }

    public void addConstraints(Collection<? extends Constraint> constraints) {
        addConstraints(constraints.toArray(new Constraint[0]));
    }

    public void removeConstraint(Constraint constraint) {
        JPC_PhysicsSystem_RemoveConstraint(handle, constraint.address());
    }

    public void removeConstraints(Constraint[] constraints) {
        try (var arena = MemorySession.openConfined()) {
            var nConstraints = arena.allocateArray(C_POINTER, constraints.length);
            for (int i = 0; i < constraints.length; i++) {
                nConstraints.setAtIndex(C_POINTER, i, constraints[i].address());
            }
            JPC_PhysicsSystem_RemoveConstraints(handle, nConstraints, constraints.length);
        }
    }

    public void removeConstraints(Collection<? extends Constraint> constraints) {
        removeConstraints(constraints.toArray(new Constraint[0]));
    }

    // TODO getConstraints

    public void optimizeBroadPhase() {
        JPC_PhysicsSystem_OptimizeBroadPhase(handle);
    }

    public void addStepListener(PhysicsStepListener listener) {
        JPC_PhysicsSystem_AddStepListener(handle, listener.address());
    }

    public void removeStepListener(PhysicsStepListener listener) {
        JPC_PhysicsSystem_RemoveStepListener(handle, listener.address());
    }

    public void setGravity(FVec3 gravity) {
        JPC_PhysicsSystem_SetGravity(handle, gravity.address());
    }

    public void getGravity(FVec3 out) {
        JPC_PhysicsSystem_GetGravity(handle, out.address());
    }

    public BodyLockInterface getBodyLockInterfaceNoLock() {
        return BodyLockInterface.at(JPC_PhysicsSystem_GetBodyLockInterfaceNoLock(handle));
    }

    public BodyLockInterface getBodyLockInterface() {
        return BodyLockInterface.at(JPC_PhysicsSystem_GetBodyLockInterface(handle));
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

    public int[] getBodies() {
        try (var arena = MemorySession.openConfined()) {
            var outNumBodyIds = arena.allocate(C_INT, 0);
            var outBodyIds = arena.allocate(C_POINTER, MemoryAddress.NULL);
            var vector = JPC_PhysicsSystem_GetBodyIDs(handle, outNumBodyIds.address(), outBodyIds.address());
            var numBodyIds = outNumBodyIds.get(C_INT, 0);
            var bodyIds = outBodyIds.get(C_POINTER, 0);
            var result = new int[numBodyIds];
            for (int i = 0; i < numBodyIds; i++) {
                result[i] = bodyIds.getAtIndex(C_INT, i);
            }
            JPC_BodyIDVector_Destroy(vector);
            return result;
        }
    }

    public int[] getActiveBodies() {
        try (var arena = MemorySession.openConfined()) {
            var outNumBodyIds = arena.allocate(C_INT, 0);
            var outBodyIds = arena.allocate(C_POINTER, MemoryAddress.NULL);
            var vector = JPC_PhysicsSystem_GetActiveBodyIDs(handle, outNumBodyIds.address(), outBodyIds.address());
            var numBodyIds = outNumBodyIds.get(C_INT, 0);
            var bodyIds = outBodyIds.get(C_POINTER, 0);
            var result = new int[numBodyIds];
            for (int i = 0; i < numBodyIds; i++) {
                result[i] = bodyIds.getAtIndex(C_INT, i);
            }
            JPC_BodyIDVector_Destroy(vector);
            return result;
        }
    }

    public boolean wereBodiesInContact(int bodyId1, int bodyId2) {
        return JPC_PhysicsSystem_WereBodiesInContact(handle, bodyId1, bodyId2);
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
