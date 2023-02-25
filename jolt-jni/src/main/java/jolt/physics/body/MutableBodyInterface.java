package jolt.physics.body;

import jolt.math.*;
import jolt.physics.Activation;
import jolt.physics.constraint.TwoBodyConstraint;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public sealed interface MutableBodyInterface extends BodyInterface permits BodyInterfaceImpl {
    static MutableBodyInterface ref(long address) { return address == 0 ? null : new BodyInterfaceImpl(address); }

    MutableBody createBody(BodyCreationSettings settings);

    MutableBody createBodyWithId(int bodyId, BodyCreationSettings settings);

    boolean assignBodyId(MutableBody body);

    boolean assignBodyId(MutableBody body, int bodyId);

    @Nullable MutableBody unassignBodyId(int bodyId);

    MutableBody[] unassignBodyIds(int... bodyIds);
    default List<MutableBody> unassignBodyIds(Collection<Integer> bodyIds) {
        return Arrays.asList(unassignBodyIds(BodyIDs.ints(bodyIds)));
    }

    void destroyBody(int bodyId);

    void destroyBodies(int... bodyIds);
    default void destroyBodies(Collection<Integer> bodyIds) {
        destroyBodies(BodyIDs.ints(bodyIds));
    }

    void addBody(int bodyId, Activation activationMode);

    void removeBody(int bodyId);

    int createAndAddBody(BodyCreationSettings settings, Activation activationMode);

    // TODO add bodies bulk

    void activateBody(int bodyId);

    void activateBodies(int... bodyIds);
    default void activateBodies(Collection<Integer> bodyIds) {
        activateBodies(BodyIDs.ints(bodyIds));
    }

    void deactivateBody(int bodyId);
    void deactivateBodies(int... bodyIds);
    default void deactivateBodies(Collection<Integer> bodyIds) {
        deactivateBodies(BodyIDs.ints(bodyIds));
    }

    // TODO
    // TwoBodyConstraint createConstraint()

    void activateConstraint(TwoBodyConstraint constraint);

    void setObjectLayer(int bodyId, short layer);

    void setPositionAndRotationSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode);

    void setPositionAndRotationDp(int bodyId, JtVec3d position, JtQuat rotation, Activation activationMode);

    void setPositionAndRotationWhenChangedSp(int bodyId, JtVec3f position, JtQuat rotation, Activation activationMode);

    void setPositionAndRotationWhenChangedDp(int bodyId, JtVec3d position, JtQuat rotation, Activation activationMode);

    void setPositionSp(int bodyId, JtVec3f position, Activation activationMode);

    void setPositionDp(int bodyId, JtVec3d position, Activation activationMode);

    void setRotation(int bodyId, JtQuat rotation, Activation activationMode);

    void moveKinematicSp(int bodyId, JtVec3f targetPosition, JtQuat targetRotation, float deltaTime);

    void moveKinematicDp(int bodyId, JtVec3d targetPosition, JtQuat targetRotation, float deltaTime);

    void setLinearAndAngularVelocity(int bodyId, JtVec3f linearVelocity, JtVec3f angularVelocity);

    void setLinearVelocity(int bodyId, JtVec3f linearVelocity);

    void addLinearVelocity(int bodyId, JtVec3f linearVelocity);

    void addLinearAndAngularVelocity(int bodyId, JtVec3f linearVelocity, JtVec3f angularVelocity);

    void setAngularVelocity(int bodyId, JtVec3f linearVelocity);

    void setPositionRotationAndVelocitySp(int bodyId, JtVec3f position, JtQuat rotation, JtVec3f linearVelocity, JtVec3f angularVelocity);

    void setPositionRotationAndVelocityDp(int bodyId, JtVec3d position, JtQuat rotation, JtVec3f linearVelocity, JtVec3f angularVelocity);

    void addForce(int bodyId, JtVec3f force);

    void addForceSp(int bodyId, JtVec3f force, JtVec3f point);

    void addForceDp(int bodyId, JtVec3f force, JtVec3d point);

    void addTorque(int bodyId, JtVec3f torque);

    void addForceAndTorque(int bodyId, JtVec3f force, JtVec3f torque);

    void addImpulse(int bodyId, JtVec3f impulse);

    void addImpulseSp(int bodyId, JtVec3f impulse, JtVec3f point);

    void addImpulseDp(int bodyId, JtVec3f impulse, JtVec3d point);

    void addAngularImpulse(int bodyId, JtVec3f angularImpulse);

    void setMotionType(int bodyId, MotionType motionType, Activation activationMode);

    void setMotionQuality(int bodyId, MotionQuality motionQuality);

    void setRestitution(int bodyId, float restitution);

    void setFriction(int bodyId, float friction);

    void setGravityFactor(int bodyId, float gravityFactor);

    void invalidateContactCache(int bodyId);
}
