package jolt.physics.collision.broadphase;

import jolt.JoltNative;

public class BroadPhaseLayer extends JoltNative {
    protected BroadPhaseLayer(long address) { super(address); }
    public static BroadPhaseLayer ofPointer(long address) { return new BroadPhaseLayer(address); }

    public static BroadPhaseLayer ofValue(byte value) { return new BroadPhaseLayer(_create(value)); }
    private static native long _create(byte value);

    public int getValue() { return _getValue(address); }
    private static native int _getValue(long address);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BroadPhaseLayer) {
            BroadPhaseLayer other = (BroadPhaseLayer) obj;
            return getValue() == other.getValue();
        }
        return false;
    }
}
