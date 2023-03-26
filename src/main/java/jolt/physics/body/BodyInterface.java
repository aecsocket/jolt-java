package jolt.physics.body;

import jolt.AddressedJoltNative;
import jolt.Deletable;
import jolt.Jolt;
import jolt.math.*;
import jolt.physics.Activation;
import jolt.physics.collision.PhysicsMaterial;
import jolt.physics.collision.TransformedShape;
import jolt.physics.collision.shape.Shape;

import java.lang.foreign.MemoryAddress;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static jolt.headers_f.JoltPhysicsC.*;

public abstract sealed class BodyInterface extends AddressedJoltNative
        permits BodyInterface.F, BodyInterface.D {
    //region Jolt-Pointer-FD
    private BodyInterface(MemoryAddress handle) {
        super(handle);
    }

    public static BodyInterface at(MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision() ? new D(addr) : new F(addr);
    }
    //endregion Jolt-Pointer-FD

    public MutableBody createBody(BodyCreationSettings settings) {
        return MutableBody.at(JPC_BodyInterface_CreateBody(handle, settings.address()));
    }

    public MutableBody createBodyWithId(int bodyId, BodyCreationSettings settings) {
        return MutableBody.at(JPC_BodyInterface_CreateBodyWithID(handle, bodyId, settings.address()));
    }

    public MutableBody createBodyWithoutId(BodyCreationSettings settings) {
        return MutableBody.at(JPC_BodyInterface_CreateBodyWithoutID(handle, settings.address()));
    }

    public void destroyBodyWithoutId(MutableBody body) {
        JPC_BodyInterface_DestroyBodyWithoutID(handle, body.address());
    }

    public boolean assignBodyId(MutableBody body) {
        return JPC_BodyInterface_AssignNextBodyID(address(), body.address());
    }

    public boolean assignBodyId(MutableBody body, int bodyId) {
        return JPC_BodyInterface_AssignBodyID(handle, body.address(), bodyId);
    }

    public MutableBody unassignBodyId(int bodyId) {
        return MutableBody.at(JPC_BodyInterface_UnassignBodyID(handle, bodyId));
    }

    public MutableBody[] unassignBodyIds(int... bodyIds) {
        try (var arena = MemorySession.openConfined()) {
            var nBodyIds = arena.allocateArray(C_INT, bodyIds);
            var nOutBodies = arena.allocateArray(C_POINTER, bodyIds.length);
            JPC_BodyInterface_UnassignBodyIDs(handle, nBodyIds, bodyIds.length, nOutBodies);
            var result = new MutableBody[bodyIds.length];
            for (int i = 0; i < bodyIds.length; i++) {
                result[i] = MutableBody.at(nOutBodies.getAtIndex(C_POINTER, i));
            }
            return result;
        }
    }

    public List<MutableBody> unassignBodyIds(Collection<? extends Integer> bodyIds) {
        return Arrays.asList(unassignBodyIds(Jolt.arrayOf(bodyIds)));
    }

    public void destroyBody(int bodyId) {
        JPC_BodyInterface_DestroyBody(handle, bodyId);
    }

    public void destroyBodies(int... bodyIds) {
        try (var arena = MemorySession.openConfined()) {
            var nBodyIds = arena.allocateArray(C_INT, bodyIds);
            JPC_BodyInterface_DestroyBodies(handle, nBodyIds.address(), bodyIds.length);
        }
    }

    public void destroyBodies(Collection<? extends Integer> bodyIds) {
        destroyBodies(Jolt.arrayOf(bodyIds));
    }

    public void addBody(int bodyId, Activation activation) {
        JPC_BodyInterface_AddBody(handle, bodyId, activation.ordinal());
    }

    public void removeBody(int bodyId) {
        JPC_BodyInterface_RemoveBody(handle, bodyId);
    }

    public boolean isAdded(int bodyId) {
        return JPC_BodyInterface_IsAdded(handle, bodyId);
    }

    public int createAndAddBody(BodyCreationSettings settings, Activation activation) {
        return JPC_BodyInterface_CreateAndAddBody(handle, settings.address(), activation.ordinal());
    }

    public static final class BodyBulk implements Deletable {
        private final MemorySession arena;
        private final MemoryAddress bodies;
        private final int numBodies;
        private MemoryAddress addState;
        private boolean destroyed;

        private BodyBulk(MemorySession arena, MemorySegment bodies, int numBodies) {
            this.arena = arena;
            this.bodies = bodies.address();
            this.numBodies = numBodies;
        }

        @Override
        public boolean isDeleted() {
            return destroyed;
        }

        @Override
        public void delete() {
            if (destroyed) throw new IllegalStateException("Object is already destroyed");
            arena.close();
            destroyed = true;
        }

        private void assertState() {
            if (addState == null)
                throw new IllegalStateException("Must call addBodiesPrepare with this BodyBulk first");
        }
    }

    public BodyBulk bodyBulk(int... bodyIds) {
        var arena = MemorySession.openConfined();
        return new BodyBulk(arena, arena.allocateArray(C_INT, bodyIds), bodyIds.length);
    }

    public BodyBulk bodyBulk(Collection<? extends Integer> bodyIds) {
        return bodyBulk(Jolt.arrayOf(bodyIds));
    }

    public void addBodiesPrepare(BodyBulk bulk) {
        bulk.addState = JPC_BodyInterface_AddBodiesPrepare(handle, bulk.bodies, bulk.numBodies);
    }

    public void addBodiesFinalize(BodyBulk bulk, Activation activation) {
        bulk.assertState();
        JPC_BodyInterface_AddBodiesFinalize(
                handle,
                bulk.bodies,
                bulk.numBodies,
                bulk.addState,
                activation.ordinal()
        );
    }

    public void addBodiesAbort(BodyBulk bulk) {
        bulk.assertState();
        JPC_BodyInterface_AddBodiesAbort(handle, bulk.bodies, bulk.numBodies, bulk.addState);
    }

    public void removeBodies(int... bodyIds) {
        try (var arena = MemorySession.openConfined()) {
            var nBodyIds = arena.allocateArray(C_INT, bodyIds);
            JPC_BodyInterface_RemoveBodies(handle, nBodyIds.address(), bodyIds.length);
        }
    }

    public void removeBodies(Collection<? extends Integer> bodyIds) {
        removeBodies(Jolt.arrayOf(bodyIds));
    }

    public void activateBody(int bodyId) {
        JPC_BodyInterface_ActivateBody(handle, bodyId);
    }

    public void activateBodies(int... bodyIds) {
        try (var arena = MemorySession.openConfined()) {
            var nBodyIds = arena.allocateArray(C_INT, bodyIds);
            JPC_BodyInterface_ActivateBodies(handle, nBodyIds.address(), bodyIds.length);
        }
    }

    public void activateBodies(Collection<? extends Integer> bodyIds) {
        activateBodies(Jolt.arrayOf(bodyIds));
    }

    public void deactivateBody(int bodyId) {
        JPC_BodyInterface_DeactivateBody(handle, bodyId);
    }

    public void deactivateBodies(int... bodyIds) {
        try (var arena = MemorySession.openConfined()) {
            var nBodyIds = arena.allocateArray(C_INT, bodyIds);
            JPC_BodyInterface_DeactivateBodies(handle, nBodyIds.address(), bodyIds.length);
        }
    }

    public void deactivateBodies(Collection<? extends Integer> bodyIds) {
        deactivateBodies(Jolt.arrayOf(bodyIds));
    }

    public boolean isActive(int bodyId) {
        return JPC_BodyInterface_IsActive(handle, bodyId);
    }

    // TODO createConstraint
    // TODO activateConstraint

    public Shape getShape(int bodyId) {
        return Shape.at(JPC_BodyInterface_GetShape(handle, bodyId));
    }

    public void setShape(int bodyId, Shape shape, boolean updateMassProperties, Activation activation) {
        JPC_BodyInterface_SetShape(handle, bodyId, shape.address(), updateMassProperties, activation.ordinal());
    }

    public void notifyShapeChanged(int bodyId, FVec3 previousCOM, boolean updateMassProperties, Activation activation) {
        JPC_BodyInterface_NotifyShapeChanged(handle, bodyId, previousCOM.address(), updateMassProperties, activation.ordinal());
    }

    public void setObjectLayer(int bodyId, short layer) {
        JPC_BodyInterface_SetObjectLayer(handle, bodyId, layer);
    }

    public short getObjectLayer(int bodyId) {
        return JPC_BodyInterface_GetObjectLayer(handle, bodyId);
    }

    public abstract void setPositionAndRotation(int bodyId, FVec3 position, Quat rotation, Activation activation);

    public abstract void setPositionAndRotation(int bodyId, DVec3 position, Quat rotation, Activation activation);

    public abstract void setPositionAndRotationWhenChanged(int bodyId, FVec3 position, Quat rotation, Activation activation);

    public abstract void setPositionAndRotationWhenChanged(int bodyId, DVec3 position, Quat rotation, Activation activation);

    public abstract void getPositionAndRotation(int bodyId, FVec3 outPosition, Quat outRotation);

    public abstract void getPositionAndRotation(int bodyId, DVec3 outPosition, Quat outRotation);

    public abstract void setPosition(int bodyId, FVec3 position, Activation activation);

    public abstract void setPosition(int bodyId, DVec3 position, Activation activation);

    public abstract void getCenterOfMassPosition(int bodyId, FVec3 out);

    public abstract void getCenterOfMassPosition(int bodyId, DVec3 out);

    public void setRotation(int bodyId, Quat rotation, Activation activation) {
        JPC_BodyInterface_SetRotation(handle, bodyId, rotation.address(), activation.ordinal());
    }

    public void getRotation(int bodyId, Quat out) {
        JPC_BodyInterface_GetRotation(handle, bodyId, out.address());
    }

    public abstract void getWorldTransform(int bodyId, FMat44 out);

    public abstract void getWorldTransform(int bodyId, DMat44 out);

    public abstract void getCenterOfMassTransform(int bodyId, FMat44 out);

    public abstract void getCenterOfMassTransform(int bodyId, DMat44 out);

    public abstract void moveKinematic(int bodyId, FVec3 targetPosition, Quat targetRotation, float deltaTime);

    public abstract void moveKinematic(int bodyId, DVec3 targetPosition, Quat targetRotation, float deltaTime);

    public void setLinearAndAngularVelocity(int bodyId, FVec3 linearVelocity, FVec3 angularVelocity) {
        JPC_BodyInterface_SetLinearAndAngularVelocity(handle, bodyId, linearVelocity.address(), angularVelocity.address());
    }

    public void getLinearAndAngularVelocity(int bodyId, FVec3 outLinearVelocity, FVec3 outAngularVelocity) {
        JPC_BodyInterface_GetLinearAndAngularVelocity(handle, bodyId, outLinearVelocity.address(), outAngularVelocity.address());
    }

    public void setLinearVelocity(int bodyId, FVec3 velocity) {
        JPC_BodyInterface_SetLinearVelocity(handle, bodyId, velocity.address());
    }

    public void getLinearVelocity(int bodyId, FVec3 out) {
        JPC_BodyInterface_GetLinearVelocity(handle, bodyId, out.address());
    }

    public void addLinearVelocity(int bodyId, FVec3 velocity) {
        JPC_BodyInterface_AddLinearVelocity(handle, bodyId, velocity.address());
    }

    public void addLinearAndAngularVelocity(int bodyId, FVec3 linearVelocity, FVec3 angularVelocity) {
        JPC_BodyInterface_AddLinearAndAngularVelocity(handle, bodyId, linearVelocity.address(), angularVelocity.address());
    }

    public void setAngularVelocity(int bodyId, FVec3 velocity) {
        JPC_BodyInterface_SetAngularVelocity(handle, bodyId, velocity.address());
    }

    public void getAngularVelocity(int bodyId, FVec3 out) {
        JPC_BodyInterface_GetAngularVelocity(handle, bodyId, out.address());
    }

    public abstract void getPointVelocity(int bodyId, FVec3 point, FVec3 out);

    public abstract void getPointVelocity(int bodyId, DVec3 point, FVec3 out);

    public abstract void setPositionRotationAndVelocity(int bodyId, FVec3 position, FVec3 rotation, FVec3 linearVelocity, FVec3 angularVelocity);

    public abstract void setPositionRotationAndVelocity(int bodyId, DVec3 position, FVec3 rotation, FVec3 linearVelocity, FVec3 angularVelocity);

    public void addForce(int bodyId, FVec3 force) {
        JPC_BodyInterface_AddForce(handle, bodyId, force.address());
    }

    public abstract void addForce(int bodyId, FVec3 force, FVec3 position);

    public abstract void addForce(int bodyId, FVec3 force, DVec3 position);

    public void addTorque(int bodyId, FVec3 torque) {
        JPC_BodyInterface_AddTorque(handle, bodyId, torque.address());
    }

    public void addForceAndTorque(int bodyId, FVec3 force, FVec3 torque) {
        JPC_BodyInterface_AddForceAndTorque(handle, bodyId, force.address(), torque.address());
    }

    public void addImpulse(int bodyId, FVec3 impulse) {
        JPC_BodyInterface_AddImpulse(handle, bodyId, impulse.address());
    }

    public abstract void addImpulse(int bodyId, FVec3 impulse, FVec3 position);

    public abstract void addImpulse(int bodyId, FVec3 impulse, DVec3 position);

    public void addAngularImpulse(int bodyId, FVec3 impulse) {
        JPC_BodyInterface_AddAngularImpulse(handle, bodyId, impulse.address());
    }

    public void setMotionType(int bodyId, MotionType motionType, Activation activation) {
        JPC_BodyInterface_SetMotionType(handle, bodyId, (byte) motionType.ordinal(), activation.ordinal());
    }

    public MotionType getMotionType(int bodyId) {
        return MotionType.values()[JPC_BodyInterface_GetMotionType(handle, bodyId)];
    }

    public void setMotionQuality(int bodyId, MotionQuality motionQuality) {
        JPC_BodyInterface_SetMotionQuality(handle, bodyId, (byte) motionQuality.ordinal());
    }

    public MotionQuality getMotionQuality(int bodyId) {
        return MotionQuality.values()[JPC_BodyInterface_GetMotionQuality(handle, bodyId)];
    }

    public void getInverseInertia(int bodyId, FMat44 out) {
        JPC_BodyInterface_GetInverseInertia(handle, bodyId, out.address());
    }

    public void setRestitution(int bodyId, float restitution) {
        JPC_BodyInterface_SetRestitution(handle, bodyId, restitution);
    }

    public float getRestitution(int bodyId) {
        return JPC_BodyInterface_GetRestitution(handle, bodyId);
    }

    public void setFriction(int bodyId, float friction) {
        JPC_BodyInterface_SetFriction(handle, bodyId, friction);
    }

    public float getFriction(int bodyId) {
        return JPC_BodyInterface_GetFriction(handle, bodyId);
    }

    public void setGravityFactor(int bodyId, float gravityFactor) {
        JPC_BodyInterface_SetGravityFactor(handle, bodyId, gravityFactor);
    }

    public float getGravityFactor(int bodyId) {
        return JPC_BodyInterface_GetGravityFactor(handle, bodyId);
    }

    public void getTransformedShape(int bodyId, TransformedShape out) {
        JPC_BodyInterface_GetTransformedShape(handle, bodyId, out.address());
    }

    public long getUserData(int bodyId) {
        return JPC_BodyInterface_GetUserData(handle, bodyId);
    }

    public PhysicsMaterial getMaterial(int bodyId, int subShapeId) {
        return PhysicsMaterial.at(JPC_BodyInterface_GetMaterial(handle, bodyId, subShapeId));
    }

    public void invalidateContactCache(int bodyId) {
        JPC_BodyInterface_InvalidateContactCache(handle, bodyId);
    }

    protected static final class F extends BodyInterface {
        private F(MemoryAddress address) {
            super(address);
        }

        @Override
        public void setPositionAndRotation(int bodyId, FVec3 position, Quat rotation, Activation activation) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_SetPositionAndRotation(handle, bodyId, position.address(), rotation.address(), activation.ordinal());
        }

        @Override
        public void setPositionAndRotation(int bodyId, DVec3 position, Quat rotation, Activation activation) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPositionAndRotationWhenChanged(int bodyId, FVec3 position, Quat rotation, Activation activation) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_SetPositionAndRotationWhenChanged(handle, bodyId, position.address(), rotation.address(), activation.ordinal());
        }

        @Override
        public void setPositionAndRotationWhenChanged(int bodyId, DVec3 position, Quat rotation, Activation activation) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getPositionAndRotation(int bodyId, FVec3 outPosition, Quat outRotation) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetPositionAndRotation(handle, bodyId, outPosition.address(), outRotation.address());
        }

        @Override
        public void getPositionAndRotation(int bodyId, DVec3 outPosition, Quat outRotation) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPosition(int bodyId, FVec3 position, Activation activation) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_SetPosition(handle, bodyId, position.address(), activation.ordinal());
        }

        @Override
        public void setPosition(int bodyId, DVec3 position, Activation activation) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassPosition(handle, bodyId, out.address());
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, DVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getWorldTransform(int bodyId, FMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_FLOAT, new float[3]);
                jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetWorldTransform(handle, bodyId, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_FLOAT));
            }
        }

        @Override
        public void getWorldTransform(int bodyId, DMat44 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getCenterOfMassTransform(int bodyId, FMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_FLOAT, new float[3]);
                jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassTransform(handle, bodyId, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_FLOAT));
            }
        }

        @Override
        public void getCenterOfMassTransform(int bodyId, DMat44 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void moveKinematic(int bodyId, FVec3 targetPosition, Quat targetRotation, float deltaTime) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_MoveKinematic(handle, bodyId, targetPosition.address(), targetRotation.address(), deltaTime);
        }

        @Override
        public void moveKinematic(int bodyId, DVec3 targetPosition, Quat targetRotation, float deltaTime) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void getPointVelocity(int bodyId, FVec3 point, FVec3 out) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_GetPointVelocity(handle, bodyId, point.address(), out.address());
        }

        @Override
        public void getPointVelocity(int bodyId, DVec3 point, FVec3 out) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPositionRotationAndVelocity(int bodyId, FVec3 position, FVec3 rotation, FVec3 linearVelocity, FVec3 angularVelocity) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_SetPositionRotationAndVelocity(handle, bodyId, position.address(), rotation.address(), linearVelocity.address(), angularVelocity.address());
        }

        @Override
        public void setPositionRotationAndVelocity(int bodyId, DVec3 position, FVec3 rotation, FVec3 linearVelocity, FVec3 angularVelocity) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void addForce(int bodyId, FVec3 force, FVec3 position) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_AddForceAtPosition(handle, bodyId, force.address(), position.address());
        }

        @Override
        public void addForce(int bodyId, FVec3 force, DVec3 position) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void addImpulse(int bodyId, FVec3 impulse, FVec3 position) {
            jolt.headers_f.JoltPhysicsC.JPC_BodyInterface_AddImpulseAtPosition(handle, bodyId, impulse.address(), position.address());
        }

        @Override
        public void addImpulse(int bodyId, FVec3 impulse, DVec3 position) {
            throw Jolt.tryingDoublePrecision();
        }
    }

    protected static final class D extends BodyInterface {
        private D(MemoryAddress address) {
            super(address);
        }

        @Override
        public void setPositionAndRotation(int bodyId, FVec3 position, Quat rotation, Activation activation) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPositionAndRotation(int bodyId, DVec3 position, Quat rotation, Activation activation) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_SetPositionAndRotation(handle, bodyId, position.address(), rotation.address(), activation.ordinal());
        }

        @Override
        public void setPositionAndRotationWhenChanged(int bodyId, FVec3 position, Quat rotation, Activation activation) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPositionAndRotationWhenChanged(int bodyId, DVec3 position, Quat rotation, Activation activation) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_SetPositionAndRotationWhenChanged(handle, bodyId, position.address(), rotation.address(), activation.ordinal());
        }

        @Override
        public void getPositionAndRotation(int bodyId, FVec3 outPosition, Quat outRotation) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPositionAndRotation(int bodyId, DVec3 outPosition, Quat outRotation) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetPositionAndRotation(handle, bodyId, outPosition.address(), outRotation.address());
        }

        @Override
        public void setPosition(int bodyId, FVec3 position, Activation activation) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPosition(int bodyId, DVec3 position, Activation activation) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_SetPosition(handle, bodyId, position.address(), activation.ordinal());
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getCenterOfMassPosition(int bodyId, DVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassPosition(handle, bodyId, out.address());
        }

        @Override
        public void getWorldTransform(int bodyId, FMat44 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getWorldTransform(int bodyId, DMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_DOUBLE, new double[3]);
                jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetWorldTransform(handle, bodyId, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_DOUBLE));
            }
        }

        @Override
        public void getCenterOfMassTransform(int bodyId, FMat44 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getCenterOfMassTransform(int bodyId, DMat44 out) {
            try (var arena = MemorySession.openConfined()) {
                var rotation = arena.allocateArray(C_FLOAT, new float[9]);
                var translation = arena.allocateArray(C_DOUBLE, new double[3]);
                jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetCenterOfMassTransform(handle, bodyId, rotation.address(), translation.address());
                out.read(rotation.toArray(C_FLOAT), translation.toArray(C_DOUBLE));
            }
        }

        @Override
        public void moveKinematic(int bodyId, FVec3 targetPosition, Quat targetRotation, float deltaTime) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void moveKinematic(int bodyId, DVec3 targetPosition, Quat targetRotation, float deltaTime) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_MoveKinematic(handle, bodyId, targetPosition.address(), targetRotation.address(), deltaTime);
        }

        @Override
        public void getPointVelocity(int bodyId, FVec3 point, FVec3 out) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void getPointVelocity(int bodyId, DVec3 point, FVec3 out) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_GetPointVelocity(handle, bodyId, point.address(), out.address());
        }

        @Override
        public void setPositionRotationAndVelocity(int bodyId, FVec3 position, FVec3 rotation, FVec3 linearVelocity, FVec3 angularVelocity) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPositionRotationAndVelocity(int bodyId, DVec3 position, FVec3 rotation, FVec3 linearVelocity, FVec3 angularVelocity) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_SetPositionRotationAndVelocity(handle, bodyId, position.address(), rotation.address(), linearVelocity.address(), angularVelocity.address());
        }

        @Override
        public void addForce(int bodyId, FVec3 force, FVec3 position) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void addForce(int bodyId, FVec3 force, DVec3 position) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_AddForceAtPosition(handle, bodyId, force.address(), position.address());
        }

        @Override
        public void addImpulse(int bodyId, FVec3 impulse, FVec3 position) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void addImpulse(int bodyId, FVec3 impulse, DVec3 position) {
            jolt.headers_d.JoltPhysicsC.JPC_BodyInterface_AddImpulseAtPosition(handle, bodyId, impulse.address(), position.address());
        }
    }
}
