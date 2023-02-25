package jolt.physics.collision.shape;

import jolt.JoltNative;
import jolt.geometry.AABox;
import jolt.math.JtMat44f;
import jolt.math.JtQuat;
import jolt.math.JtVec3d;
import jolt.math.JtVec3f;
import jolt.physics.body.MassProperties;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.RayCast3f;
import jolt.physics.collision.TransformedShape;

public sealed interface Shape extends JoltNative permits MutableShape {
    static Shape ref(long address) { return address == 0 ? null : new ShapeImpl(address); }

    ShapeType getType();

    ShapeSubType getSubType();

    int getUserData();

    boolean mustBeStatic();

    JtVec3f getCenterOfMass(JtVec3f out);
    default JtVec3f getCenterOfMass() { return getCenterOfMass(new JtVec3f()); }

    AABox getLocalBounds(AABox out);
    default AABox getLocalBounds() { return getLocalBounds(new AABox()); }

    int getSubShapeIdBitsRecursive();

    AABox getWorldSpaceBounds(JtMat44f centerOfMassTransform, JtVec3f scale, AABox out);
    default AABox getWorldSpaceBounds(JtMat44f centerOfMassTransform, JtVec3f scale) { return getWorldSpaceBounds(centerOfMassTransform, scale, new AABox()); }

    float getInnerRadius();

    MassProperties getMassProperties();

    PhysicsMaterial getMaterial(int subShapeId);

    // TODO getSupportingFace

    long getSubShapeUserData(int subShapeId);

    TransformedShape getSubShapeTransformedShape(int subShapeId, JtVec3f positionCOM, JtQuat rotation, JtVec3f scale);

    // TODO getSubmergedVolume

    // TODO castRay

    // TODO scaleShape

    float getVolume();

    boolean isValidScale(JtVec3f scale);
}
