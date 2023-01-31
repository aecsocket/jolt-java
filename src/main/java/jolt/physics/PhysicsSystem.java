package jolt.physics;

import jolt.JoltNative;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyInterface;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;

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
        address = _create(maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints, broadPhaseLayerInterface.getAddress(), objectVsBroadPhaseLayerFilter.getAddress(), objectLayerPairFilter.getAddress());
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

    public BodyInterface getBodyInterface() { return BodyInterface.ofPointer(_getBodyInterface(address)); }
    private static native long _getBodyInterface(long address);

    public BodyInterface getBodyInterfaceNoLock() { return BodyInterface.ofPointer(_getBodyInterfaceNoLock(address)); }
    private static native long _getBodyInterfaceNoLock(long address);

    public @Nullable BodyActivationListener getBodyActivationListener() { return BodyActivationListener.ofPointer(_getBodyActivationListener(address)); }
    private static native long _getBodyActivationListener(long address);

    public void setBodyActivationListener(@Nullable BodyActivationListener value) { _setBodyActivationListener(address, value == null ? 0 : value.getAddress()); }
    private static native void _setBodyActivationListener(long address, long value);

    public @Nullable ContactListener getContactListener() { return ContactListener.ofPointer(_getContactListener(address)); }
    private static native long _getContactListener(long address);

    public void setContactListener(@Nullable ContactListener value) { _setContactListener(address, value == null ? 0 : value.getAddress()); }
    private static native void _setContactListener(long address, long value);
}
