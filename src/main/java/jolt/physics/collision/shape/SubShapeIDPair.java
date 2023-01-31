package jolt.physics.collision.shape;

import jolt.JoltNative;
import jolt.physics.body.BodyID;

public final class SubShapeIDPair extends JoltNative {
    private SubShapeIDPair(long address) { super(address); }
    public static SubShapeIDPair ofPointer(long address) { return new SubShapeIDPair(address); }

    public BodyID getBody1ID() { return BodyID.ofPointer(_getBody1ID(address)); }
    private static native long _getBody1ID(long address);

    public SubShapeID getSubShape1ID() { return SubShapeID.ofPointer(_getSubShape1ID(address)); }
    private static native long _getSubShape1ID(long address);

    public BodyID getBody2ID() { return BodyID.ofPointer(_getBody2ID(address)); }
    private static native long _getBody2ID(long address);

    public SubShapeID getSubShape2ID() { return SubShapeID.ofPointer(_getSubShape2ID(address)); }
    private static native long _getSubShape2ID(long address);

    public long hash() { return _hash(address); }
    private static native long _hash(long address);
}
