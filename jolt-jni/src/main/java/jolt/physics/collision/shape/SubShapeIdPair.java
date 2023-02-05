package jolt.physics.collision.shape;

import jolt.jni.JniInclude;
import jolt.jni.JniBindSelf;
import jolt.JoltNative;
import jolt.jni.JniType;
import jolt.physics.body.BodyIds;

@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeIDPair.h>")
@JniType("SubShapeIDPair")
public final class SubShapeIdPair extends JoltNative {
    private SubShapeIdPair(long address) { super(address); }
    public static SubShapeIdPair ref(long address) { return address == 0 ? null : new SubShapeIdPair(address); }

    public BodyIds getBody1Id() { return BodyIds.ref(_getBody1Id(address)); }
    @JniBindSelf("return (jlong) &self->GetBody1ID();")
    private static native long _getBody1Id(long _a);

    public SubShapeId getSubShape1Id() { return SubShapeId.ref(_getSubShape1Id(address)); }
    @JniBindSelf("return (jlong) &self->GetSubShapeID1();")
    private static native long _getSubShape1Id(long _a);

    public BodyIds getBody2Id() { return BodyIds.ref(_getBody2Id(address)); }
    @JniBindSelf("return (jlong) &self->GetBody2ID();")
    private static native long _getBody2Id(long _a);

    public SubShapeId getSubShape2Id() { return SubShapeId.ref(_getSubShape2Id(address)); }
    @JniBindSelf("return (jlong) &self->GetSubShapeID2();")
    private static native long _getSubShape2Id(long _a);

    public long hash() { return _hash(address); }
    @JniBindSelf("return self->GetHash();")
    private static native long _hash(long _a);
}
