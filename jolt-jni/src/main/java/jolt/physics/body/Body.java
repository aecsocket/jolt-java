package jolt.physics.body;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniBindSelf;

@JniInclude("<Jolt/Physics/Body/Body.h>")
@JniTypeMapping("Body")
public final class Body extends JoltNative {
    private Body(long address) { super(address); }
    public static Body ref(long address) { return address == 0 ? null : new Body(address); }

    public int getId() { return _getId(address); }
    @JniBindSelf("return (jint) self->GetID().GetIndexAndSequenceNumber();")
    private static native int _getId(long _a);

    public boolean isActive() { return _isActive(address); }
    @JniBindSelf("return self->IsActive();")
    private static native boolean _isActive(long _a);

    public boolean isStatic() { return _isStatic(address); }
    @JniBindSelf("return self->IsStatic();")
    private static native boolean _isStatic(long _a);

    public boolean isKinematic() { return _isKinematic(address); }
    @JniBindSelf("return self->IsKinematic();")
    private static native boolean _isKinematic(long _a);

    public boolean isDynamic() { return _isDynamic(address); }
    @JniBindSelf("return self->IsDynamic();")
    private static native boolean _isDynamic(long _a);

    public MotionProperties getMotionProperties() {
        if (isStatic()) throw new IllegalStateException("Body is static");
        return MotionProperties.ref(_getMotionProperties(address));
    }
    // unchecked since we check `isStatic()` above
    @JniBindSelf("return (jlong) self->GetMotionPropertiesUnchecked();")
    private static native long _getMotionProperties(long _a);
}
