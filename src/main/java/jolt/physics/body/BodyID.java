package jolt.physics.body;

import jolt.JoltNative;

public final class BodyID extends JoltNative {
    private BodyID(long address) { super(address); }
    public static BodyID ofPointer(long address) { return new BodyID(address); }
}
