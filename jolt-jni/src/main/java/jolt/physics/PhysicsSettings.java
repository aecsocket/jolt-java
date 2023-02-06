package jolt.physics;

import jolt.JoltNative;
import jolt.jni.JniBindSelf;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/PhysicsSettings.h>")
@JniType("PhysicsSettings")
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
}
