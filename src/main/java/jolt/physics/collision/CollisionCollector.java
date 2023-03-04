package jolt.physics.collision;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

import static jolt.headers.JoltPhysicsC.*;

public abstract class CollisionCollector extends SegmentedJoltNative {
    protected CollisionCollector(MemorySegment segment) {
        super(segment);
    }

    public void reset() {
        JPC_CollisionCollector_Reset(handle);
    }

    public abstract float getEarlyOutFraction();

    public abstract TransformedShape getContext(MemorySession arena);

    public abstract void setContext(TransformedShape context);

    public void updateEarlyOutFraction(float fraction) {
        JPC_CollisionCollector_UpdateEarlyOutFraction(handle, fraction);
    }

    public void resetEarlyOutFraction(float fraction) {
        JPC_CollisionCollector_ResetEarlyOutFraction(handle, fraction);
    }

    public abstract void forceEarlyOut();

    public abstract boolean shouldEarlyOut();
}
