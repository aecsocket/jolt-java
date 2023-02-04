package jolt.physics.collision.shape;

import jolt.jni.JniInclude;
import jolt.jni.JniBindSelf;
import jolt.JoltNative;
import jolt.jni.JniType;
import jolt.physics.body.BodyId;

@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeIDPair.h>")
@JniType("SubShapeIDPair")
public final class SubShapeIdPair extends JoltNative {
    private SubShapeIdPair(long address) { super(address); }
    public static SubShapeIdPair ref(long address) { return new SubShapeIdPair(address); }

    public BodyId getBody1Id() { return BodyId.ref(_getBody1Id(address)); }
    @JniBindSelf("return (long) &self->GetBody1ID();")
    private static native long _getBody1Id(long address);

    public SubShapeId getSubShape1Id() { return SubShapeId.ref(_getSubShape1Id(address)); }
    @JniBindSelf("return (long) &self->GetSubShapeID1();")
    private static native long _getSubShape1Id(long address);

    public BodyId getBody2Id() { return BodyId.ref(_getBody2Id(address)); }
    @JniBindSelf("return (long) &self->GetBody2ID();")
    private static native long _getBody2Id(long address);

    public SubShapeId getSubShape2Id() { return SubShapeId.ref(_getSubShape2Id(address)); }
    @JniBindSelf("return (long) &self->GetSubShapeID2();")
    private static native long _getSubShape2Id(long address);

    public long hash() { return _hash(address); }
    @JniBindSelf("return self->GetHash();")
    private static native long _hash(long address);
}
