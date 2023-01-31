package jolt;

import jolt.physics.collision.broadphase.BroadPhaseLayer;

public class ObjectVsBroadPhaseLayerFilter extends JoltNative {
    protected ObjectVsBroadPhaseLayerFilter(long address) { super(address); }
    public static ObjectVsBroadPhaseLayerFilter ofPointer(long address) { return new ObjectVsBroadPhaseLayerFilter(address); }

    public ObjectVsBroadPhaseLayerFilter() {
        address = _create();
    }
    private native long _create();

    public boolean shouldCollide(int layer1, BroadPhaseLayer layer2) { throw unimplemented(); }
    private boolean _shouldCollide(int layer1, long layer2) { return shouldCollide(layer1, BroadPhaseLayer.ofPointer(layer2)); }
}
