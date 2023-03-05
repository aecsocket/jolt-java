package jolt.physics.body;

import jolt.Jolt;
import jolt.JoltNative;
import jolt.math.*;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.MemoryAddress;

public sealed interface Body extends JoltNative permits MutableBody {
    static Body at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new BodyImpl.D(addr)
                : new BodyImpl.F(addr);
    }

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

    // TODO
    // CollisionGroup getCollisionGroup();

    boolean getAllowSleeping();

    float getFriction();

    float getRestitution();

    void getLinearVelocity(FVec3 out);

    void getAngularVelocity(FVec3 out);

    void getPointVelocityCOM(FVec3 pointRelativeToCOM, FVec3 out);

    void getPointVelocity(FVec3 point, FVec3 out);

    void getPointVelocity(DVec3 point, FVec3 out);

    void getAccumulatedForce(FVec3 out);

    void getAccumulatedTorque(FVec3 out);

    void getInverseInertia(FMat44 out);

    boolean isInBroadPhase();

    boolean isCollisionCacheInvalid();

    Shape getShape();

    void getPosition(FVec3 out);

    void getPosition(DVec3 out);

    void getRotation(Quat out);

    void getWorldTransform(FMat44 out);

    void getWorldTransform(DMat44 out);

    void getCenterOfMassPosition(FVec3 out);

    void getCenterOfMassPosition(DVec3 out);

    void getCenterOfMassTransform(FMat44 out);

    void getCenterOfMassTransform(DMat44 out);

    void getInverseCenterOfMassTransform(FMat44 out);

    void getInverseCenterOfMassTransform(DMat44 out);

    MotionProperties getMotionProperties();

    long getUserData();

    void getWorldSpaceSurfaceNormal(int subShapeId, FVec3 position, FVec3 out);

    void getWorldSpaceSurfaceNormal(int subShapeId, DVec3 position, FVec3 out);

    void getTransformedShape(TransformedShape out);

    void getBodyCreationSettings(BodyCreationSettings out);


}
