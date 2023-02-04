package jolt.physics.collision.broadphase;

import jolt.jni.*;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
@JniType("BroadPhaseLayer")
public final class BroadPhaseLayer extends JoltNative {
    private BroadPhaseLayer(long address) { super(address); }
    public static BroadPhaseLayer ref(long address) { return address == 0 ? null : new BroadPhaseLayer(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);

    public static BroadPhaseLayer ofValue(byte value) { return new BroadPhaseLayer(_ofValue(value)); }
    @JniBind("return (jlong) new BroadPhaseLayer(value);")
    private static native long _ofValue(byte value);

    public int getValue() { return _getValue(address); }
    @JniBindSelf("return (int) (uint8) *self;")
    private static native int _getValue(long _a);

    @Override
    public boolean equals(Object o) {
        if (o instanceof BroadPhaseLayer other) {
            return getValue() == other.getValue();
        }
        return false;
    }
}
