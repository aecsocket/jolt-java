package jolt;

public class ObjectLayerPairFilter extends JoltNative {
    protected ObjectLayerPairFilter(long address) { super(address); }
    public static ObjectLayerPairFilter ofPointer(long address) { return new ObjectLayerPairFilter(address); }

    public ObjectLayerPairFilter() {
        address = _create();
    }
    private native long _create();

    public boolean shouldCollide(int layer1, int layer2) { throw unimplemented(); }
    private boolean _shouldCollide(int layer1, int layer2) { return shouldCollide(layer1, layer2); }
}
