package jolt.physics.body;

import jolt.Jolt;
import jolt.SegmentedJoltNative;
import jolt.math.DVec3;
import jolt.math.FVec3;
import jolt.math.Quat;
import jolt.physics.collision.CollisionGroup;
import jolt.physics.collision.shape.Shape;
import jolt.physics.collision.shape.ShapeResult;
import jolt.physics.collision.shape.ShapeSettings;

import javax.annotation.Nullable;
import java.lang.foreign.*;

import static jolt.headers.JoltPhysicsC.*;

public abstract sealed class BodyCreationSettings extends SegmentedJoltNative
        permits BodyCreationSettings.F, BodyCreationSettings.D {
    //region Jolt-Value-FD
    private BodyCreationSettings(MemorySegment handle) {
        super(handle);
    }

    public static BodyCreationSettings at(MemorySegment segment) {
        return Jolt.doublePrecision() ? new D(segment) : new F(segment);
    }

    public static BodyCreationSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : Jolt.doublePrecision()
                ? new D(jolt.headers_d.JPC_BodyCreationSettings.ofAddress(addr, alloc))
                : new F(jolt.headers_f.JPC_BodyCreationSettings.ofAddress(addr, alloc));
    }

    public static BodyCreationSettings of(SegmentAllocator alloc) {
        return Jolt.doublePrecision()
                ? new D(jolt.headers_d.JPC_BodyCreationSettings.allocate(alloc))
                : new F(jolt.headers_f.JPC_BodyCreationSettings.allocate(alloc));
    }
    //endregion Jolt-Value-FD

    public static BodyCreationSettings of(
            SegmentAllocator alloc,
            Shape shape,
            FVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        Jolt.assertSinglePrecision();
        var segment = jolt.headers_f.JPC_BodyCreationSettings.allocate(alloc);
        jolt.headers_f.JoltPhysicsC.JPC_BodyCreationSettings_Set(
                segment,
                shape.address(), position.address(), rotation.address(),
                (byte) motionType.ordinal(),
                layer
        );
        return new BodyCreationSettings.F(segment);
    }

    public static BodyCreationSettings of(
            SegmentAllocator alloc,
            Shape shape,
            DVec3 position,
            Quat rotation,
            MotionType motionType,
            short layer
    ) {
        Jolt.assertDoublePrecision();
        var segment = jolt.headers_d.JPC_BodyCreationSettings.allocate(alloc);
        jolt.headers_d.JoltPhysicsC.JPC_BodyCreationSettings_Set(
                segment,
                shape.address(), position.address(), rotation.address(),
                (byte) motionType.ordinal(),
                layer
        );
        return new BodyCreationSettings.D(segment);
    }

    public @Nullable ShapeSettings getShapeSettings() {
        return ShapeSettings.at(JPC_BodyCreationSettings_GetShapeSettings(handle));
    }

    public void setShapeSettings(ShapeSettings shape) {
        JPC_BodyCreationSettings_SetShapeSettings(handle, shape.address());
    }

    public ShapeResult convertShapeSettings(SegmentAllocator alloc) {
        return ShapeResult.at(JPC_BodyCreationSettings_ConvertShapeSettings(alloc, handle));
    }

    public @Nullable Shape getShape() {
        return Shape.at(JPC_BodyCreationSettings_GetShape(handle));
    }

    public void setShape(Shape shape) {
        JPC_BodyCreationSettings_SetShape(handle, shape.address());
    }

    public boolean hasMassProperties() {
        return JPC_BodyCreationSettings_HasMassProperties(handle);
    }

    public void getMassProperties(MassProperties out) {
        JPC_BodyCreationSettings_GetMassProperties(handle, out.address());
    }

    public abstract FVec3 getPositionF();

    public abstract void setPosition(FVec3 position);

    public abstract DVec3 getPositionD();

    public abstract void setPosition(DVec3 position);

    public abstract Quat getRotation();

    public abstract void setRotation(Quat rotation);

    public abstract FVec3 getLinearVelocity();

    public abstract void setLinearVelocity(FVec3 linearVelocity);

    public abstract FVec3 getAngularVelocity();

    public abstract void setAngularVelocity(FVec3 angularVelocity);

    public abstract long getUserData();

    public abstract void setUserData(long userData);

    public abstract short getObjectLayer();

    public abstract void setObjectLayer(short objectLayer);

    public abstract CollisionGroup getCollisionGroup();

    public abstract MotionType getMotionType();

    public abstract void setMotionType(MotionType motionType);

    public abstract boolean getAllowDynamicOrKinematic();

    public abstract void setAllowDynamicOrKinematic(boolean allowDynamicOrKinematic);

    public abstract boolean getIsSensor();

    public abstract void setIsSensor(boolean isSensor);

    public abstract boolean getUseManifoldReduction();

    public abstract void setUseManifoldReduction(boolean useManifoldReduction);

    public abstract MotionQuality getMotionQuality();

    public abstract void setMotionQuality(MotionQuality motionQuality);

    public abstract boolean getAllowSleeping();

    public abstract void setAllowSleeping(boolean allowSleeping);

    public abstract float getFriction();

    public abstract void setFriction(float friction);

    public abstract float getRestitution();

    public abstract void setRestitution(float restitution);

    public abstract float getLinearDamping();

    public abstract void setLinearDamping(float linearDamping);

    public abstract float getAngularDamping();

    public abstract void setAngularDamping(float angularDamping);

    public abstract float getMaxLinearVelocity();

    public abstract void setMaxLinearVelocity(float maxLinearVelocity);

    public abstract float getMaxAngularVelocity();

    public abstract void setMaxAngularVelocity(float maxAngularVelocity);

    public abstract float getGravityFactor();

    public abstract void setGravityFactor(float gravityFactor);

    public abstract OverrideMassProperties getOverrideMassProperties();

    public abstract void setOverrideMassProperties(OverrideMassProperties overrideMassProperties);

    public abstract float getInertiaMultiplier();

    public abstract void setInertiaMultiplier(float inertiaMultiplier);

    public abstract MassProperties getMassPropertiesOverride();

    public abstract void setMassPropertiesOverride(MassProperties massPropertiesOverride);

    protected static final class F extends BodyCreationSettings {
        private F(MemorySegment handle) {
            super(handle);
        }

        @Override
        public FVec3 getPositionF() {
            return FVec3.at(jolt.headers_f.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public void setPosition(FVec3 position) {
            position.write(jolt.headers_f.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public DVec3 getPositionD() {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public void setPosition(DVec3 position) {
            throw Jolt.tryingDoublePrecision();
        }

        @Override
        public Quat getRotation() {
            return Quat.at(jolt.headers_f.JPC_BodyCreationSettings.rotation$slice(handle));
        }

        @Override
        public void setRotation(Quat rotation) {
            rotation.write(jolt.headers_f.JPC_BodyCreationSettings.rotation$slice(handle));
        }

        @Override
        public FVec3 getLinearVelocity() {
            return FVec3.at(jolt.headers_f.JPC_BodyCreationSettings.linear_velocity$slice(handle));
        }

        @Override
        public void setLinearVelocity(FVec3 linearVelocity) {
            linearVelocity.write(jolt.headers_f.JPC_BodyCreationSettings.linear_velocity$slice(handle));
        }

        @Override
        public FVec3 getAngularVelocity() {
            return FVec3.at(jolt.headers_f.JPC_BodyCreationSettings.angular_velocity$slice(handle));
        }

        @Override
        public void setAngularVelocity(FVec3 angularVelocity) {
            angularVelocity.write(jolt.headers_f.JPC_BodyCreationSettings.angular_velocity$slice(handle));
        }

        @Override
        public long getUserData() {
            return jolt.headers_f.JPC_BodyCreationSettings.user_data$get(handle);
        }

        @Override
        public void setUserData(long userData) {
            jolt.headers_f.JPC_BodyCreationSettings.user_data$set(handle, userData);
        }

        @Override
        public short getObjectLayer() {
            return jolt.headers_f.JPC_BodyCreationSettings.object_layer$get(handle);
        }

        @Override
        public void setObjectLayer(short objectLayer) {
            jolt.headers_f.JPC_BodyCreationSettings.object_layer$set(handle, objectLayer);
        }

        @Override
        public CollisionGroup getCollisionGroup() {
            return CollisionGroup.at(jolt.headers_f.JPC_BodyCreationSettings.collision_group$slice(handle));
        }

        @Override
        public MotionType getMotionType() {
            return MotionType.values()[jolt.headers_f.JPC_BodyCreationSettings.motion_type$get(handle)];
        }

        @Override
        public void setMotionType(MotionType motionType) {
            jolt.headers_f.JPC_BodyCreationSettings.motion_type$set(handle, (byte) motionType.ordinal());
        }

        @Override
        public boolean getAllowDynamicOrKinematic() {
            return jolt.headers_f.JPC_BodyCreationSettings.allow_dynamic_or_kinematic$get(handle);
        }

        @Override
        public void setAllowDynamicOrKinematic(boolean allowDynamicOrKinematic) {
            jolt.headers_f.JPC_BodyCreationSettings.allow_dynamic_or_kinematic$set(handle, allowDynamicOrKinematic);
        }

        @Override
        public boolean getIsSensor() {
            return jolt.headers_f.JPC_BodyCreationSettings.is_sensor$get(handle);
        }

        @Override
        public void setIsSensor(boolean isSensor) {
            jolt.headers_f.JPC_BodyCreationSettings.is_sensor$set(handle, isSensor);
        }

        @Override
        public boolean getUseManifoldReduction() {
            return jolt.headers_f.JPC_BodyCreationSettings.use_manifold_reduction$get(handle);
        }

        @Override
        public void setUseManifoldReduction(boolean useManifoldReduction) {
            jolt.headers_f.JPC_BodyCreationSettings.use_manifold_reduction$set(handle, useManifoldReduction);
        }

        @Override
        public MotionQuality getMotionQuality() {
            return MotionQuality.values()[jolt.headers_f.JPC_BodyCreationSettings.motion_quality$get(handle)];
        }

        @Override
        public void setMotionQuality(MotionQuality motionQuality) {
            jolt.headers_f.JPC_BodyCreationSettings.motion_quality$get(handle, motionQuality.ordinal());
        }

        @Override
        public boolean getAllowSleeping() {
            return jolt.headers_f.JPC_BodyCreationSettings.allow_sleeping$get(handle);
        }

        @Override
        public void setAllowSleeping(boolean allowSleeping) {
            jolt.headers_f.JPC_BodyCreationSettings.allow_sleeping$set(handle, allowSleeping);
        }

        @Override
        public float getFriction() {
            return jolt.headers_f.JPC_BodyCreationSettings.friction$get(handle);
        }

        @Override
        public void setFriction(float friction) {
            jolt.headers_f.JPC_BodyCreationSettings.friction$set(handle, friction);
        }

        @Override
        public float getRestitution() {
            return jolt.headers_f.JPC_BodyCreationSettings.restitution$get(handle);
        }

        @Override
        public void setRestitution(float restitution) {
            jolt.headers_f.JPC_BodyCreationSettings.restitution$set(handle, restitution);
        }

        @Override
        public float getLinearDamping() {
            return jolt.headers_f.JPC_BodyCreationSettings.linear_damping$get(handle);
        }

        @Override
        public void setLinearDamping(float linearDamping) {
            jolt.headers_f.JPC_BodyCreationSettings.linear_damping$set(handle, linearDamping);
        }

        @Override
        public float getAngularDamping() {
            return jolt.headers_f.JPC_BodyCreationSettings.angular_damping$get(handle);
        }

        @Override
        public void setAngularDamping(float angularDamping) {
            jolt.headers_f.JPC_BodyCreationSettings.angular_damping$set(handle, angularDamping);
        }

        @Override
        public float getMaxLinearVelocity() {
            return jolt.headers_f.JPC_BodyCreationSettings.max_linear_velocity$get(handle);
        }

        @Override
        public void setMaxLinearVelocity(float maxLinearVelocity) {
            jolt.headers_f.JPC_BodyCreationSettings.max_linear_velocity$set(handle, maxLinearVelocity);
        }

        @Override
        public float getMaxAngularVelocity() {
            return jolt.headers_f.JPC_BodyCreationSettings.max_angular_velocity$get(handle);
        }

        @Override
        public void setMaxAngularVelocity(float maxAngularVelocity) {
            jolt.headers_f.JPC_BodyCreationSettings.max_angular_velocity$set(handle, maxAngularVelocity);
        }

        @Override
        public float getGravityFactor() {
            return jolt.headers_f.JPC_BodyCreationSettings.gravity_factor$get(handle);
        }

        @Override
        public void setGravityFactor(float gravityFactor) {
            jolt.headers_f.JPC_BodyCreationSettings.gravity_factor$set(handle, gravityFactor);
        }

        @Override
        public OverrideMassProperties getOverrideMassProperties() {
            return OverrideMassProperties.values()[jolt.headers_f.JPC_BodyCreationSettings.override_mass_properties$get(handle)];
        }

        @Override
        public void setOverrideMassProperties(OverrideMassProperties overrideMassProperties) {
            jolt.headers_f.JPC_BodyCreationSettings.override_mass_properties$set(handle, (byte) overrideMassProperties.ordinal());
        }

        @Override
        public float getInertiaMultiplier() {
            return jolt.headers_f.JPC_BodyCreationSettings.inertia_multiplier$get(handle);
        }

        @Override
        public void setInertiaMultiplier(float inertiaMultiplier) {
            jolt.headers_f.JPC_BodyCreationSettings.inertia_multiplier$set(handle, inertiaMultiplier);
        }

        @Override
        public MassProperties getMassPropertiesOverride() {
            return MassProperties.at(jolt.headers_f.JPC_BodyCreationSettings.mass_properties_override$slice(handle));
        }

        @Override
        public void setMassPropertiesOverride(MassProperties massPropertiesOverride) {
            massPropertiesOverride.write(jolt.headers_f.JPC_BodyCreationSettings.mass_properties_override$slice(handle));
        }
    }

    static final class D extends BodyCreationSettings {
        private D(MemorySegment handle) {
            super(handle);
        }

        @Override
        public FVec3 getPositionF() {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public void setPosition(FVec3 position) {
            throw Jolt.tryingSinglePrecision();
        }

        @Override
        public DVec3 getPositionD() {
            return DVec3.at(jolt.headers_d.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public void setPosition(DVec3 position) {
            position.write(jolt.headers_d.JPC_BodyCreationSettings.position$slice(handle));
        }

        @Override
        public Quat getRotation() {
            return Quat.at(jolt.headers_d.JPC_BodyCreationSettings.rotation$slice(handle));
        }

        @Override
        public void setRotation(Quat rotation) {
            rotation.write(jolt.headers_d.JPC_BodyCreationSettings.rotation$slice(handle));
        }

        @Override
        public FVec3 getLinearVelocity() {
            return FVec3.at(jolt.headers_d.JPC_BodyCreationSettings.linear_velocity$slice(handle));
        }

        @Override
        public void setLinearVelocity(FVec3 linearVelocity) {
            linearVelocity.write(jolt.headers_d.JPC_BodyCreationSettings.linear_velocity$slice(handle));
        }

        @Override
        public FVec3 getAngularVelocity() {
            return FVec3.at(jolt.headers_d.JPC_BodyCreationSettings.angular_velocity$slice(handle));
        }

        @Override
        public void setAngularVelocity(FVec3 angularVelocity) {
            angularVelocity.write(jolt.headers_d.JPC_BodyCreationSettings.angular_velocity$slice(handle));
        }

        @Override
        public long getUserData() {
            return jolt.headers_d.JPC_BodyCreationSettings.user_data$get(handle);
        }

        @Override
        public void setUserData(long userData) {
            jolt.headers_d.JPC_BodyCreationSettings.user_data$set(handle, userData);
        }

        @Override
        public short getObjectLayer() {
            return jolt.headers_d.JPC_BodyCreationSettings.object_layer$get(handle);
        }

        @Override
        public void setObjectLayer(short objectLayer) {
            jolt.headers_d.JPC_BodyCreationSettings.object_layer$set(handle, objectLayer);
        }

        @Override
        public CollisionGroup getCollisionGroup() {
            return CollisionGroup.at(jolt.headers_d.JPC_BodyCreationSettings.collision_group$slice(handle));
        }

        @Override
        public MotionType getMotionType() {
            return MotionType.values()[jolt.headers_d.JPC_BodyCreationSettings.motion_type$get(handle)];
        }

        @Override
        public void setMotionType(MotionType motionType) {
            jolt.headers_d.JPC_BodyCreationSettings.motion_type$set(handle, (byte) motionType.ordinal());
        }

        @Override
        public boolean getAllowDynamicOrKinematic() {
            return jolt.headers_d.JPC_BodyCreationSettings.allow_dynamic_or_kinematic$get(handle);
        }

        @Override
        public void setAllowDynamicOrKinematic(boolean allowDynamicOrKinematic) {
            jolt.headers_d.JPC_BodyCreationSettings.allow_dynamic_or_kinematic$set(handle, allowDynamicOrKinematic);
        }

        @Override
        public boolean getIsSensor() {
            return jolt.headers_d.JPC_BodyCreationSettings.is_sensor$get(handle);
        }

        @Override
        public void setIsSensor(boolean isSensor) {
            jolt.headers_d.JPC_BodyCreationSettings.is_sensor$set(handle, isSensor);
        }

        @Override
        public boolean getUseManifoldReduction() {
            return jolt.headers_d.JPC_BodyCreationSettings.use_manifold_reduction$get(handle);
        }

        @Override
        public void setUseManifoldReduction(boolean useManifoldReduction) {
            jolt.headers_d.JPC_BodyCreationSettings.use_manifold_reduction$set(handle, useManifoldReduction);
        }

        @Override
        public MotionQuality getMotionQuality() {
            return MotionQuality.values()[jolt.headers_d.JPC_BodyCreationSettings.motion_quality$get(handle)];
        }

        @Override
        public void setMotionQuality(MotionQuality motionQuality) {
            jolt.headers_d.JPC_BodyCreationSettings.motion_quality$get(handle, motionQuality.ordinal());
        }

        @Override
        public boolean getAllowSleeping() {
            return jolt.headers_d.JPC_BodyCreationSettings.allow_sleeping$get(handle);
        }

        @Override
        public void setAllowSleeping(boolean allowSleeping) {
            jolt.headers_d.JPC_BodyCreationSettings.allow_sleeping$set(handle, allowSleeping);
        }

        @Override
        public float getFriction() {
            return jolt.headers_d.JPC_BodyCreationSettings.friction$get(handle);
        }

        @Override
        public void setFriction(float friction) {
            jolt.headers_d.JPC_BodyCreationSettings.friction$set(handle, friction);
        }

        @Override
        public float getRestitution() {
            return jolt.headers_d.JPC_BodyCreationSettings.restitution$get(handle);
        }

        @Override
        public void setRestitution(float restitution) {
            jolt.headers_d.JPC_BodyCreationSettings.restitution$set(handle, restitution);
        }

        @Override
        public float getLinearDamping() {
            return jolt.headers_d.JPC_BodyCreationSettings.linear_damping$get(handle);
        }

        @Override
        public void setLinearDamping(float linearDamping) {
            jolt.headers_d.JPC_BodyCreationSettings.linear_damping$set(handle, linearDamping);
        }

        @Override
        public float getAngularDamping() {
            return jolt.headers_d.JPC_BodyCreationSettings.angular_damping$get(handle);
        }

        @Override
        public void setAngularDamping(float angularDamping) {
            jolt.headers_d.JPC_BodyCreationSettings.angular_damping$set(handle, angularDamping);
        }

        @Override
        public float getMaxLinearVelocity() {
            return jolt.headers_d.JPC_BodyCreationSettings.max_linear_velocity$get(handle);
        }

        @Override
        public void setMaxLinearVelocity(float maxLinearVelocity) {
            jolt.headers_d.JPC_BodyCreationSettings.max_linear_velocity$set(handle, maxLinearVelocity);
        }

        @Override
        public float getMaxAngularVelocity() {
            return jolt.headers_d.JPC_BodyCreationSettings.max_angular_velocity$get(handle);
        }

        @Override
        public void setMaxAngularVelocity(float maxAngularVelocity) {
            jolt.headers_d.JPC_BodyCreationSettings.max_angular_velocity$set(handle, maxAngularVelocity);
        }

        @Override
        public float getGravityFactor() {
            return jolt.headers_d.JPC_BodyCreationSettings.gravity_factor$get(handle);
        }

        @Override
        public void setGravityFactor(float gravityFactor) {
            jolt.headers_d.JPC_BodyCreationSettings.gravity_factor$set(handle, gravityFactor);
        }

        @Override
        public OverrideMassProperties getOverrideMassProperties() {
            return OverrideMassProperties.values()[jolt.headers_d.JPC_BodyCreationSettings.override_mass_properties$get(handle)];
        }

        @Override
        public void setOverrideMassProperties(OverrideMassProperties overrideMassProperties) {
            jolt.headers_d.JPC_BodyCreationSettings.override_mass_properties$set(handle, (byte) overrideMassProperties.ordinal());
        }

        @Override
        public float getInertiaMultiplier() {
            return jolt.headers_d.JPC_BodyCreationSettings.inertia_multiplier$get(handle);
        }

        @Override
        public void setInertiaMultiplier(float inertiaMultiplier) {
            jolt.headers_d.JPC_BodyCreationSettings.inertia_multiplier$set(handle, inertiaMultiplier);
        }

        @Override
        public MassProperties getMassPropertiesOverride() {
            return MassProperties.at(jolt.headers_d.JPC_BodyCreationSettings.mass_properties_override$slice(handle));
        }

        @Override
        public void setMassPropertiesOverride(MassProperties massPropertiesOverride) {
            massPropertiesOverride.write(jolt.headers_d.JPC_BodyCreationSettings.mass_properties_override$slice(handle));
        }
    }
}
