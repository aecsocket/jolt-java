package jolt.physics;

import io.github.aecsocket.jniglue.JniCallback;
import io.github.aecsocket.jniglue.JniHeader;
import io.github.aecsocket.jniglue.JniNative;
import io.github.aecsocket.jniglue.JniReferenced;
import jolt.JoltEnvironment;

@JniNative(JoltEnvironment.JNI_MODEL)
@JniReferenced
@JniHeader("""
        void ToJava(JNIEnv* env, const PhysicsSettings from, jobject to) {
            JNI_PhysicsSettings_set(env, to,
                from.mMaxInFlightBodyPairs,
                from.mStepListenersBatchSize,
                from.mStepListenerBatchesPerJob,
                from.mBaumgarte,
                from.mSpeculativeContactDistance,
                from.mPenetrationSlop,
                from.mLinearCastThreshold,
                from.mLinearCastMaxPenetration,
                from.mManifoldToleranceSq,
                from.mMaxPenetrationDistance,
                from.mBodyPairCacheMaxDeltaPositionSq,
                from.mBodyPairCacheCosMaxDeltaRotationDiv2,
                from.mContactNormalCosMaxDeltaRotation,
                from.mContactPointPreserveLambdaMaxDistSq,
                from.mNumVelocitySteps,
                from.mNumPositionSteps,
                from.mMinVelocityForRestitution,
                from.mTimeBeforeSleep,
                from.mPointVelocitySleepThreshold,
                from.mConstraintWarmStart,
                from.mUseBodyPairContactCache,
                from.mUseManifoldReduction,
                from.mAllowSleeping,
                from.mCheckActiveEdges);
        }""")
public final class PhysicsSettings {
    public static final float DEFAULT_COLLISION_TOLERANCE = 1.0e-4f;

    public static final float DEFAULT_PENETRATION_TOLERANCE = 1.0e-4f;

    public static final float DEFAULT_CONVEX_RADIUS = 0.05f;

    public static final float CAPSULE_PROJECTION_SLOP = 0.02f;

    public static final int MAX_PHYSICS_JOBS = 2048;

    public static final int MAX_PHYSICS_BARRIERS = 8;

    public int maxInFlightBodyPairs = 16384;
    public int stepListenersBatchSize = 8;
    public int stepListenerBatchesPerJob = 1;
    public float baumgarte = 0.2f;
    public float speculativeContactDistance = 0.02f;
    public float penetrationSlop = 0.02f;
    public float linearCastThreshold = 0.75f;
    public float linearCastMaxPenetration = 0.25f;
    public float manifoldToleranceSq = 1.0e-6f;
    public float maxPenetrationDistance = 0.2f;
    public float bodyPairCacheMaxDeltaPositionSq = 0.001f * 0.001f;
    public float bodyPairCacheCosMaxDeltaRotationDiv2 = (float) Math.cos(Math.toRadians(2.0) / 2.0);
    public float contactNormalCosMaxDeltaRotation = (float) Math.cos(Math.toRadians(5.0));
    public float contactPointPreserveLambdaMaxDistSq = 0.01f * 0.01f;
    public int numVelocitySteps = 10;
    public int numPositionSteps = 2;
    public float minVelocityForRestitution = 1.0f;
    public float timeBeforeSleep = 0.5f;
    public float pointVelocitySleepThreshold = 0.03f;
    public boolean constraintWarmStart = true;
    public boolean useBodyPairContactCache = true;
    public boolean useManifoldReduction = true;
    public boolean allowSleeping = true;
    public boolean checkActiveEdges = true;

    @JniCallback
    public void set(int maxInFlightBodyPairs, int stepListenersBatchSize, int stepListenerBatchesPerJob, float baumgarte, float speculativeContactDistance, float penetrationSlop, float linearCastThreshold, float linearCastMaxPenetration, float manifoldToleranceSq, float maxPenetrationDistance, float bodyPairCacheMaxDeltaPositionSq, float bodyPairCacheCosMaxDeltaRotationDiv2, float contactNormalCosMaxDeltaRotation, float contactPointPreserveLambdaMaxDistSq, int numVelocitySteps, int numPositionSteps, float minVelocityForRestitution, float timeBeforeSleep, float pointVelocitySleepThreshold, boolean constraintWarmStart, boolean useBodyPairContactCache, boolean useManifoldReduction, boolean allowSleeping, boolean checkActiveEdges) {
        this.maxInFlightBodyPairs = maxInFlightBodyPairs;
        this.stepListenersBatchSize = stepListenersBatchSize;
        this.stepListenerBatchesPerJob = stepListenerBatchesPerJob;
        this.baumgarte = baumgarte;
        this.speculativeContactDistance = speculativeContactDistance;
        this.penetrationSlop = penetrationSlop;
        this.linearCastThreshold = linearCastThreshold;
        this.linearCastMaxPenetration = linearCastMaxPenetration;
        this.manifoldToleranceSq = manifoldToleranceSq;
        this.maxPenetrationDistance = maxPenetrationDistance;
        this.bodyPairCacheMaxDeltaPositionSq = bodyPairCacheMaxDeltaPositionSq;
        this.bodyPairCacheCosMaxDeltaRotationDiv2 = bodyPairCacheCosMaxDeltaRotationDiv2;
        this.contactNormalCosMaxDeltaRotation = contactNormalCosMaxDeltaRotation;
        this.contactPointPreserveLambdaMaxDistSq = contactPointPreserveLambdaMaxDistSq;
        this.numVelocitySteps = numVelocitySteps;
        this.numPositionSteps = numPositionSteps;
        this.minVelocityForRestitution = minVelocityForRestitution;
        this.timeBeforeSleep = timeBeforeSleep;
        this.pointVelocitySleepThreshold = pointVelocitySleepThreshold;
        this.constraintWarmStart = constraintWarmStart;
        this.useBodyPairContactCache = useBodyPairContactCache;
        this.useManifoldReduction = useManifoldReduction;
        this.allowSleeping = allowSleeping;
        this.checkActiveEdges = checkActiveEdges;
    }

    public void set(PhysicsSettings s) {
        maxInFlightBodyPairs = s.maxInFlightBodyPairs;
        stepListenersBatchSize = s.stepListenersBatchSize;
        stepListenerBatchesPerJob = s.stepListenerBatchesPerJob;
        baumgarte = s.baumgarte;
        speculativeContactDistance = s.speculativeContactDistance;
        penetrationSlop = s.penetrationSlop;
        linearCastThreshold = s.linearCastThreshold;
        linearCastMaxPenetration = s.linearCastMaxPenetration;
        manifoldToleranceSq = s.manifoldToleranceSq;
        maxPenetrationDistance = s.maxPenetrationDistance;
        bodyPairCacheMaxDeltaPositionSq = s.bodyPairCacheMaxDeltaPositionSq;
        bodyPairCacheCosMaxDeltaRotationDiv2 = s.bodyPairCacheCosMaxDeltaRotationDiv2;
        contactNormalCosMaxDeltaRotation = s.contactNormalCosMaxDeltaRotation;
        contactPointPreserveLambdaMaxDistSq = s.contactPointPreserveLambdaMaxDistSq;
        numVelocitySteps = s.numVelocitySteps;
        numPositionSteps = s.numPositionSteps;
        minVelocityForRestitution = s.minVelocityForRestitution;
        timeBeforeSleep = s.timeBeforeSleep;
        pointVelocitySleepThreshold = s.pointVelocitySleepThreshold;
        constraintWarmStart = s.constraintWarmStart;
        useBodyPairContactCache = s.useBodyPairContactCache;
        useManifoldReduction = s.useManifoldReduction;
        allowSleeping = s.allowSleeping;
        checkActiveEdges = s.checkActiveEdges;
    }
}
