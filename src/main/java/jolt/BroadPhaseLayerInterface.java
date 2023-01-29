package jolt;

// must be overridden
public class BroadPhaseLayerInterface extends JoltNative {
    protected BroadPhaseLayerInterface(long address) { super(address); }
    public static BroadPhaseLayerInterface ofPointer(long address) { return new BroadPhaseLayerInterface(address); }

    public BroadPhaseLayerInterface() {
        super(_create());
    }
    private static native long _create();

    public int getNumBroadPhaseLayers() { throw unsupported(); }
    public BroadPhaseLayer getBroadPhaseLayer(int layer) { throw unsupported(); }
}
