package jolt.physics.body;

import jolt.JoltNative;
import jolt.math.*;
import jolt.physics.Activation;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

public sealed interface BodyInterface extends JoltNative permits MutableBodyInterface {
    MutableBody createBodyWithoutId(BodyCreationSettings settings);

    void destroyBodyWithoutId(MutableBody body);

    boolean isAdded(int bodyId);

    boolean isActive(int bodyId);

    // TODO getshape

    void setShape(int bodyId, Shape shape, boolean updateMassProperties, Activation activationMode);

    void notifyShapeChanged(int bodyId, JtVec3f previousCenterOfMass, boolean updateMassProperties, Activation activationMode);

    short getObjectLayer(int bodyId);

    void getPositionAndRotationSp(int bodyId, JtVec3f outPosition, JtQuat outRotation);

    JtVec3f getPositionSp(int bodyId, JtVec3f out);
    default JtVec3f getPositionSp(int bodyId) { return getPositionSp(bodyId, new JtVec3f()); }

    JtVec3d getPositionDp(int bodyId, JtVec3d out);
    default JtVec3d getPositionDp(int bodyId) { return getPositionDp(bodyId, new JtVec3d()); }

    JtVec3f getCenterOfMassPositionSp(int bodyId, JtVec3f out);
    default JtVec3f getCenterOfMassPositionSp(int bodyId) { return getCenterOfMassPositionSp(bodyId, new JtVec3f()); }
    
    JtVec3d getCenterOfMassPositionDp(int bodyId, JtVec3d out);
    default JtVec3d getCenterOfMassPositionDp(int bodyId) { return getCenterOfMassPositionDp(bodyId, new JtVec3d()); }
    
    JtQuat getRotation(int bodyId, JtQuat out);
    default JtQuat getRotation(int bodyId) { return getRotation(bodyId, new JtQuat()); }
    
    JtMat44f getWorldTransformSp(int bodyId, JtMat44f out);
    default JtMat44f getWorldTransformSp(int bodyId) { return getWorldTransformSp(bodyId, new JtMat44f()); }
    
    JtMat44d getWorldTransformDp(int bodyId, JtMat44d out);
    default JtMat44d getWorldTransformDp(int bodyId) { return getWorldTransformDp(bodyId, new JtMat44d()); }

    JtMat44f getCenterOfMassTransformSp(int bodyId, JtMat44f out);
    default JtMat44f getCenterOfMassTransformSp(int bodyId) { return getCenterOfMassTransformSp(bodyId, new JtMat44f()); }

    JtMat44d getCenterOfMassTransformDp(int bodyId, JtMat44d out);
    default JtMat44d getCenterOfMassTransformDp(int bodyId) { return getCenterOfMassTransformDp(bodyId, new JtMat44d()); }

    //void moveKinematicSp(int bodyId, JtVec3f targetPosition, JtQuat targetRotation, float deltaTime);

    //void moveKinematicDp(int bodyId, JtVec3d targetPosition, JtQuat targetRotation, float deltaTime);

    void getLinearAndAngularVelocity(int bodyId, JtVec3f outLinearVelocity, JtVec3f outAngularVelocity);

    JtVec3f getLinearVelocity(int bodyId, JtVec3f out);
    default JtVec3f getLinearVelocity(int bodyId) { return getLinearVelocity(bodyId, new JtVec3f()); }

    JtVec3f getAngularVelocity(int bodyId, JtVec3f out);
    default JtVec3f getAngularVelocity(int bodyId) { return getAngularVelocity(bodyId, new JtVec3f()); }

    JtVec3f getPointVelocitySp(int bodyId, JtVec3f point, JtVec3f out);
    default JtVec3f getPointVelocitySp(int bodyId, JtVec3f point) { return getPointVelocitySp(bodyId, point, new JtVec3f()); }

    JtVec3f getPointVelocityDp(int bodyId, JtVec3d point, JtVec3f out);
    default JtVec3f getPointVelocityDp(int bodyId, JtVec3d point) { return getPointVelocityDp(bodyId, point, new JtVec3f()); }

    MotionType getMotionType(int bodyId);

    MotionQuality getMotionQuality(int bodyId);

    JtMat44f getInverseInertia(int bodyId, JtMat44f out);
    default JtMat44f getInverseInertia(int bodyId) { return getInverseInertia(bodyId, new JtMat44f()); }

    float getRestitution(int bodyId);

    float getFriction(int bodyId);

    float getGravityFactor(int bodyId);

    TransformedShape getTransformedShape(int bodyId);

    long getUserData(int bodyId);

    PhysicsMaterial getMaterial(int bodyId, int subShapeId);
}
