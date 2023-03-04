package jolt.physics;

import jolt.SegmentedJoltNative;
import jolt.geometry.AABox;

import java.lang.foreign.*;

import static jolt.headers.JPC_PhysicsSettings.*;

public final class PhysicsSettings extends SegmentedJoltNative {
    //region Jolt-Value
    private PhysicsSettings(MemorySegment handle) {
        super(handle);
    }

    public static PhysicsSettings at(MemorySegment segment) {
        return new PhysicsSettings(segment);
    }

    public static PhysicsSettings at(MemorySession alloc, MemoryAddress addr) {
        return addr == MemoryAddress.NULL ? null : new PhysicsSettings(ofAddress(addr, alloc));
    }

    public static PhysicsSettings of(SegmentAllocator alloc) {
        return new PhysicsSettings(allocate(alloc));
    }
    //endregion Jolt-Value

    public int getMaxInFlightBodyPairs() {
        return max_in_flight_body_pairs$get(handle);
    }

    public void setMaxInFlightBodyPairs(int maxInFlightBodyPairs) {
        max_in_flight_body_pairs$set(handle, maxInFlightBodyPairs);
    }

    public int getStepListenersBatchSize() {
        return step_listeners_batch_size$get(handle);
    }

    public void setStepListenersBatchSize(int stepListenersBatchSize) {
        step_listeners_batch_size$set(handle, stepListenersBatchSize);
    }

    public int getStepListenerBatchesPerJob() {
        return step_listener_batches_per_job$get(handle);
    }

    public void setStepListenerBatchesPerJob(int stepListenerBatchesPerJob) {
        step_listener_batches_per_job$set(handle, stepListenerBatchesPerJob);
    }

    public float getBaumgarte() {
        return baumgarte$get(handle);
    }

    public void setBaumgarte(float baumgarte) {
        baumgarte$set(handle, baumgarte);
    }

    public float getSpeculativeContactDistance() {
        return speculative_contact_distance$get(handle);
    }

    public void setSpeculativeContactDistance(float speculativeContactDistance) {
        speculative_contact_distance$set(handle, speculativeContactDistance);
    }

    public float getPenetrationSlop() {
        return penetration_slop$get(handle);
    }

    public void setPenetrationSlop(float penetrationSlop) {
        penetration_slop$set(handle, penetrationSlop);
    }

    public float getLinearCastThreshold() {
        return linear_cast_threshold$get(handle);
    }

    public void setLinearCastThreshold(float linearCastThreshold) {
        linear_cast_threshold$set(handle, linearCastThreshold);
    }

    public float getLinearCastMaxPenetration() {
        return linear_cast_max_penetration$get(handle);
    }

    public void setLinearCastMaxPenetration(float linearCastMaxPenetration) {
        linear_cast_max_penetration$set(handle, linearCastMaxPenetration);
    }

    public float getManifoldToleranceSq() {
        return manifold_tolerance_sq$get(handle);
    }

    public void setManifoldToleranceSq(float manifoldToleranceSq) {
        manifold_tolerance_sq$set(handle, manifoldToleranceSq);
    }

    public float getMaxPenetrationDistance() {
        return max_penetration_distance$get(handle);
    }

    public void setMaxPenetrationDistance(float maxPenetrationDistance) {
        max_penetration_distance$set(handle, maxPenetrationDistance);
    }

    public float getBodyPairCacheMaxDeltaPositionSq() {
        return body_pair_cache_max_delta_position_sq$get(handle);
    }

    public void setBodyPairCacheMaxDeltaPositionSq(float bodyPairCacheMaxDeltaPositionSq) {
        body_pair_cache_max_delta_position_sq$set(handle, bodyPairCacheMaxDeltaPositionSq);
    }

    public float getBodyPairCacheCosMaxDeltaRotationDiv2() {
        return body_pair_cache_cos_max_delta_rotation_div_2$get(handle);
    }

    public void setBodyPairCacheCosMaxDeltaRotationDiv2(float bodyPairCacheCosMaxDeltaRotationDiv2) {
        body_pair_cache_cos_max_delta_rotation_div_2$set(handle, bodyPairCacheCosMaxDeltaRotationDiv2);
    }

    public float getContactNormalCosMaxDeltaRotation() {
        return contact_normal_cos_max_delta_rotation$get(handle);
    }

    public void setContactNormalCosMaxDeltaRotation(float contactNormalCosMaxDeltaRotation) {
        contact_normal_cos_max_delta_rotation$set(handle, contactNormalCosMaxDeltaRotation);
    }

    public float getContactPointPreserveLambdaMaxDistSq() {
        return contact_point_preserve_lambda_max_dist_sq$get(handle);
    }

    public void setContactPointPreserveLambdaMaxDistSq(float contactPointPreserveLambdaMaxDistSq) {
        contact_point_preserve_lambda_max_dist_sq$set(handle, contactPointPreserveLambdaMaxDistSq);
    }

    public int getNumVelocitySteps() {
        return num_velocity_steps$get(handle);
    }

    public void setNumVelocitySteps(int numVelocitySteps) {
        num_velocity_steps$set(handle, numVelocitySteps);
    }

    public int getNumPositionSteps() {
        return num_position_steps$get(handle);
    }

    public void setNumPositionSteps(int numPositionSteps) {
        num_position_steps$set(handle, numPositionSteps);
    }

    public float getMinVelocityForRestitution() {
        return min_velocity_for_restitution$get(handle);
    }

    public void setMinVelocityForRestitution(float minVelocityForRestitution) {
        min_velocity_for_restitution$set(handle, minVelocityForRestitution);
    }

    public float getTimeBeforeSleep() {
        return time_before_sleep$get(handle);
    }

    public void setTimeBeforeSleep(float timeBeforeSleep) {
        time_before_sleep$set(handle, timeBeforeSleep);
    }

    public float getPointVelocitySleepThreshold() {
        return point_velocity_sleep_threshold$get(handle);
    }

    public void setPointVelocitySleepThreshold(float pointVelocitySleepThreshold) {
        point_velocity_sleep_threshold$set(handle, pointVelocitySleepThreshold);
    }

    public boolean getConstraintWarmStart() {
        return constraint_warm_start$get(handle);
    }

    public void setConstraintWarmStart(boolean constraintWarmStart) {
        constraint_warm_start$set(handle, constraintWarmStart);
    }

    public boolean getUseBodyPairContactCache() {
        return use_body_pair_contact_cache$get(handle);
    }

    public void setUseBodyPairContactCache(boolean useBodyPairContactCache) {
        use_body_pair_contact_cache$set(handle, useBodyPairContactCache);
    }

    public boolean getUseManifoldReduction() {
        return use_manifold_reduction$get(handle);
    }

    public void setUseManifoldReduction(boolean useManifoldReduction) {
        use_manifold_reduction$set(handle, useManifoldReduction);
    }

    public boolean getAllowSleeping() {
        return allow_sleeping$get(handle);
    }

    public void setAllowSleeping(boolean allowSleeping) {
        allow_sleeping$set(handle, allowSleeping);
    }

    public boolean getCheckActiveEdges() {
        return check_active_edges$get(handle);
    }

    public void setCheckActiveEdges(boolean checkActiveEdges) {
        check_active_edges$set(handle, checkActiveEdges);
    }
}
