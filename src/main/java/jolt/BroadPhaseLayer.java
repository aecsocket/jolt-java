package jolt;

public class BroadPhaseLayer extends JoltNative {
    protected BroadPhaseLayer(long address) { super(address); }
    public static BroadPhaseLayer ofPointer(long address) { return new BroadPhaseLayer(address); }

    public BroadPhaseLayer(byte value) {
        address = _create(value);
    }
    private static native long _create(byte value);

    public int getValue() { return _getValue(address); }
    private static native int _getValue(long address);
}
