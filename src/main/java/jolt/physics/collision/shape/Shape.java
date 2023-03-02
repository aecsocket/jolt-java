package jolt.physics.collision.shape;

import jolt.DestroyableJoltNative;
import jolt.geometry.AABox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.TransformedShape;

import javax.annotation.Nullable;
import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public sealed class Shape extends DestroyableJoltNative
        permits ConvexShape {
    public static final float DEFAULT_CONVEX_RADIUS = 0.05f;

    public static Shape at(Addressable ptr) {
        var address = ptr.address();
        return address == MemoryAddress.NULL ? null : new Shape(address);
    }

    protected Shape(MemoryAddress address) {
        super(address);
    }

    @Override
    protected void destroyInternal() {
        JPC_Shape_Release(address);
    }

    public ShapeType getType() {
        return ShapeType.values()[JPC_Shape_GetType(address)];
    }

    public ShapeSubType getSubType() {
        return ShapeSubType.values()[JPC_Shape_GetSubType(address)];
    }

    public long getUserData() {
        return JPC_Shape_GetUserData(address);
    }

    public void setUserData(long userData) {
        JPC_Shape_SetUserData(address, userData);
    }

    public boolean mustBeStatic() {
        return JPC_Shape_MustBeStatic(address);
    }

    public FVec3 getCenterOfMass() {
        try (var session = MemorySession.openConfined()) {
            var out = FVec3.ZERO.allocate(session);
            JPC_Shape_GetCenterOfMass(address, out);
            return FVec3.read(out);
        }
    }

    public AABox getLocalBounds() {
        try (var session = MemorySession.openConfined()) {
            return AABox.read(JPC_Shape_GetLocalBounds(session, address));
        }
    }

    public int getSubShapeIDBitsRecursive() {
        return JPC_Shape_GetSubShapeIDBitsRecursive(address);
    }

    public AABox getWorldSpaceBounds(FMat44 comTransform, FVec3 scale) {
        try (var session = MemorySession.openConfined()) {
            return AABox.read(JPC_Shape_GetWorldSpaceBounds(session, address,
                    comTransform.allocate(session),
                    scale.allocate(session)
            ));
        }
    }

    public float getInnerRadius() {
        return JPC_Shape_GetInnerRadius(address);
    }

    // TODO getMassProperties

    public @Nullable PhysicsMaterial getMaterial(int subShapeId) {
        return PhysicsMaterial.at(JPC_Shape_GetMaterial(address, subShapeId));
    }

    public FVec3 getSurfaceNormal(int subShapeId, FVec3 localSurfacePosition) {
        try (var session = MemorySession.openConfined()) {
            var out = FVec3.ZERO.allocate(session);
            JPC_Shape_GetSurfaceNormal(address, subShapeId, localSurfacePosition.allocate(session), out);
            return FVec3.read(out);
        }
    }

    // TODO getSupportingFace

    public long getSubShapeUserData(int subShapeId) {
        return JPC_Shape_GetSubShapeUserData(address, subShapeId);
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
        return JPC_Shape_GetVolume(address);
    }

    public boolean isValidScale(FVec3 scale) {
        try (var session = MemorySession.openConfined()) {
            return JPC_Shape_IsValidScale(address, scale.allocate(session));
        }
    }
}
