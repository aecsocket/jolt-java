package jolt.physics;

import jolt.core.JobSystem;
import jolt.core.TempAllocator;
import jolt.math.JtVec3f;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.MutableBodyInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;
import jolt.physics.constraint.Constraint;

import javax.annotation.Nullable;
import java.util.Collection;

public sealed interface MutablePhysicsSystem extends PhysicsSystem permits PhysicsSystemImpl {
    static MutablePhysicsSystem ref(long address) { return address == 0 ? null : new PhysicsSystemImpl(address); }

    void init(
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            BroadPhaseLayerInterface broadPhaseLayerInterface,
            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
            ObjectLayerPairFilter objectLayerPairFilter
    );

    void setBodyActivationListener(@Nullable BodyActivationListener listener);

    void setContactListener(@Nullable ContactListener listener);

    // TODO setCombine*

    void setPhysicsSettings(PhysicsSettings settings);

    @Override
    MutableBodyInterface getBodyInterface();

    @Override
    MutableBodyInterface getBodyInterfaceNoLock();

    void addConstraint(Constraint constraint);

    void removeConstraint(Constraint constraint);

    void addConstraints(Constraint... constraints);
    default void addConstraints(Collection<Constraint> constraints) {
        addConstraints(constraints.toArray(new Constraint[0]));
    }

    void removeConstraints(Constraint... constraints);
    default void removeConstraints(Collection<Constraint> constraints) {
        removeConstraints(constraints.toArray(new Constraint[0]));
    }

    // TODO
    // List<ConstraintRef> getConstraints();

    void optimizeBroadPhase();

    void addStepListener(PhysicsStepListener listener);

    void removeStepListener(PhysicsStepListener listener);

    void update(float deltaTime, int collisionSteps, int integrationSubSteps, TempAllocator tempAllocator, JobSystem jobSystem);

    // todo save/restore state

    void setGravity(JtVec3f gravity);
}
