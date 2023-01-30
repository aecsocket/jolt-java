package jolt;

import javax.annotation.Nullable;

public class PhysicsSystem extends JoltNative {
    protected PhysicsSystem(long address) { super(address); }
    public static PhysicsSystem ofPointer(long address) { return new PhysicsSystem(address); }

    public PhysicsSystem(
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            BroadPhaseLayerInterface broadPhaseLayerInterface,
            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
            ObjectLayerPairFilter objectLayerPairFilter
    ) {
        address = _create(maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints, broadPhaseLayerInterface.address, objectVsBroadPhaseLayerFilter.address, objectLayerPairFilter.address);
    }
    private static native long _create(
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            long broadPhaseLayerInterface,
            long objectVsBroadPhaseLayerFilter,
            long objectLayerPairFilter
    );

    public @Nullable BodyActivationListener getBodyActivationListener() { return BodyActivationListener.ofPointer(_getBodyActivationListener(address)); }
    private static native long _getBodyActivationListener(long address);

    public void setBodyActivationListener(@Nullable BodyActivationListener listener) { _setBodyActivationListener(address, listener == null ? 0 : listener.address); }
    private static native void _setBodyActivationListener(long address, long listener);
}
