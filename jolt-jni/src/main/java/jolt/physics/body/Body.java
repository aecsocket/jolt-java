package jolt.physics.body;

import jolt.JoltNative;
import jolt.geometry.AABox;
import jolt.math.*;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

public sealed interface Body extends JoltNative permits MutableBody {
    static Body ref(long address) { return address == 0 ? null : new BodyImpl(address); }

    int getId();

    boolean isActive();

    boolean isStatic();

    boolean isKinematic();

    boolean isDynamic();

    boolean canBeKinematicOrDynamic();

    boolean isSensor();

    boolean getUseManifoldReduction();

    boolean getUseManifoldReductionWithBody(Body body);

    MotionType getMotionType();

    byte getBroadPhaseLayer();

    short getObjectLayer();

    CollisionGroup getCollisionGroup();

    boolean getAllowSleeping();

    float getFriction();

    float getRestitution();

    JtVec3f getLinearVelocity(JtVec3f out);
    default JtVec3f getLinearVelocity() { return getLinearVelocity(new JtVec3f()); }

    JtVec3f getAngularVelocity(JtVec3f out);
    default JtVec3f getAngularVelocity() { return getAngularVelocity(new JtVec3f()); }

    JtVec3f getPointVelocityCOM(JtVec3f pointRelativeToCOM, JtVec3f out);
    default JtVec3f getPointVelocityCOM(JtVec3f pointRelativeToCOM) { return getPointVelocityCOM(pointRelativeToCOM, new JtVec3f()); }

    JtVec3f getPointVelocitySp(JtVec3f point, JtVec3f out);
    default JtVec3f getPointVelocitySp(JtVec3f point) { return getPointVelocitySp(point, new JtVec3f()); }

    JtVec3f getPointVelocityDp(JtVec3d point, JtVec3f out);
    default JtVec3f getPointVelocityDp(JtVec3d point) { return getPointVelocityDp(point, new JtVec3f()); }

    JtVec3f getAccumulatedForce(JtVec3f out);
    default JtVec3f getAccumulatedForce() { return getAccumulatedForce(new JtVec3f()); }

    JtVec3f getAccumulatedTorque(JtVec3f out);
    default JtVec3f getAccumulatedTorque() { return getAccumulatedTorque(new JtVec3f()); }

    JtMat44f getInverseInertia(JtMat44f out);
    default JtMat44f getInverseInertia() { return getInverseInertia(new JtMat44f()); }

    boolean isInBroadPhase();

    boolean isCollisionCacheInvalid();

    Shape getShape();

    JtVec3f getPositionSp(JtVec3f out);
    default JtVec3f getPositionSp() { return getPositionSp(new JtVec3f()); }

    JtVec3d getPositionDp(JtVec3d out);
    default JtVec3d getPositionDp() { return getPositionDp(new JtVec3d()); }

    JtQuat getRotation(JtQuat out);
    default JtQuat getRotation() { return getRotation(new JtQuat()); }

    JtMat44f getWorldTransformSp(JtMat44f out);
    default JtMat44f getWorldTransformSp() { return getWorldTransformSp(new JtMat44f()); }

    JtMat44d getWorldTransformDp(JtMat44d out);
    default JtMat44d getWorldTransformDp() { return getWorldTransformDp(new JtMat44d()); }

    JtVec3f getCenterOfMassPositionSp(JtVec3f out);
    default JtVec3f getCenterOfMassPositionSp() { return getCenterOfMassPositionSp(new JtVec3f()); }

    JtVec3d getCenterOfMassPositionDp(JtVec3d out);
    default JtVec3d getCenterOfMassPositionDp() { return getCenterOfMassPositionDp(new JtVec3d()); }

    JtMat44f getCenterOfMassTransformSp(JtMat44f out);
    default JtMat44f getCenterOfMassTransformSp() { return getCenterOfMassTransformSp(new JtMat44f()); }

    JtMat44d getCenterOfMassTransformDp(JtMat44d out);
    default JtMat44d getCenterOfMassTransformDp() { return getCenterOfMassTransformDp(new JtMat44d()); }

    JtMat44f getInverseCenterOfMassTransformSp(JtMat44f out);
    default JtMat44f getInverseCenterOfMassTransformSp() { return getInverseCenterOfMassTransformSp(new JtMat44f()); }

    JtMat44d getInverseCenterOfMassTransformDp(JtMat44d out);
    default JtMat44d getInverseCenterOfMassTransformDp() { return getInverseCenterOfMassTransformDp(new JtMat44d()); }

    AABox getWorldSpaceBounds(AABox out);
    default AABox getWorldSpaceBounds() { return getWorldSpaceBounds(new AABox()); }

    MotionProperties getMotionProperties();

    long getUserData();

    JtVec3f getWorldSpaceSurfaceNormalSp(int subShapeId, JtVec3f position, JtVec3f out);
    default JtVec3f getWorldSpaceSurfaceNormalSp(int subShapeId, JtVec3f position) { return getWorldSpaceSurfaceNormalSp(subShapeId, position, new JtVec3f()); }

    JtVec3f getWorldSpaceSurfaceNormalDp(int subShapeId, JtVec3d position, JtVec3f out);
    default JtVec3f getWorldSpaceSurfaceNormalDp(int subShapeId, JtVec3d position) { return getWorldSpaceSurfaceNormalDp(subShapeId, position, new JtVec3f()); }

    TransformedShape getTransformedShape();

    BodyCreationSettings getBodyCreationSettings();
}
