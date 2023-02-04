package jolt.physics.collision.broadphase;

import jolt.jni.JniInclude;
import jolt.jni.JniSelfBind;
import jolt.JoltNative;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
public final class BroadPhaseLayer extends JoltNative {
    private BroadPhaseLayer(long address) { super(address); }
    public static BroadPhaseLayer ref(long address) { return address == 0 ? null : new BroadPhaseLayer(address); }

    public static BroadPhaseLayer ofValue(byte value) { return new BroadPhaseLayer(_ofValue(value)); }
    private static native long _ofValue(byte value);

    public int getValue() { return _getValue(address); }
    @JniSelfBind("return (jint) (uint8) *self;")
    private static native int _getValue(long address);
}
