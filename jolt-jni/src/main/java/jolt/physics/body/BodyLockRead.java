package jolt.physics.body;

import io.github.aecsocket.jniglue.JniBind;
import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;

@JniInclude("<Jolt/Physics/Body/BodyLock.h>")
@JniTypeMapping("BodyLockRead")
public final class BodyLockRead extends JoltNativeImpl implements BodyLockBase {
    private BodyLockRead(long address) { super(address); }
    public static BodyLockRead ref(long address) { return address == 0 ? null : new BodyLockRead(address); }

    public BodyLockRead(BodyLockInterface bodyLockInterface, int bodyId) {
        address = _ctor(bodyLockInterface.getAddress(), bodyId);
    }
    @JniBind("return (jlong) new BodyLockRead(*((BodyLockInterface*) bodyLockInterface), BodyID(bodyId));")
    private native long _ctor(long bodyLockInterface, int bodyId);

    @Override
    public boolean succeeded() { return _succeeded(address); }
    @JniBindSelf("return self->Succeeded();")
    private static native boolean _succeeded(long _a);

    @Override
    public boolean succeededAndIsInBroadPhase() { return _succeededAndIsInBroadPhase(address); }
    @JniBindSelf("return self->SucceededAndIsInBroadPhase();")
    private static native boolean _succeededAndIsInBroadPhase(long _a);

    @Override
    public Body getBody() { return Body.ref(_getBody(address)); }
    @JniBindSelf("return (jlong) &self->GetBody();")
    private static native long _getBody(long _a);
}
