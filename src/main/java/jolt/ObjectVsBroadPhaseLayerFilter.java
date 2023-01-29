package jolt;

public class ObjectVsBroadPhaseLayerFilter extends JoltNative {
    protected ObjectVsBroadPhaseLayerFilter(long address) { super(address); }
    public static ObjectVsBroadPhaseLayerFilter ofPointer(long address) { return new ObjectVsBroadPhaseLayerFilter(address); }

    public ObjectVsBroadPhaseLayerFilter() {
        super(_create());
    }
    private static native long _create();

    public boolean shouldCollide(int layer1, BroadPhaseLayer layer2) { throw unsupported(); }
}
