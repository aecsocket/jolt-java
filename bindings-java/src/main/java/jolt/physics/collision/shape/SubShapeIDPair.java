package jolt.physics.collision.shape;

import jolt.jni.JniInclude;
import jolt.jni.JniSelfBind;
import jolt.JoltNative;
import jolt.jni.JniType;
import jolt.physics.body.BodyID;

@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeIDPair.h>")
@JniType("SubShapeIDPair")
public final class SubShapeIDPair extends JoltNative {
    private SubShapeIDPair(long address) { super(address); }
    public static SubShapeIDPair ref(long address) { return new SubShapeIDPair(address); }

    public BodyID getBody1ID() { return BodyID.ref(_getBody1ID(address)); }
    @JniSelfBind("return (long) &self->GetBody1ID();")
    private static native long _getBody1ID(long address);

    public SubShapeID getSubShape1ID() { return SubShapeID.ref(_getSubShape1ID(address)); }
    @JniSelfBind("return (long) &self->GetSubShapeID1();")
    private static native long _getSubShape1ID(long address);

    public BodyID getBody2ID() { return BodyID.ref(_getBody2ID(address)); }
    @JniSelfBind("return (long) &self->GetBody2ID();")
    private static native long _getBody2ID(long address);

    public SubShapeID getSubShape2ID() { return SubShapeID.ref(_getSubShape2ID(address)); }
    @JniSelfBind("return (long) &self->GetSubShapeID2();")
    private static native long _getSubShape2ID(long address);

    public long hash() { return _hash(address); }
    @JniSelfBind("return self->GetHash();")
    private static native long _hash(long address);
}
