package jolt;

// must be overridden
public class BroadPhaseLayerInterface extends JoltNative {
    protected BroadPhaseLayerInterface(long address) { super(address); }
    public static BroadPhaseLayerInterface ofPointer(long address) { return new BroadPhaseLayerInterface(address); }

    public BroadPhaseLayerInterface() {
        address = _create();
    }
    private native long _create();

    public int getNumBroadPhaseLayers() { throw unsupported(); }
    private int _getNumBroadPhaseLayers() { return getNumBroadPhaseLayers(); }

    public BroadPhaseLayer getBroadPhaseLayer(int layer) { throw unsupported(); }
    private long _getBroadPhaseLayer(int layer) { return getBroadPhaseLayer(layer).address; }

    public String getBroadPhaseLayerName(BroadPhaseLayer layer) { throw unsupported(); }
    private String _getBroadPhaseLayerName(long layer) { return getBroadPhaseLayerName(BroadPhaseLayer.ofPointer(layer)); }
}
