package jolt;

public class ObjectLayerPairFilter extends JoltNative {
    protected ObjectLayerPairFilter(long address) { super(address); }
    public static ObjectLayerPairFilter ofPointer(long address) { return new ObjectLayerPairFilter(address); }

    public ObjectLayerPairFilter() {
        super(_create());
    }
    private static native long _create();

    public boolean shouldCollide(int layer1, int layer2) { throw unsupported(); }
}
