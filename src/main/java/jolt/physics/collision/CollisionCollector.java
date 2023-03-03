package jolt.physics.collision;

import jolt.SegmentedJoltNative;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

public abstract class CollisionCollector extends SegmentedJoltNative {
    protected CollisionCollector(MemorySegment segment) {
        super(segment);
    }

    public abstract void reset();

    public abstract float getEarlyOutFraction();

    public abstract TransformedShape getContext(MemorySession arena);

    public abstract void setContext(TransformedShape context);

    public abstract void updateEarlyOutFraction(float fraction);

    public abstract void resetEarlyOutFraction(float fraction);

    public abstract void forceEarlyOut();

    public abstract boolean shouldEarlyOut();
}
