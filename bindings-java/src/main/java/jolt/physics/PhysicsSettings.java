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
}
