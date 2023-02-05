package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniBindSelf;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Body/Body.h>")
@JniType("Body")
public final class Body extends JoltNative {
    private Body(long address) { super(address); }
    public static Body ref(long address) { return address == 0 ? null : new Body(address); }

    public int getId() { return _getId(address); }
    @JniBindSelf("return (jint) self->GetID().GetIndexAndSequenceNumber();")
    private static native int _getId(long _a);
}
