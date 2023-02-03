package jolt.physics.collision.broadphase;

import jolt.JoltNative;

public class ObjectVsBroadPhaseLayerFilter extends JoltNative {
    private ObjectVsBroadPhaseLayerFilter(long address) { super(address); }
    public static ObjectVsBroadPhaseLayerFilter ref(long address) { return address == 0 ? null : new ObjectVsBroadPhaseLayerFilter(address); }

    public ObjectVsBroadPhaseLayerFilter() { address = _ctor(); }
    private native long _ctor();

    public boolean shouldCollide(int layer1, BroadPhaseLayer layer2) { throw unimplemented(); }
    private boolean _shouldCollide(int layer1, long layer2) { return shouldCollide(layer1, BroadPhaseLayer.ref(layer2)); }
}
