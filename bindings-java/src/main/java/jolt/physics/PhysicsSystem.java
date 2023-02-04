package jolt.physics;

import jolt.jni.*;
import jolt.JoltNative;
import jolt.physics.body.BodyActivationListener;
import jolt.physics.body.BodyInterface;
import jolt.physics.collision.ContactListener;
import jolt.physics.collision.ObjectLayerPairFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerInterface;
import jolt.physics.collision.broadphase.ObjectVsBroadPhaseLayerFilter;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/PhysicsSystem.h>")
@JniType("PhysicsSystem")
public final class PhysicsSystem extends JoltNative {
    private PhysicsSystem(long address) { super(address); }
    public static PhysicsSystem ref(long address) { return address == 0 ? null : new PhysicsSystem(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public PhysicsSystem() { address = _ctor(); }
    @JniBind("return (jlong) new PhysicsSystem();")
    private static native long _ctor();

    public void init(
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            BroadPhaseLayerInterface broadPhaseLayerInterface,
            ObjectVsBroadPhaseLayerFilter objectVsBroadPhaseLayerFilter,
            ObjectLayerPairFilter objectLayerPairFilter
    ) {
        _init(
                address,
                maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
                broadPhaseLayerInterface.getAddress(),
                objectVsBroadPhaseLayerFilter.getAddress(),
                objectLayerPairFilter.getAddress()
        );
    }
    @JniBindSelf("""
            self->Init(
                maxBodies, numBodyMutexes, maxBodyPairs, maxContactConstraints,
                *((BroadPhaseLayerInterface*) broadPhaseLayerInterface),
                *((ObjectVsBroadPhaseLayerFilter*) objectVsBroadPhaseLayerFilter),
                *((ObjectLayerPairFilter*) objectLayerPairFilter)
            );""")
    private static native void _init(
            long _a,
            int maxBodies,
            int numBodyMutexes,
            int maxBodyPairs,
            int maxContactConstraints,
            long broadPhaseLayerInterface,
            long objectVsBroadPhaseLayerFilter,
            long objectLayerPairFilter
    );

    public BodyInterface getBodyInterface() { return BodyInterface.ref(_getBodyInterface(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyInterface();")
    private static native long _getBodyInterface(long _a);

    public BodyInterface getBodyInterfaceNoLock() { return BodyInterface.ref(_getBodyInterfaceNoLock(address)); }
    @JniBindSelf("return (jlong) &self->GetBodyInterfaceNoLock();")
    private static native long _getBodyInterfaceNoLock(long _a);

    public @Nullable BodyActivationListener getBodyActivationListener() { return BodyActivationListener.ref(_getBodyActivationListener(address)); }
    @JniBindSelf("return (jlong) self->GetBodyActivationListener();")
    private static native long _getBodyActivationListener(long _a);

    public void setBodyActivationListener(@Nullable BodyActivationListener value) { _setBodyActivationListener(address, ptr(value)); }
    @JniBindSelf("self->SetBodyActivationListener((BodyActivationListener*) value);")
    private static native void _setBodyActivationListener(long _a, long value);

    public @Nullable ContactListener getContactListener() { return ContactListener.ref(_getContactListener(address)); }
    @JniBindSelf("return (jlong) self->GetContactListener();")
    private static native long _getContactListener(long _a);

    public void setContactListener(@Nullable ContactListener value) { _setContactListener(address, ptr(value)); }
    @JniBindSelf("self->SetContactListener((ContactListener*) value);")
    private static native void _setContactListener(long _a, long value);
}
