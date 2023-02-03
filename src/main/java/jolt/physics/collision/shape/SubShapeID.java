package jolt.physics.collision.shape;

import jolt.JoltNative;

public final class SubShapeID extends JoltNative {
    private SubShapeID(long address) { super(address); }
    public static SubShapeID ref(long address) { return new SubShapeID(address); }
}
