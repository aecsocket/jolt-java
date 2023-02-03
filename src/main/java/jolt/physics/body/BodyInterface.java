package jolt.physics.body;

import jolt.JoltNative;

public final class BodyInterface extends JoltNative {
    private BodyInterface(long address) { super(address); }
    public static BodyInterface ref(long address) { return address == 0 ? null : new BodyInterface(address); }
}
