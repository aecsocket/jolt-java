package jolt.physics.body;

import jolt.JoltNative;

public final class BodyID extends JoltNative {
    private BodyID(long address) { super(address); }
    public static BodyID ref(long address) { return address == 0 ? null : new BodyID(address); }
}
