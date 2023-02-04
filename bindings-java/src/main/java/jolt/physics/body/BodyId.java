package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniBindSelf;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Body/BodyID.h>")
@JniType("BodyID")
public final class BodyId extends JoltNative {
    private BodyId(long address) { super(address); }
    public static BodyId ref(long address) { return address == 0 ? null : new BodyId(address); }

    public int getIndex() { return _getIndex(address); }
    @JniBindSelf("return self->GetIndex();")
    private static native int _getIndex(long _a);

    public byte getSequenceNumber() { return _getSequenceNumber(address); }
    @JniBindSelf("return self->GetSequenceNumber();")
    private static native byte _getSequenceNumber(long _a);

    public int getIndexAndSequenceNumber() { return _getIndexAndSequenceNumber(address); }
    @JniBindSelf("return self->GetIndexAndSequenceNumber();")
    private static native int _getIndexAndSequenceNumber(long _a);

    @Override
    public boolean equals(Object o) {
        if (o instanceof BodyId other) {
            return getIndexAndSequenceNumber() == other.getIndexAndSequenceNumber();
        }
        return false;
    }
}
