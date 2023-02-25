package jolt.physics;

import jolt.JoltNative;
import jolt.math.JtVec3f;
import jolt.physics.body.*;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.NarrowPhaseQuery;
import jolt.physics.collision.broadphase.BroadPhaseQuery;

import javax.annotation.Nullable;
import java.util.List;

public sealed interface PhysicsSystem extends JoltNative permits MutablePhysicsSystem {
    static PhysicsSystem ref(long address) { return address == 0 ? null : new PhysicsSystemImpl(address); }
    static MutablePhysicsSystem create() { return new PhysicsSystemImpl(); }

    @Nullable BodyActivationListener getBodyActivationListener();

    @Nullable ContactListener getContactListener();

    PhysicsSettings getPhysicsSettings();

    BodyInterface getBodyInterface();

    BodyInterface getBodyInterfaceNoLock();

    BroadPhaseQuery getBroadPhaseQuery();

    NarrowPhaseQuery getNarrowPhaseQuery();

    NarrowPhaseQuery getNarrowPhaseQueryNoLock();

    // TODO getConstraints

    JtVec3f getGravity(JtVec3f out);
    default JtVec3f getGravity() { return getGravity(new JtVec3f()); }

    BodyLockInterface getBodyLockInterfaceNoLock();

    BodyLockInterface getBodyLockInterface();

    // todo default filters

    int getNumBodies();

    int getNumActiveBodies();

    int getMaxBodies();

    List<Integer> getBodies();

    List<Integer> getActiveBodies();
}
