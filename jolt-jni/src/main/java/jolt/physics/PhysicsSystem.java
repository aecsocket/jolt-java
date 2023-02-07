package jolt.physics;

import io.github.aecsocket.jniglue.*;
import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.JoltNative;
import jolt.math.JtVec3f;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.NarrowPhaseQuery;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.BroadPhaseQuery;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.constraint.Constraint;

import javax.annotation.Nullable;

/**
 * The main class for the physics system. It contains all rigid bodies and simulates them.
 * <p>
 * The main simulation is performed by the {@link #update(float, int, int, TempAllocator, JobSystem)} call on multiple threads (if the JobSystem is configured to use them).
 * Please refer to the general architecture overview in the Docs folder for more information.
 */
@JniInclude("<Jolt/Physics/PhysicsSystem.h>")
@JniType("PhysicsSystem")
public final class PhysicsSystem extends JoltNative {
    private PhysicsSystem(long address) { super(address); }
    public static PhysicsSystem ref(long address) { return address == 0 ? null : new PhysicsSystem(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public PhysicsSystem() { address = _ctor(); }
    @JniBind("return (jlong) new PhysicsSystem();")
    private static native long _ctor();

    /**
     * Initialize the system.
     * @param maxBodies Maximum number of bodies to support.
     * @param numBodyMutexes Number of body mutexes to use. Should be a power of 2 in the range [1, 64], use 0 to auto detect.
     * @param maxBodyPairs Maximum amount of body pairs to process (anything else will fall through the world), this number should generally be much higher than the max amount of contact points as there will be lots of bodies close that are not actually touching.
     * @param maxContactConstraints Maximum amount of contact constraints to process (anything else will fall through the world).
     * @param broadPhaseLayerInterface Information on the mapping of object layers to broad phase layers. Since this is a virtual interface, the instance needs to stay alive during the lifetime of the PhysicsSystem.
     * @param objectVsBroadPhaseLayerFilter Filter callback function that is used to determine if an object layer collides with a broad phase layer. Since this is a virtual interface, the instance needs to stay alive during the lifetime of the PhysicsSystem.
     * @param objectLayerPairFilter Filter callback function that is used to determine if two object layers collide. Since this is a virtual interface, the instance needs to stay alive during the lifetime of the PhysicsSystem.
     */
    public void init(
            int maxBodies, int numBodyMutexes, int maxBodyPairs, int maxContactConstraints,
            BroadPhaseLayerInterface broadPhaseLayerInterface,
            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
            ObjectLayerPairFilter objectLayerPairFilter
    ) {
        _init(
                address,
                maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
                broadPhaseLayerInterface.getAddress(),
                objectVsBroadPhaseLayerFilter.getAddress(),
                objectLayerPairFilter.getAddress()
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

    // getters

    /**
     * Access to the body interface. This interface allows to create / remove bodies and to change their properties.
     */
    public BodyInterface getBodyInterface() { return BodyInterface.ref(_getBodyInterface(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyInterface();")
    private static native long _getBodyInterface(long _a);

    /**
     * Access to the body interface. This interface allows to create / remove bodies and to change their properties.
     * <p>
     * Version that does not lock the bodies, use with great care!
     */
    public BodyInterface getBodyInterfaceNoLock() { return BodyInterface.ref(_getBodyInterfaceNoLock(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyInterfaceNoLock();")
    private static native long _getBodyInterfaceNoLock(long _a);

    /**
     * Access to the broadphase interface that allows coarse collision queries.
     */
    public BroadPhaseQuery getBroadPhaseQuery() { return BroadPhaseQuery.ref(_getBroadPhaseQuery(address)); }
    @JniBindSelf("return (jlong) &self->GetBroadPhaseQuery();")
    private static native long _getBroadPhaseQuery(long _a);

    /**
     * Interface that allows fine collision queries against first the broad phase and then the narrow phase.
     */
    public NarrowPhaseQuery getNarrowPhaseQuery() { return NarrowPhaseQuery.ref(_getNarrowPhaseQuery(address)); }
    @JniBindSelf("return (jlong) &self->GetNarrowPhaseQuery();")
    private static native long _getNarrowPhaseQuery(long _a);

    /**
     * Interface that allows fine collision queries against first the broad phase and then the narrow phase.
     * <p>
     * Version that does not lock the bodies, use with great care!
     */
    public NarrowPhaseQuery getNarrowPhaseQueryNoLock() { return NarrowPhaseQuery.ref(_getNarrowPhaseQueryNoLock(address)); }
    @JniBindSelf("return (jlong) &self->GetNarrowPhaseQueryNoLock();")
    private static native long _getNarrowPhaseQueryNoLock(long _a);

    /**
     * Gets the current amount of bodies that are in the body manager.
     */
    public int getNumBodies() { return _getNumBodies(address); }
    @JniBindSelf("return self->GetNumBodies();")
    private static native int _getNumBodies(long _a);

    /**
     * Gets the current amount of active bodies that are in the body manager.
     */
    public int getNumActiveBodies() { return _getNumActiveBodies(address); }
    @JniBindSelf("return self->GetNumActiveBodies();")
    private static native int _getNumActiveBodies(long _a);

    /**
     * Get the maximum amount of bodies that this physics system supports.
     */
    public int getMaxBodies() { return _getMaxBodies(address); }
    @JniBindSelf("return self->GetMaxBodies();")
    private static native int _getMaxBodies(long _a);

    // properties

    /**
     * Control the main constants of the physics simulation.
     */
    public PhysicsSettings getPhysicsSettings() { return PhysicsSettings.ref(_getPhysicsSettings(address)); }
    @JniBindSelf("return (jlong) &self->GetPhysicsSettings();")
    private static native long _getPhysicsSettings(long _a);

    /**
     * Control the main constants of the physics simulation.
     */
    public void setPhysicsSettings(PhysicsSettings value) { _setPhysicsSettings(address, value.getAddress()); }
    @JniBindSelf("self->SetPhysicsSettings(*((PhysicsSettings*) value));")
    private static native void _setPhysicsSettings(long _a, long value);

    /**
     * Add constraint to the world.
     */
    public void addConstraint(Constraint constraint) { _addConstraint(address, constraint.getAddress()); }
    @JniBindSelf("self->AddConstraint((Constraint*) constraint);")
    private static native void _addConstraint(long _a, long constraint);

    /**
     * Remove constraint from the world.
     */
    public void removeConstraint(Constraint constraint) { _removeConstraint(address, constraint.getAddress()); }
    @JniBindSelf("self->RemoveConstraint((Constraint*) constraint);")
    private static native void _removeConstraint(long _a, long constraint);

    // TODO GetConstraints()

    public JtVec3f getGravity(JtVec3f out) {
        _getGravity(address, out);
        return out;
    }
    public JtVec3f getGravity() { return getGravity(new JtVec3f()); }
    @JniBindSelf("ToJavaSp(env, self->GetGravity(), out);")
    private static native void _getGravity(long _a, JtVec3f out);

    public void setGravity(JtVec3f value) { _setGravity(address, value.x, value.y, value.z); }
    @JniBindSelf("self->SetGravity(Vec3(valueX, valueY, valueZ));")
    private static native void _setGravity(long _a, float valueX, float valueY, float valueZ);

    // listeners

    /**
     * Listener that is notified whenever a body is activated/deactivated.
     */
    public @Nullable BodyActivationListener getBodyActivationListener() { return BodyActivationListener.ref(_getBodyActivationListener(address)); }
    @JniBindSelf("return (jlong) self->GetBodyActivationListener();")
    private static native long _getBodyActivationListener(long _a);

    /**
     * Listener that is notified whenever a body is activated/deactivated.
     */
    public void setBodyActivationListener(@Nullable BodyActivationListener value) { _setBodyActivationListener(address, ptr(value)); }
    @JniBindSelf("self->SetBodyActivationListener((BodyActivationListener*) value);")
    private static native void _setBodyActivationListener(long _a, long value);

    /**
     * Listener that is notified whenever a contact point between two bodies is added/updated/removed.
     */
    public @Nullable ContactListener getContactListener() { return ContactListener.ref(_getContactListener(address)); }
    @JniBindSelf("return (jlong) self->GetContactListener();")
    private static native long _getContactListener(long _a);

    /**
     * Listener that is notified whenever a contact point between two bodies is added/updated/removed.
     */
    public void setContactListener(@Nullable ContactListener value) { _setContactListener(address, ptr(value)); }
    @JniBindSelf("self->SetContactListener((ContactListener*) value);")
    private static native void _setContactListener(long _a, long value);

    // functions

    /**
     * Optimize the broadphase, needed only if you've added many bodies prior to calling Update() for the first time.
     */
    public void optimizeBroadPhase() { _optimizeBroadPhase(address); }
    @JniBindSelf("self->OptimizeBroadPhase();")
    private static native void _optimizeBroadPhase(long _a);

    /**
     * Simulate the system.
     * The world steps for a total of inDeltaTime seconds. This is divided in inCollisionSteps iterations. Each iteration
     * consists of collision detection followed by inIntegrationSubSteps integration steps.
     */
    /*
    crash at JPH::TempAllocatorImpl::Allocate(unsigned int):
      TempAllocator is not big enough to fit all contactConstraints
     */
    public void update(
            float deltaTime, int collisionSteps, int integrationSubSteps,
            TempAllocator tempAllocator, JobSystem jobSystem
    ) {
        _update(
                address, deltaTime, collisionSteps, integrationSubSteps,
                tempAllocator.getAddress(), jobSystem.getAddress()
        );
    }
    @JniBindSelf("""
            self->Update(
                deltaTime, collisionSteps, integrationSubSteps,
                (TempAllocator*) tempAllocator,
                (JobSystem*) jobSystem
            );""")
    private static native void _update(
            long _a, float deltaTime, int collisionSteps, int integrationSubSteps,
            long tempAllocator, long jobSystem
    );
}
