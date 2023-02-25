package jolt.physics;

import io.github.aecsocket.jniglue.*;
import jolt.JoltNative;
import jolt.UnimplementedMethodException;
import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.JoltNativeImpl;
import jolt.math.JtVec3f;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyLockInterface;
import jolt.physics.body.MutableBodyInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.NarrowPhaseQuery;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.BroadPhaseQuery;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.constraint.Constraint;

import javax.annotation.Nullable;
import java.util.List;

@JniInclude("<Jolt/Physics/PhysicsSystem.h>")
@JniTypeMapping("PhysicsSystem")
final class PhysicsSystemImpl extends JoltNativeImpl implements MutablePhysicsSystem {
    PhysicsSystemImpl(long address) { super(address); }

    @Override protected void deleteInternal() { _delete(address); }
    @JniBindDelete private static native void _delete(long _a);

    PhysicsSystemImpl() { address = _ctor(); }
    @JniBind("return (jlong) new PhysicsSystem();")
    private static native long _ctor();

    @Override
    public void init(int maxBodies, int numBodyMutexes, int maxBodyPairs, int maxContactConstraints, BroadPhaseLayerInterface broadPhaseLayerInterface, ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter, ObjectLayerPairFilter objectLayerPairFilter) {
        _init(
                address, maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
                broadPhaseLayerInterface.getAddress(), objectVsBroadPhaseLayerFilter.getAddress(), objectLayerPairFilter.getAddress()
        );
    }
    @JniBindSelf("""
            self->Init(
                maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
                *((BroadPhaseLayerInterface*) broadPhaseLayerInterface),
                *((ObjectVsBroadPhaseLayerFilter*) objectVsBroadPhaseLayerFilter),
                *((ObjectLayerPairFilter*) objectLayerPairFilter)
            );""")
    private static native void _init(
            long _a, int maxBodies, int numBodyMutexes, int maxBodyPairs, int maxContactConstraints,
            long broadPhaseLayerInterface,
            long objectVsBroadPhaseLayerFilter,
            long objectLayerPairFilter
    );

    @Override
    public void setBodyActivationListener(@Nullable BodyActivationListener listener) { _setBodyActivationListener(address, ptr(listener)); }
    @JniBindSelf("self->SetBodyActivationListener((BodyActivationListener*) value);")
    private static native void _setBodyActivationListener(long _a, long value);

    @Override
    public @Nullable BodyActivationListener getBodyActivationListener() { return BodyActivationListener.ref(_getBodyActivationListener(address)); }
    @JniBindSelf("return (jlong) self->GetBodyActivationListener();")
    private static native long _getBodyActivationListener(long _a);

    @Override
    public void setContactListener(@Nullable ContactListener listener) { _setContactListener(address, ptr(listener)); }
    @JniBindSelf("self->SetContactListener((ContactListener*) value);")
    private static native void _setContactListener(long _a, long value);

    @Override
    public @Nullable ContactListener getContactListener() { return ContactListener.ref(_getContactListener(address)); }
    @JniBindSelf("return (jlong) self->GetContactListener();")
    private static native long _getContactListener(long _a);

    @Override
    public void setPhysicsSettings(PhysicsSettings settings) {
        _setPhysicsSettings(address, settings.maxInFlightBodyPairs, settings.stepListenersBatchSize, settings.stepListenerBatchesPerJob, settings.baumgarte, settings.speculativeContactDistance, settings.penetrationSlop, settings.linearCastThreshold, settings.linearCastMaxPenetration, settings.manifoldToleranceSq, settings.maxPenetrationDistance, settings.bodyPairCacheMaxDeltaPositionSq, settings.bodyPairCacheCosMaxDeltaRotationDiv2, settings.contactNormalCosMaxDeltaRotation, settings.contactPointPreserveLambdaMaxDistSq, settings.numVelocitySteps, settings.numPositionSteps, settings.minVelocityForRestitution, settings.timeBeforeSleep, settings.pointVelocitySleepThreshold, settings.constraintWarmStart, settings.useBodyPairContactCache, settings.useManifoldReduction, settings.allowSleeping, settings.checkActiveEdges);
    }
    @JniBindSelf("""
            PhysicsSettings settings;
            settings.mMaxInFlightBodyPairs = maxInFlightBodyPairs;
            settings.mStepListenersBatchSize = stepListenersBatchSize;
            settings.mStepListenerBatchesPerJob = stepListenerBatchesPerJob;
            settings.mBaumgarte = baumgarte;
            settings.mSpeculativeContactDistance = speculativeContactDistance;
            settings.mPenetrationSlop = penetrationSlop;
            settings.mLinearCastThreshold = linearCastThreshold;
            settings.mLinearCastMaxPenetration = linearCastMaxPenetration;
            settings.mManifoldToleranceSq = manifoldToleranceSq;
            settings.mMaxPenetrationDistance = maxPenetrationDistance;
            settings.mBodyPairCacheMaxDeltaPositionSq = bodyPairCacheMaxDeltaPositionSq;
            settings.mBodyPairCacheCosMaxDeltaRotationDiv2 = bodyPairCacheCosMaxDeltaRotationDiv2;
            settings.mContactNormalCosMaxDeltaRotation = contactNormalCosMaxDeltaRotation;
            settings.mContactPointPreserveLambdaMaxDistSq = contactPointPreserveLambdaMaxDistSq;
            settings.mNumVelocitySteps = numVelocitySteps;
            settings.mNumPositionSteps = numPositionSteps;
            settings.mMinVelocityForRestitution = minVelocityForRestitution;
            settings.mTimeBeforeSleep = timeBeforeSleep;
            settings.mPointVelocitySleepThreshold = pointVelocitySleepThreshold;
            settings.mConstraintWarmStart = constraintWarmStart;
            settings.mUseBodyPairContactCache = useBodyPairContactCache;
            settings.mUseManifoldReduction = useManifoldReduction;
            settings.mAllowSleeping = allowSleeping;
            settings.mCheckActiveEdges = checkActiveEdges;
            self->SetPhysicsSettings(settings);""")
    private static native void _setPhysicsSettings(long _a, int maxInFlightBodyPairs, int stepListenersBatchSize, int stepListenerBatchesPerJob, float baumgarte, float speculativeContactDistance, float penetrationSlop, float linearCastThreshold, float linearCastMaxPenetration, float manifoldToleranceSq, float maxPenetrationDistance, float bodyPairCacheMaxDeltaPositionSq, float bodyPairCacheCosMaxDeltaRotationDiv2, float contactNormalCosMaxDeltaRotation, float contactPointPreserveLambdaMaxDistSq, int numVelocitySteps, int numPositionSteps, float minVelocityForRestitution, float timeBeforeSleep, float pointVelocitySleepThreshold, boolean constraintWarmStart, boolean useBodyPairContactCache, boolean useManifoldReduction, boolean allowSleeping, boolean checkActiveEdges);

    @Override
    public PhysicsSettings getPhysicsSettings(PhysicsSettings out) {
        _getPhysicsSettings(address, out);
        return out;
    }
    @JniBindSelf("ToJava(env, self->GetPhysicsSettings(), out);")
    private static native void _getPhysicsSettings(long _a, PhysicsSettings out);

    @Override
    public MutableBodyInterface getBodyInterface() { return MutableBodyInterface.ref(_getBodyInterface(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyInterface();")
    private static native long _getBodyInterface(long _a);

    @Override
    public MutableBodyInterface getBodyInterfaceNoLock() { return MutableBodyInterface.ref(_getBodyInterfaceNoLock(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyInterfaceNoLock();")
    private static native long _getBodyInterfaceNoLock(long _a);

    @Override
    public BroadPhaseQuery getBroadPhaseQuery() { return BroadPhaseQuery.ref(_getBroadPhaseQuery(address)); }
    @JniBindSelf("return (jlong) &self->GetBroadPhaseQuery();")
    private static native long _getBroadPhaseQuery(long _a);

    @Override
    public NarrowPhaseQuery getNarrowPhaseQuery() { return NarrowPhaseQuery.ref(_getNarrowPhaseQuery(address)); }
    @JniBindSelf("return (jlong) &self->GetNarrowPhaseQuery();")
    private static native long _getNarrowPhaseQuery(long _a);

    @Override
    public NarrowPhaseQuery getNarrowPhaseQueryNoLock() { return NarrowPhaseQuery.ref(_getNarrowPhaseQueryNoLock(address)); }
    @JniBindSelf("return (jlong) &self->GetNarrowPhaseQueryNoLock();")
    private static native long _getNarrowPhaseQueryNoLock(long _a);

    @Override
    public void addConstraint(Constraint constraint) { _addConstraint(address, constraint.getAddress()); }
    @JniBindSelf("self->AddConstraint((Constraint*) constraint);")
    private static native void _addConstraint(long _a, long constraint);

    @Override
    public void removeConstraint(Constraint constraint) { _removeConstraint(address, constraint.getAddress()); }
    @JniBindSelf("self->RemoveConstraint((Constraint*) constraint);")
    private static native void _removeConstraint(long _a, long constraint);

    @Override
    public void addConstraints(Constraint... constraints) { _addConstraints(address, JoltNative.addressesOf(constraints)); }
    @JniBindSelf("""
            jlong* cConstraints = env->GetLongArrayElements(constraints, JNI_FALSE);
            self->AddConstraints((Constraint**) cConstraints, env->GetArrayLength(constraints));
            env->ReleaseLongArrayElements(constraints, cConstraints, 0)""")
    private static native void _addConstraints(long _a, long[] constraints);

    @Override
    public void removeConstraints(Constraint... constraints) {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public void optimizeBroadPhase() { _optimizeBroadPhase(address); }
    @JniBindSelf("self->OptimizeBroadPhase();")
    private static native void _optimizeBroadPhase(long _a);

    @Override
    public void addStepListener(PhysicsStepListener listener) { _addStepListener(address, listener.getAddress()); }
    @JniBindSelf("self->AddStepListener((PhysicsStepListener*) listener);")
    private static native void _addStepListener(long _a, long listener);

    @Override
    public void removeStepListener(PhysicsStepListener listener) { _removeStepListener(address, listener.getAddress()); }
    @JniBindSelf("self->RemoveStepListener((PhysicsStepListener*) listener);")
    private static native void _removeStepListener(long _a, long listener);

    @Override
    public void update(float deltaTime, int collisionSteps, int integrationSubSteps, TempAllocator tempAllocator, JobSystem jobSystem) { _update(address, deltaTime, collisionSteps, integrationSubSteps, tempAllocator.getAddress(), jobSystem.getAddress()); }
    @JniBindSelf("self->Update(deltaTime, collisionSteps, integrationSubSteps, (TempAllocator*) tempAllocator, (JobSystem*) jobSystem);")
    private static native void _update(long _a, float deltaTime, int collisionSteps, int integrationSubSteps, long tempAllocator, long jobSystem);

    @Override
    public void setGravity(JtVec3f gravity) { _setGravity(address, gravity.x, gravity.y, gravity.z); }
    @JniBindSelf("self->SetGravity(Vec3(valueX, valueY, valueZ));")
    private static native void _setGravity(long _a, float valueX, float valueY, float valueZ);

    @Override
    public JtVec3f getGravity(JtVec3f out) {
        _getGravity(address, out);
        return out;
    }
    @JniBindSelf("ToJavaSp(env, self->GetGravity(), out);")
    private static native void _getGravity(long _a, JtVec3f out);

    @Override
    public BodyLockInterface getBodyLockInterfaceNoLock() { return BodyLockInterface.ref(_getBodyLockInterfaceNoLock(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyLockInterfaceNoLock();")
    private static native long _getBodyLockInterfaceNoLock(long _a);

    @Override
    public BodyLockInterface getBodyLockInterface() { return BodyLockInterface.ref(_getBodyLockInterface(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyLockInterface();")
    private static native long _getBodyLockInterface(long _a);

    @Override
    public int getNumBodies() { return _getNumBodies(address); }
    @JniBindSelf("return self->GetNumBodies();")
    private static native int _getNumBodies(long _a);

    @Override
    public int getNumActiveBodies() { return _getNumActiveBodies(address); }
    @JniBindSelf("return self->GetNumActiveBodies();")
    private static native int _getNumActiveBodies(long _a);

    @Override
    public int getMaxBodies() { return _getMaxBodies(address); }
    @JniBindSelf("return self->GetMaxBodies();")
    private static native int _getMaxBodies(long _a);

    @Override
    public List<Integer> getBodies() {
        throw new UnimplementedMethodException(); // TODO
    }

    @Override
    public List<Integer> getActiveBodies() {
        throw new UnimplementedMethodException(); // TODO
    }

    //
//    public void init(
//            int maxBodies, int numBodyMutexes, int maxBodyPairs, int maxContactConstraints,
//            BroadPhaseLayerInterface broadPhaseLayerInterface,
//            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
//            ObjectLayerPairFilter objectLayerPairFilter
//    ) {
//        _init(
//                address,
//                maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
//                broadPhaseLayerInterface.getAddress(),
//                objectVsBroadPhaseLayerFilter.getAddress(),
//                objectLayerPairFilter.getAddress()
//        );
//    }
//    @JniBindSelf("""
//            self->Init(
//                maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
//                *((BroadPhaseLayerInterface*) broadPhaseLayerInterface),
//                *((ObjectVsBroadPhaseLayerFilter*) objectVsBroadPhaseLayerFilter),
//                *((ObjectLayerPairFilter*) objectLayerPairFilter)
//            );""")
//    private static native void _init(
//            long _a, int maxBodies, int numBodyMutexes, int maxBodyPairs, int maxContactConstraints,
//            long broadPhaseLayerInterface,
//            long objectVsBroadPhaseLayerFilter,
//            long objectLayerPairFilter
//    );
//
//
//    // listeners
//
//    /**
//     * Listener that is notified whenever a body is activated/deactivated.
//     */
//    public @Nullable BodyActivationListener getBodyActivationListener() { return BodyActivationListener.ref(_getBodyActivationListener(address)); }
//    @JniBindSelf("return (jlong) self->GetBodyActivationListener();")
//    private static native long _getBodyActivationListener(long _a);
//
//    /**
//     * Listener that is notified whenever a body is activated/deactivated.
//     */
//    public void setBodyActivationListener(@Nullable BodyActivationListener value) { _setBodyActivationListener(address, ptr(value)); }
//    @JniBindSelf("self->SetBodyActivationListener((BodyActivationListener*) value);")
//    private static native void _setBodyActivationListener(long _a, long value);
//
//    /**
//     * Listener that is notified whenever a contact point between two bodies is added/updated/removed.
//     */
//    public @Nullable ContactListener getContactListener() { return ContactListener.ref(_getContactListener(address)); }
//    @JniBindSelf("return (jlong) self->GetContactListener();")
//    private static native long _getContactListener(long _a);
//
//    /**
//     * Listener that is notified whenever a contact point between two bodies is added/updated/removed.
//     */
//    public void setContactListener(@Nullable ContactListener value) { _setContactListener(address, ptr(value)); }
//    @JniBindSelf("self->SetContactListener((ContactListener*) value);")
//    private static native void _setContactListener(long _a, long value);
//
//    // properties
//
//    /**
//     * Control the main constants of the physics simulation.
//     */
//    public PhysicsSettings getPhysicsSettings() { return PhysicsSettings.ref(_getPhysicsSettings(address)); }
//    @JniBindSelf("return (jlong) &self->GetPhysicsSettings();")
//    private static native long _getPhysicsSettings(long _a);
//
//    /**
//     * Control the main constants of the physics simulation.
//     */
//    public void setPhysicsSettings(PhysicsSettings value) { _setPhysicsSettings(address, value.getAddress()); }
//    @JniBindSelf("self->SetPhysicsSettings(*((PhysicsSettings*) value));")
//    private static native void _setPhysicsSettings(long _a, long value);
//
//    /**
//     * Access to the body interface. This interface allows to create / remove bodies and to change their properties.
//     */
//    public BodyInterface getBodyInterface() { return BodyInterface.ref(_getBodyInterface(address)); }
//    @JniBindSelf("return (jlong) &self->GetBodyInterface();")
//    private static native long _getBodyInterface(long _a);
//
//    /**
//     * Access to the body interface. This interface allows to create / remove bodies and to change their properties.
//     * <p>
//     * Version that does not lock the bodies, use with great care!
//     */
//    public BodyInterface getBodyInterfaceNoLock() { return BodyInterface.ref(_getBodyInterfaceNoLock(address)); }
//    @JniBindSelf("return (jlong) &self->GetBodyInterfaceNoLock();")
//    private static native long _getBodyInterfaceNoLock(long _a);
//
//    /**
//     * Access to the broadphase interface that allows coarse collision queries.
//     */
//    public BroadPhaseQuery getBroadPhaseQuery() { return BroadPhaseQuery.ref(_getBroadPhaseQuery(address)); }
//    @JniBindSelf("return (jlong) &self->GetBroadPhaseQuery();")
//    private static native long _getBroadPhaseQuery(long _a);
//
//    /**
//     * Interface that allows fine collision queries against first the broad phase and then the narrow phase.
//     */
//    public NarrowPhaseQuery getNarrowPhaseQuery() { return NarrowPhaseQuery.ref(_getNarrowPhaseQuery(address)); }
//    @JniBindSelf("return (jlong) &self->GetNarrowPhaseQuery();")
//    private static native long _getNarrowPhaseQuery(long _a);
//
//    /**
//     * Interface that allows fine collision queries against first the broad phase and then the narrow phase.
//     * <p>
//     * Version that does not lock the bodies, use with great care!
//     */
//    public NarrowPhaseQuery getNarrowPhaseQueryNoLock() { return NarrowPhaseQuery.ref(_getNarrowPhaseQueryNoLock(address)); }
//    @JniBindSelf("return (jlong) &self->GetNarrowPhaseQueryNoLock();")
//    private static native long _getNarrowPhaseQueryNoLock(long _a);
//
//    /**
//     * Add constraint to the world.
//     */
//    public void addConstraint(Constraint constraint) { _addConstraint(address, constraint.getAddress()); }
//    @JniBindSelf("self->AddConstraint((Constraint*) constraint);")
//    private static native void _addConstraint(long _a, long constraint);
//
//    public void addConstraints(Constraint... constraints) { _addConstraints(address, addressesOf(constraints)); }
//    public void addConstraints(Collection<Constraint> constraints) { _addConstraints(address, addressesOf(constraints)); }
//    @JniBindSelf("self->AddConstraints((Constraint**) env->GetLongArrayElements(constraints, JNI_FALSE), env->GetArrayLength(constraints));")
//    private static native void _addConstraints(long _a, long[] constraints);
//
//    /**
//     * Remove constraint from the world.
//     */
//    public void removeConstraint(Constraint constraint) { _removeConstraint(address, constraint.getAddress()); }
//    @JniBindSelf("self->RemoveConstraint((Constraint*) constraint);")
//    private static native void _removeConstraint(long _a, long constraint);
//
//    public void removeConstraints(Constraint... constraints) { _removeConstraints(address, addressesOf(constraints)); }
//    public void removeConstraints(Collection<Constraint> constraints) { _removeConstraints(address, addressesOf(constraints)); }
//    @JniBindSelf("self->RemoveConstraints((Constraint**) env->GetLongArrayElements(constraints, JNI_FALSE), env->GetArrayLength(constraints));")
//    private static native void _removeConstraints(long _a, long[] constraints);
//
//    /**
//     * Optimize the broadphase, needed only if you've added many bodies prior to calling Update() for the first time.
//     */
//    public void optimizeBroadPhase() { _optimizeBroadPhase(address); }
//    @JniBindSelf("self->OptimizeBroadPhase();")
//    private static native void _optimizeBroadPhase(long _a);
//
//    public void addStepListener(PhysicsStepListener value) { _addStepListener(address, value.getAddress()); }
//    @JniBindSelf("self->AddStepListener((PhysicsStepListener*) value);")
//    private static native void _addStepListener(long _a, long value);
//
//    public void removeStepListener(PhysicsStepListener value) { _removeStepListener(address, value.getAddress()); }
//    @JniBindSelf("self->RemoveStepListener((PhysicsStepListener*) value);")
//    private static native void _removeStepListener(long _a, long value);
//
//    /**
//     * Simulate the system.
//     * The world steps for a total of inDeltaTime seconds. This is divided in inCollisionSteps iterations. Each iteration
//     * consists of collision detection followed by inIntegrationSubSteps integration steps.
//     */
//    /*
//    crash at JPH::TempAllocatorImpl::Allocate(unsigned int):
//      TempAllocator is not big enough to fit all contactConstraints
//     */
//    public void update(
//            float deltaTime, int collisionSteps, int integrationSubSteps,
//            TempAllocator tempAllocator, JobSystem jobSystem
//    ) {
//        _update(
//                address, deltaTime, collisionSteps, integrationSubSteps,
//                tempAllocator.getAddress(), jobSystem.getAddress()
//        );
//    }
//    @JniBindSelf("""
//            self->Update(
//                deltaTime, collisionSteps, integrationSubSteps,
//                (TempAllocator*) tempAllocator,
//                (JobSystem*) jobSystem
//            );""")
//    private static native void _update(
//            long _a, float deltaTime, int collisionSteps, int integrationSubSteps,
//            long tempAllocator, long jobSystem
//    );
//
//    // TODO Save/RestoreState
//
//    public JtVec3f getGravity(JtVec3f out) {
//        _getGravity(address, out);
//        return out;
//    }
//    public JtVec3f getGravity() { return getGravity(new JtVec3f()); }
//    @JniBindSelf("ToJavaSp(env, self->GetGravity(), out);")
//    private static native void _getGravity(long _a, JtVec3f out);
//
//    public void setGravity(JtVec3f value) { _setGravity(address, value.x, value.y, value.z); }
//    @JniBindSelf("self->SetGravity(Vec3(valueX, valueY, valueZ));")
//    private static native void _setGravity(long _a, float valueX, float valueY, float valueZ);
//
//    public BodyLockInterface getBodyLockInterface() { return BodyLockInterface.ref(_getBodyLockInterface(address)); }
//    @JniBindSelf("return (jlong) &self->GetBodyLockInterface();")
//    private static native long _getBodyLockInterface(long _a);
//
//    public BodyLockInterface getBodyLockInterfaceNoLock() { return BodyLockInterface.ref(_getBodyLockInterfaceNoLock(address)); }
//    @JniBindSelf("return (jlong) &self->GetBodyLockInterfaceNoLock();")
//    private static native long _getBodyLockInterfaceNoLock(long _a);
//
//    /**
//     * Gets the current amount of bodies that are in the body manager.
//     */
//    public int getNumBodies() { return _getNumBodies(address); }
//    @JniBindSelf("return self->GetNumBodies();")
//    private static native int _getNumBodies(long _a);
//
//    /**
//     * Gets the current amount of active bodies that are in the body manager.
//     */
//    public int getNumActiveBodies() { return _getNumActiveBodies(address); }
//    @JniBindSelf("return self->GetNumActiveBodies();")
//    private static native int _getNumActiveBodies(long _a);
//
//    /**
//     * Get the maximum amount of bodies that this physics system supports.
//     */
//    public int getMaxBodies() { return _getMaxBodies(address); }
//    @JniBindSelf("return self->GetMaxBodies();")
//    private static native int _getMaxBodies(long _a);
}
