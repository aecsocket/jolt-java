package jolt.physics;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/PhysicsSettings.h>")
@JniTypeMapping("PhysicsSettings")
public final class PhysicsSettings extends JoltNative {
    /**
     * How much padding to add around objects.
     */
    public static final float DEFAULT_CONVEX_RADIUS = 0.05f;

    /**
     * Maximum amount of jobs to allow.
     */
    public static final int MAX_PHYSICS_JOBS = 2048;

    /**
     * Maximum amount of barriers to allow.
     */
    public static final int MAX_PHYSICS_BARRIERS = 8;

    private PhysicsSettings(long address) { super(address); }
    public static PhysicsSettings ref(long address) { return address == 0 ? null : new PhysicsSettings(address); }

    /**
     * Size of body pairs array, corresponds to the maximum amount of potential body pairs that can be in flight at any time.
     * Setting this to a low value will use less memory but slow down simulation as threads may run out of narrow phase work.
     */
    public int getMaxInFlightBodyPairs() { return _getMaxInFlightBodyPairs(address); }
    @JniBindSelf("return self->mMaxInFlightBodyPairs;")
    private static native int _getMaxInFlightBodyPairs(long _a);
    /**
     * Size of body pairs array, corresponds to the maximum amount of potential body pairs that can be in flight at any time.
     * Setting this to a low value will use less memory but slow down simulation as threads may run out of narrow phase work.
     */
    public void setMaxInFlightBodyPairs(int value) { _setMaxInFlightBodyPairs(address, value); }
    @JniBindSelf("self->mMaxInFlightBodyPairs = value;")
    private static native void _setMaxInFlightBodyPairs(long _a, int value);


    /**
     * How many PhysicsStepListeners to notify in 1 batch.
     */
    public int getStepListenersBatchSize() { return _getStepListenersBatchSize(address); }
    @JniBindSelf("return self->mStepListenersBatchSize;")
    private static native int _getStepListenersBatchSize(long _a);

    /**
     * How many PhysicsStepListeners to notify in 1 batch.
     */
    public void setStepListenersBatchSize(int value) { _setStepListenersBatchSize(address, value); }
    @JniBindSelf("self->mStepListenersBatchSize = value;")
    private static native void _setStepListenersBatchSize(long _a, int value);


    /**
     * How many step listener batches are needed before spawning another job (set to INT_MAX if no parallelism is desired).
     */
    public int getStepListenerBatchesPerJob() { return _getStepListenerBatchesPerJob(address); }
    @JniBindSelf("return self->mStepListenerBatchesPerJob;")
    private static native int _getStepListenerBatchesPerJob(long _a);

    /**
     * How many step listener batches are needed before spawning another job (set to INT_MAX if no parallelism is desired).
     */
    public void setStepListenerBatchesPerJob(int value) { _setStepListenerBatchesPerJob(address, value); }
    @JniBindSelf("self->mStepListenerBatchesPerJob = value;")
    private static native void _setStepListenerBatchesPerJob(long _a, int value);


    /**
     * Baumgarte stabilization factor (how much of the position error to 'fix' in 1 update) (unit: dimensionless, 0 = nothing, 1 = 100%).
     */
    public float getBaumgarte() { return _getBaumgarte(address); }
    @JniBindSelf("return self->mBaumgarte;")
    private static native float _getBaumgarte(long _a);

    /**
     * Baumgarte stabilization factor (how much of the position error to 'fix' in 1 update) (unit: dimensionless, 0 = nothing, 1 = 100%).
     */
    public void setBaumgarte(float value) { _setBaumgarte(address, value); }
    @JniBindSelf("self->mBaumgarte = value;")
    private static native void _setBaumgarte(long _a, float value);


    /**
     * Radius around objects inside which speculative contact points will be detected. Note that if this is too big
     * you will get ghost collisions as speculative contacts are based on the closest points during the collision detection
     * step which may not be the actual closest points by the time the two objects hit (unit: meters).
     */
    public float getSpeculativeContactDistance() { return _getSpeculativeContactDistance(address); }
    @JniBindSelf("return self->mSpeculativeContactDistance;")
    private static native float _getSpeculativeContactDistance(long _a);

    /**
     * Radius around objects inside which speculative contact points will be detected. Note that if this is too big
     * you will get ghost collisions as speculative contacts are based on the closest points during the collision detection
     * step which may not be the actual closest points by the time the two objects hit (unit: meters).
     */
    public void setSpeculativeContactDistance(float value) { _setSpeculativeContactDistance(address, value); }
    @JniBindSelf("self->mSpeculativeContactDistance = value;")
    private static native void _setSpeculativeContactDistance(long _a, float value);


    /**
     * How much bodies are allowed to sink into each other (unit: meters).
     */
    public float getPenetrationSlop() { return _getPenetrationSlop(address); }
    @JniBindSelf("return self->mPenetrationSlop;")
    private static native float _getPenetrationSlop(long _a);

    /**
     * How much bodies are allowed to sink into each other (unit: meters).
     */
    public void setPenetrationSlop(float value) { _setPenetrationSlop(address, value); }
    @JniBindSelf("self->mPenetrationSlop = value;")
    private static native void _setPenetrationSlop(long _a, float value);


    /**
     * Fraction of its inner radius a body must move per step to enable casting for the LinearCast motion quality.
     */
    public float getLinearCastThreshold() { return _getLinearCastThreshold(address); }
    @JniBindSelf("return self->mLinearCastThreshold;")
    private static native float _getLinearCastThreshold(long _a);

    /**
     * Fraction of its inner radius a body must move per step to enable casting for the LinearCast motion quality.
     */
    public void setLinearCastThreshold(float value) { _setLinearCastThreshold(address, value); }
    @JniBindSelf("self->mLinearCastThreshold = value;")
    private static native void _setLinearCastThreshold(long _a, float value);


    /**
     * Fraction of its inner radius a body may penetrate another body for the LinearCast motion quality.
     */
    public float getLinearCastMaxPenetration() { return _getLinearCastMaxPenetration(address); }
    @JniBindSelf("return self->mLinearCastMaxPenetration;")
    private static native float _getLinearCastMaxPenetration(long _a);

    /**
     * Fraction of its inner radius a body may penetrate another body for the LinearCast motion quality.
     */
    public void setLinearCastMaxPenetration(float value) { _setLinearCastMaxPenetration(address, value); }
    @JniBindSelf("self->mLinearCastMaxPenetration = value;")
    private static native void _setLinearCastMaxPenetration(long _a, float value);


    /**
     * Max squared distance to use to determine if two points are on the same plane for determining the contact manifold between two shape faces (unit: meter^2).
     */
    public float getManifoldToleranceSq() { return _getManifoldToleranceSq(address); }
    @JniBindSelf("return self->mManifoldToleranceSq;")
    private static native float _getManifoldToleranceSq(long _a);

    /**
     * Max squared distance to use to determine if two points are on the same plane for determining the contact manifold between two shape faces (unit: meter^2).
     */
    public void setManifoldToleranceSq(float value) { _setManifoldToleranceSq(address, value); }
    @JniBindSelf("self->mManifoldToleranceSq = value;")
    private static native void _setManifoldToleranceSq(long _a, float value);


    /**
     * Maximum distance to correct in a single iteration when solving position constraints (unit: meters).
     */
    public float getMaxPenetrationDistance() { return _getMaxPenetrationDistance(address); }
    @JniBindSelf("return self->mMaxPenetrationDistance;")
    private static native float _getMaxPenetrationDistance(long _a);

    /**
     * Maximum distance to correct in a single iteration when solving position constraints (unit: meters).
     */
    public void setMaxPenetrationDistance(float value) { _setMaxPenetrationDistance(address, value); }
    @JniBindSelf("self->mMaxPenetrationDistance = value;")
    private static native void _setMaxPenetrationDistance(long _a, float value);


    /**
     * Maximum relative delta position for body pairs to be able to reuse collision results from last frame (units: meter^2).
     */
    public float getBodyPairCacheMaxDeltaPositionSq() { return _getBodyPairCacheMaxDeltaPositionSq(address); }
    @JniBindSelf("return self->mBodyPairCacheMaxDeltaPositionSq;")
    private static native float _getBodyPairCacheMaxDeltaPositionSq(long _a);

    /**
     * Maximum relative delta position for body pairs to be able to reuse collision results from last frame (units: meter^2).
     */
    public void setBodyPairCacheMaxDeltaPositionSq(float value) { _setBodyPairCacheMaxDeltaPositionSq(address, value); }
    @JniBindSelf("self->mBodyPairCacheMaxDeltaPositionSq = value;")
    private static native void _setBodyPairCacheMaxDeltaPositionSq(long _a, float value);


    /**
     * Maximum relative delta orientation for body pairs to be able to reuse collision results from last frame, stored as cos(max angle / 2).
     */
    public float getBodyPairCacheCosMaxDeltaRotationDiv2() { return _getBodyPairCacheCosMaxDeltaRotationDiv2(address); }
    @JniBindSelf("return self->mBodyPairCacheCosMaxDeltaRotationDiv2;")
    private static native float _getBodyPairCacheCosMaxDeltaRotationDiv2(long _a);

    /**
     * Maximum relative delta orientation for body pairs to be able to reuse collision results from last frame, stored as cos(max angle / 2).
     */
    public void setBodyPairCacheCosMaxDeltaRotationDiv2(float value) { _setBodyPairCacheCosMaxDeltaRotationDiv2(address, value); }
    @JniBindSelf("self->mBodyPairCacheCosMaxDeltaRotationDiv2 = value;")
    private static native void _setBodyPairCacheCosMaxDeltaRotationDiv2(long _a, float value);


    /**
     * Maximum angle between normals that allows manifolds between different sub shapes of the same body pair to be combined.
     */
    public float getContactNormalCosMaxDeltaRotation() { return _getContactNormalCosMaxDeltaRotation(address); }
    @JniBindSelf("return self->mContactNormalCosMaxDeltaRotation;")
    private static native float _getContactNormalCosMaxDeltaRotation(long _a);

    /**
     * Maximum angle between normals that allows manifolds between different sub shapes of the same body pair to be combined.
     */
    public void setContactNormalCosMaxDeltaRotation(float value) { _setContactNormalCosMaxDeltaRotation(address, value); }
    @JniBindSelf("self->mContactNormalCosMaxDeltaRotation = value;")
    private static native void _setContactNormalCosMaxDeltaRotation(long _a, float value);


    /**
     * Maximum allowed distance between old and new contact point to preserve contact forces for warm start (units: meter^2).
     */
    public float getContactPointPreserveLambdaMaxDistSq() { return _getContactPointPreserveLambdaMaxDistSq(address); }
    @JniBindSelf("return self->mContactPointPreserveLambdaMaxDistSq;")
    private static native float _getContactPointPreserveLambdaMaxDistSq(long _a);

    /**
     * Maximum allowed distance between old and new contact point to preserve contact forces for warm start (units: meter^2).
     */
    public void setContactPointPreserveLambdaMaxDistSq(float value) { _setContactPointPreserveLambdaMaxDistSq(address, value); }
    @JniBindSelf("self->mContactPointPreserveLambdaMaxDistSq = value;")
    private static native void _setContactPointPreserveLambdaMaxDistSq(long _a, float value);


    /**
     * Number of solver velocity iterations to run.
     * Note that this needs to be >= 2 in order for friction to work (friction is applied using the non-penetration impulse from the previous iteration).
     */
    public int getNumVelocitySteps() { return _getNumVelocitySteps(address); }
    @JniBindSelf("return self->mNumVelocitySteps;")
    private static native int _getNumVelocitySteps(long _a);

    /**
     * Number of solver velocity iterations to run.
     * Note that this needs to be >= 2 in order for friction to work (friction is applied using the non-penetration impulse from the previous iteration).
     */
    public void setNumVelocitySteps(int value) { _setNumVelocitySteps(address, value); }
    @JniBindSelf("self->mNumVelocitySteps = value;")
    private static native void _setNumVelocitySteps(long _a, int value);


    /**
     * Number of solver position iterations to run.
     */
    public int getNumPositionSteps() { return _getNumPositionSteps(address); }
    @JniBindSelf("return self->mNumPositionSteps;")
    private static native int _getNumPositionSteps(long _a);

    /**
     * Number of solver position iterations to run.
     */
    public void setNumPositionSteps(int value) { _setNumPositionSteps(address, value); }
    @JniBindSelf("self->mNumPositionSteps = value;")
    private static native void _setNumPositionSteps(long _a, int value);


    /**
     * Minimal velocity needed before a collision can be elastic (unit: m).
     */
    public float getMinVelocityForRestitution() { return _getMinVelocityForRestitution(address); }
    @JniBindSelf("return self->mMinVelocityForRestitution;")
    private static native float _getMinVelocityForRestitution(long _a);

    /**
     * Minimal velocity needed before a collision can be elastic (unit: m).
     */
    public void setMinVelocityForRestitution(float value) { _setMinVelocityForRestitution(address, value); }
    @JniBindSelf("self->mMinVelocityForRestitution = value;")
    private static native void _setMinVelocityForRestitution(long _a, float value);


    /**
     * Time before object is allowed to go to sleep (unit: seconds).
     */
    public float getTimeBeforeSleep() { return _getTimeBeforeSleep(address); }
    @JniBindSelf("return self->mTimeBeforeSleep;")
    private static native float _getTimeBeforeSleep(long _a);

    /**
     * Time before object is allowed to go to sleep (unit: seconds).
     */
    public void setTimeBeforeSleep(float value) { _setTimeBeforeSleep(address, value); }
    @JniBindSelf("self->mTimeBeforeSleep = value;")
    private static native void _setTimeBeforeSleep(long _a, float value);


    /**
     * Velocity of points on bounding box of object below which an object can be considered sleeping (unit: m/s).
     */
    public float getPointVelocitySleepThreshold() { return _getPointVelocitySleepThreshold(address); }
    @JniBindSelf("return self->mPointVelocitySleepThreshold;")
    private static native float _getPointVelocitySleepThreshold(long _a);

    /**
     * Velocity of points on bounding box of object below which an object can be considered sleeping (unit: m/s).
     */
    public void setPointVelocitySleepThreshold(float value) { _setPointVelocitySleepThreshold(address, value); }
    @JniBindSelf("self->mPointVelocitySleepThreshold = value;")
    private static native void _setPointVelocitySleepThreshold(long _a, float value);


    /**
     * Whether or not to use warm starting for constraints (initially applying previous frames impulses).
     */
    public boolean getConstraintWarmStart() { return _getConstraintWarmStart(address); }
    @JniBindSelf("return self->mConstraintWarmStart;")
    private static native boolean _getConstraintWarmStart(long _a);

    /**
     * Whether or not to use warm starting for constraints (initially applying previous frames impulses).
     */
    public void setConstraintWarmStart(boolean value) { _setConstraintWarmStart(address, value); }
    @JniBindSelf("self->mConstraintWarmStart = value;")
    private static native void _setConstraintWarmStart(long _a, boolean value);


    /**
     * Whether or not to use the body pair cache, which removes the need for narrow phase collision detection
     * when orientation between two bodies didn't change.
     */
    public boolean getUseBodyPairContactCache() { return _getUseBodyPairContactCache(address); }
    @JniBindSelf("return self->mUseBodyPairContactCache;")
    private static native boolean _getUseBodyPairContactCache(long _a);

    /**
     * Whether or not to use the body pair cache, which removes the need for narrow phase collision detection
     * when orientation between two bodies didn't change.
     */
    public void setUseBodyPairContactCache(boolean value) { _setUseBodyPairContactCache(address, value); }
    @JniBindSelf("self->mUseBodyPairContactCache = value;")
    private static native void _setUseBodyPairContactCache(long _a, boolean value);


    /**
     * Whether or not to reduce manifolds with similar contact normals into one contact manifold.
     */
    public boolean getUseManifoldReduction() { return _getUseManifoldReduction(address); }
    @JniBindSelf("return self->mUseManifoldReduction;")
    private static native boolean _getUseManifoldReduction(long _a);

    /**
     * Whether or not to reduce manifolds with similar contact normals into one contact manifold.
     */
    public void setUseManifoldReduction(boolean value) { _setUseManifoldReduction(address, value); }
    @JniBindSelf("self->mUseManifoldReduction = value;")
    private static native void _setUseManifoldReduction(long _a, boolean value);


    /**
     * If objects can go to sleep or not.
     */
    public boolean getAllowSleeping() { return _getAllowSleeping(address); }
    @JniBindSelf("return self->mAllowSleeping;")
    private static native boolean _getAllowSleeping(long _a);

    /**
     * If objects can go to sleep or not.
     */
    public void setAllowSleeping(boolean value) { _setAllowSleeping(address, value); }
    @JniBindSelf("self->mAllowSleeping = value;")
    private static native void _setAllowSleeping(long _a, boolean value);


    /**
     * When false, we prevent collision against non-active (shared) edges. Mainly for debugging the algorithm.
     */
    public boolean getCheckActiveEdges() { return _getCheckActiveEdges(address); }
    @JniBindSelf("return self->mCheckActiveEdges;")
    private static native boolean _getCheckActiveEdges(long _a);

    /**
     * When false, we prevent collision against non-active (shared) edges. Mainly for debugging the algorithm.
     */
    public void setCheckActiveEdges(boolean value) { _setCheckActiveEdges(address, value); }
    @JniBindSelf("self->mCheckActiveEdges = value;")
    private static native void _setCheckActiveEdges(long _a, boolean value);
}
