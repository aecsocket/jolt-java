package jolt.physics.body;

import jolt.JoltNative;

public final class BodyInterface extends JoltNative {
    private BodyInterface(long address) { super(address); }
    public static BodyInterface ofPointer(long address) { return new BodyInterface(address); }

}

