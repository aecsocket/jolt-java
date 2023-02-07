package jolt.physics.collision.shape;

import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeIDPair.h>")
@JniType("SubShapeIDPair")
public final class SubShapeIdPair extends JoltNative {
    private SubShapeIdPair(long address) { super(address); }
    public static SubShapeIdPair ref(long address) { return address == 0 ? null : new SubShapeIdPair(address); }

    public int getBody1Id() { return _getBody1Id(address); }
    @JniBindSelf("return self->GetBody1ID().GetIndexAndSequenceNumber();")
    private static native int _getBody1Id(long _a);

    public int getSubShape1Id() { return _getSubShape1Id(address); }
    @JniBindSelf("return self->GetSubShapeID1().GetValue();")
    private static native int _getSubShape1Id(long _a);

    public int getBody2Id() { return _getBody2Id(address); }
    @JniBindSelf("return self->GetBody2ID().GetIndexAndSequenceNumber();")
    private static native int _getBody2Id(long _a);

    public int getSubShape2Id() { return _getSubShape2Id(address); }
    @JniBindSelf("return self->GetSubShapeID2().GetValue();")
    private static native int _getSubShape2Id(long _a);

    public long hash() { return _hash(address); }
    @JniBindSelf("return self->GetHash();")
    private static native long _hash(long _a);
}
