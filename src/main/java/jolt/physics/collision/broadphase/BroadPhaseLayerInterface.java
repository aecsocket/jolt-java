package jolt.physics.collision.broadphase;

import jolt.JoltNative;

// must be overridden
public class BroadPhaseLayerInterface extends JoltNative {
    protected BroadPhaseLayerInterface(long address) { super(address); }
    public static BroadPhaseLayerInterface ofPointer(long address) { return new BroadPhaseLayerInterface(address); }

    public BroadPhaseLayerInterface() {
        address = _create();
    }
    private native long _create();

    public int getNumBroadPhaseLayers() { throw unimplemented(); }
    private int _getNumBroadPhaseLayers() { return getNumBroadPhaseLayers(); }

    public BroadPhaseLayer getBroadPhaseLayer(int layer) { throw unimplemented(); }
    private long _getBroadPhaseLayer(int layer) { return getBroadPhaseLayer(layer).getAddress(); }

    public String getBroadPhaseLayerName(BroadPhaseLayer layer) { throw unimplemented(); }
    private String _getBroadPhaseLayerName(long layer) { return getBroadPhaseLayerName(BroadPhaseLayer.ofPointer(layer)); }
}
