package jolt.physics.collision.shape;

import jolt.DestroyableJoltNative;
import jolt.geometry.AABox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.collision.PhysicsMaterial;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;

import static jolt.headers.JoltPhysicsC.*;

public sealed class Shape extends DestroyableJoltNative
        permits ConvexShape, CompoundShape {
    public static final float DEFAULT_CONVEX_RADIUS = 0.05f;

    //region Jolt-Pointer-Protected
    protected Shape(MemoryAddress handle) {
        super(handle);
    }

    public static Shape at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new Shape(addr);
    }
    //endregion Jolt-Pointer-Protected

    @Override
    protected void destroyInternal() {
        JPC_Shape_Release(handle);
    }

    public ShapeType getType() {
        return ShapeType.values()[JPC_Shape_GetType(handle)];
    }

    public ShapeSubType getSubType() {
        return ShapeSubType.values()[JPC_Shape_GetSubType(handle)];
    }

    public long getUserData() {
        return JPC_Shape_GetUserData(handle);
    }

    public void setUserData(long userData) {
        JPC_Shape_SetUserData(handle, userData);
    }

    public boolean mustBeStatic() {
        return JPC_Shape_MustBeStatic(handle);
    }

    public void getCenterOfMass(FVec3 out) {
        JPC_Shape_GetCenterOfMass(handle, out.address());
    }

    public void getLocalBounds(AABox out) {
        JPC_Shape_GetLocalBounds(handle, out.address());
    }

    public int getSubShapeIDBitsRecursive() {
        return JPC_Shape_GetSubShapeIDBitsRecursive(handle);
    }

    public void getWorldSpaceBounds(FMat44 comTransform, FVec3 scale, AABox out) {
        JPC_Shape_GetWorldSpaceBounds(handle, comTransform.address(), scale.address(), out.address());
    }

    public float getInnerRadius() {
        return JPC_Shape_GetInnerRadius(handle);
    }

    // TODO getMassProperties

    public @Nullable PhysicsMaterial getMaterial(int subShapeId) {
        return PhysicsMaterial.at(JPC_Shape_GetMaterial(handle, subShapeId));
    }

    public void getSurfaceNormal(int subShapeId, FVec3 localSurfacePosition, FVec3 out) {
        JPC_Shape_GetSurfaceNormal(handle, subShapeId, localSurfacePosition.address(), out.address());
    }

    // TODO getSupportingFace

    public long getSubShapeUserData(int subShapeId) {
        return JPC_Shape_GetSubShapeUserData(handle, subShapeId);
    }

    // TODO
//    public TransformedShape getSubShapeTransformedShape(MemorySession session, int subShapeId, FVec3 positionCOM, Quat rotation, FVec3 scale) {
//
//        try (var session = MemorySession.openConfined()) {
//            return TransformedShape.at(JPC_Shape_GetSubShapeTransformedShape(session, address,
//                    subShapeId,
//                    positionCOM.allocate(session),
//                    rotation.allocate(session),
//                    scale.allocate(session)
//            ));
//        }
//    }

    // TODO getSubmergedVolume
    // TODO castRay
    // TODO castRay
    // TODO collidePoint
    // TODO collectTransformedShapes
    // TODO transformShape
    // TODO scaleShape
    // TODO getTrianglesStart
    // TODO getTrianglesNext

    public float getVolume() {
        return JPC_Shape_GetVolume(handle);
    }

    public boolean isValidScale(FVec3 scale) {
        return JPC_Shape_IsValidScale(handle, scale.address());
    }
}
