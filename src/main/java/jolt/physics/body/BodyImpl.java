package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Jolt;
import jolt.math.*;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.*;
import static jolt.headers_f.JoltPhysicsC.C_FLOAT;

abstract sealed class BodyImpl extends AddressedJoltNative implements MutableBody
        permits BodyImpl.F, BodyImpl.D {
    static final MutableBody FIXED_TO_WORLD = MutableBody.at(JPC_Body_FixedToWorld());

    private BodyImpl(MemoryAddress handle) {
        super(handle);
    }

    @Override
    public int getId() {
        return JPC_Body_GetID(handle);
    }

    @Override
    public boolean isActive() {
        return JPC_Body_IsActive(handle);
    }

    @Override
    public boolean isStatic() {
        return JPC_Body_IsStatic(handle);
    }

    @Override
    public boolean isKinematic() {
        return JPC_Body_IsKinematic(handle);
    }

    @Override
    public boolean isDynamic() {
        return JPC_Body_IsDynamic(handle);
    }

    @Override
    public boolean canBeKinematicOrDynamic() {
        return JPC_Body_CanBeKinematicOrDynamic(handle);
    }

    @Override
    public void setIsSensor(boolean isSensor) {
        JPC_Body_SetIsSensor(handle, isSensor);
    }

    @Override
    public boolean isSensor() {
        return JPC_Body_IsSensor(handle);
    }

    @Override
    public void setUseManifoldReduction(boolean useReduction) {
        JPC_Body_SetUseManifoldReduction(handle, useReduction);
    }

    @Override
    public boolean getUseManifoldReduction() {
        return JPC_Body_GetUseManifoldReduction(handle);
    }

    @Override
    public boolean getUseManifoldReductionWithBody(Body body) {
        return JPC_Body_GetUseManifoldReductionWithBody(handle, body.address());
    }

    @Override
    public MotionType getMotionType() {
        return MotionType.values()[JPC_Body_GetMotionType(handle)];
    }

    @Override
    public void setMotionType(MotionType motionType) {
        JPC_Body_SetMotionType(handle, (byte) motionType.ordinal());
    }

    @Override
    public byte getBroadPhaseLayer() {
        return JPC_Body_GetBroadPhaseLayer(handle);
    }

    @Override
    public short getObjectLayer() {
        return JPC_Body_GetObjectLayer(handle);
    }

    // TODO
//    @Override
//    public void getCollisionGroup(CollisionGroup out) {
//        return CollisionGroup.of(JPC_Body_GetCollisionGroup(handle));
//    }

    @Override
    public void setCollisionGroup(CollisionGroup group) {
        JPC_Body_SetCollisionGroup(handle, group.address());
    }

    @Override
    public boolean getAllowSleeping() {
        return JPC_Body_GetAllowSleeping(handle);
    }

    @Override
    public void setAllowSleeping(boolean allowSleeping) {
        JPC_Body_SetAllowSleeping(handle, allowSleeping);
    }

    @Override
    public float getFriction() {
        return JPC_Body_GetFriction(handle);
    }

    @Override
    public void setFriction(float friction) {
        JPC_Body_SetFriction(handle, friction);
    }

    @Override
    public float getRestitution() {
        return JPC_Body_GetRestitution(handle);
    }

    @Override
    public void setRestitution(float restitution) {
        JPC_Body_SetRestitution(handle, restitution);
    }

    @Override
    public void getLinearVelocity(FVec3 out) {
        JPC_Body_GetLinearVelocity(handle, out.address());
    }

    @Override
    public void setLinearVelocity(FVec3 linearVelocity) {
        JPC_Body_SetLinearVelocity(handle, linearVelocity.address());
    }

    @Override
    public void setLinearVelocityClamped(FVec3 linearVelocity) {
        JPC_Body_SetLinearVelocity(handle, linearVelocity.address());
    }

    @Override
    public void getAngularVelocity(FVec3 out) {
        JPC_Body_GetAngularVelocity(handle, out.address());
    }

    @Override
    public void setAngularVelocity(FVec3 angularVelocity) {
        JPC_Body_SetAngularVelocity(handle, angularVelocity.address());
    }

    @Override
    public void setAngularVelocityClamped(FVec3 angularVelocity) {
        JPC_Body_SetAngularVelocityClamped(handle, angularVelocity.address());
    }

    @Override
    public void getPointVelocityCOM(FVec3 pointRelativeToCOM, FVec3 out) {
        JPC_Body_GetPointVelocityCOM(handle, pointRelativeToCOM.address(), out.address());
    }

    @Override
    public void addForce(FVec3 force) {
        JPC_Body_AddForce(handle, force.address());
    }

    @Override
    public void addTorque(FVec3 torque) {
        JPC_Body_AddTorque(handle, torque.address());
    }

    @Override
    public void getAccumulatedForce(FVec3 out) {
        JPC_Body_GetAccumulatedForce(handle, out.address());
    }

    @Override
    public void getAccumulatedTorque(FVec3 out) {
        JPC_Body_GetAccumulatedTorque(handle, out.address());
    }

    @Override
    public void getInverseInertia(FMat44 out) {
        JPC_Body_GetInverseInertia(handle, out.address());
    }

    @Override
    public void addImpulse(FVec3 impulse) {
        JPC_Body_AddImpulse(handle, impulse.address());
    }

    @Override
    public void addAngularImpulse(FVec3 angularImpulse) {
        JPC_Body_AddAngularImpulse(handle, angularImpulse.address());
    }

    @Override
    public boolean isInBroadPhase() {
        return JPC_Body_IsInBroadPhase(handle);
    }

    @Override
    public boolean isCollisionCacheInvalid() {
        return JPC_Body_IsCollisionCacheInvalid(handle);
    }

    @Override
    public Shape getShape() {
        return Shape.at(JPC_Body_GetShape(handle));
    }

    @Override
    public void getRotation(Quat out) {
        JPC_Body_GetRotation(handle, out.address());
    }

    @Override
    public MutableMotionProperties getMotionProperties() {
        return null; // TODO
    }

    @Override
    public long getUserData() {
        return JPC_Body_GetUserData(handle);
    }

    @Override
    public void setUserData(long userData) {
        JPC_Body_SetUserData(handle, userData);
    }

    @Override
    public void getTransformedShape(TransformedShape out) {
        JPC_Body_GetTransformedShape(handle, out.address());
    }

    @Override
    public void getBodyCreationSettings(BodyCreationSettings out) {
        JPC_Body_GetBodyCreationSettings(handle, out.address());
    }

    static final class F extends BodyImpl {
        F(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPointVelocity(FVec3 point, FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_GetPointVelocity(handle, point.address(), out.address());
        }

        @Override
        public void getPointVelocity(DVec3 point, FVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void addForce(FVec3 force, FVec3 position) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_AddForceAtPosition(handle, force.address(), position.address());
        }

        @Override
        public void addForce(FVec3 force, DVec3 position) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void addImpulse(FVec3 impulse, FVec3 position) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_AddImpulseAtPosition(handle, impulse.address(), position.address());
        }

        @Override
        public void addImpulse(FVec3 impulse, DVec3 position) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void moveKinematic(FVec3 targetPosition, Quat targetRotation, float deltaTime) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_MoveKinematic(handle, targetPosition.address(), targetRotation.address(), deltaTime);
        }

        @Override
        public void moveKinematic(DVec3 targetPosition, Quat targetRotation, float deltaTime) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public boolean applyBuoyancyImpulse(FVec3 surfacePosition, FVec3 surfaceNormal, float buoyancy, float linearDrag, float angularDrag, FVec3 fluidVelocity, FVec3 gravity, float deltaTime) {
            return jolt.headers_f.JoltPhysicsC.JPC_Body_ApplyBuoyancyImpulse(handle,
                    surfacePosition.address(),
                    surfaceNormal.address(),
                    buoyancy,
                    linearDrag,
                    angularDrag,
                    fluidVelocity.address(),
                    gravity.address(),
                    deltaTime
            );
        }

        @Override
        public boolean applyBuoyancyImpulse(DVec3 surfacePosition, FVec3 surfaceNormal, float buoyancy, float linearDrag, float angularDrag, FVec3 fluidVelocity, FVec3 gravity, float deltaTime) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getPosition(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_GetPosition(handle, out.address());
        }

        @Override
        public void getPosition(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getWorldTransform(FMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_FLOAT, new float[3]);
                jolt.headers_f.JoltPhysicsC.JPC_Body_GetWorldTransform(handle, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_FLOAT));
            }
        }

        @Override
        public void getWorldTransform(DMat44 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getCenterOfMassPosition(FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_GetCenterOfMassPosition(handle, out.address());
        }

        @Override
        public void getCenterOfMassPosition(DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getCenterOfMassTransform(FMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_FLOAT, new float[3]);
                jolt.headers_f.JoltPhysicsC.JPC_Body_GetCenterOfMassTransform(handle, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_FLOAT));
            }
        }

        @Override
        public void getCenterOfMassTransform(DMat44 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getInverseCenterOfMassTransform(FMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_FLOAT, new float[3]);
                jolt.headers_f.JoltPhysicsC.JPC_Body_GetInverseCenterOfMassTransform(handle, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_FLOAT));
            }
        }

        @Override
        public void getInverseCenterOfMassTransform(DMat44 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getWorldSpaceSurfaceNormal(int subShapeId, FVec3 position, FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_Body_GetWorldSpaceSurfaceNormal(handle, subShapeId, position.address(), out.address());
        }

        @Override
        public void getWorldSpaceSurfaceNormal(int subShapeId, DVec3 position, FVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    static final class D extends BodyImpl {
        D(MemoryAddress handle) {
            super(handle);
        }

        @Override
        public void getPointVelocity(FVec3 point, FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPointVelocity(DVec3 point, FVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_GetPointVelocity(handle, point.address(), out.address());
        }

        @Override
        public void addForce(FVec3 force, FVec3 position) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void addForce(FVec3 force, DVec3 position) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_AddForceAtPosition(handle, force.address(), position.address());
        }

        @Override
        public void addImpulse(FVec3 impulse, FVec3 position) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void addImpulse(FVec3 impulse, DVec3 position) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_AddImpulseAtPosition(handle, impulse.address(), position.address());
        }

        @Override
        public void moveKinematic(FVec3 targetPosition, Quat targetRotation, float deltaTime) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void moveKinematic(DVec3 targetPosition, Quat targetRotation, float deltaTime) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_MoveKinematic(handle, targetPosition.address(), targetRotation.address(), deltaTime);
        }

        @Override
        public boolean applyBuoyancyImpulse(FVec3 surfacePosition, FVec3 surfaceNormal, float buoyancy, float linearDrag, float angularDrag, FVec3 fluidVelocity, FVec3 gravity, float deltaTime) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public boolean applyBuoyancyImpulse(DVec3 surfacePosition, FVec3 surfaceNormal, float buoyancy, float linearDrag, float angularDrag, FVec3 fluidVelocity, FVec3 gravity, float deltaTime) {
            return jolt.headers_d.JoltPhysicsC.JPC_Body_ApplyBuoyancyImpulse(handle,
                    surfacePosition.address(),
                    surfaceNormal.address(),
                    buoyancy,
                    linearDrag,
                    angularDrag,
                    fluidVelocity.address(),
                    gravity.address(),
                    deltaTime
            );
        }

        @Override
        public void getPosition(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPosition(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_GetPosition(handle, out.address());
        }

        @Override
        public void getWorldTransform(FMat44 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getWorldTransform(DMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_DOUBLE, new double[3]);
                jolt.headers_d.JoltPhysicsC.JPC_Body_GetWorldTransform(handle, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_DOUBLE));
            }
        }

        @Override
        public void getCenterOfMassPosition(FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getCenterOfMassPosition(DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_GetCenterOfMassPosition(handle, out.address());
        }

        @Override
        public void getCenterOfMassTransform(FMat44 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getCenterOfMassTransform(DMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_DOUBLE, new double[3]);
                jolt.headers_d.JoltPhysicsC.JPC_Body_GetCenterOfMassTransform(handle, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_DOUBLE));
            }
        }

        @Override
        public void getInverseCenterOfMassTransform(FMat44 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getInverseCenterOfMassTransform(DMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_DOUBLE, new double[3]);
                jolt.headers_d.JoltPhysicsC.JPC_Body_GetInverseCenterOfMassTransform(handle, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_DOUBLE));
            }
        }

        @Override
        public void getWorldSpaceSurfaceNormal(int subShapeId, FVec3 position, FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getWorldSpaceSurfaceNormal(int subShapeId, DVec3 position, FVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_Body_GetWorldSpaceSurfaceNormal(handle, subShapeId, position.address(), out.address());
        }
    }
}
