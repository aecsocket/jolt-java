package jolt.physics.body;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import jolt.core.SharedMutex;

import javax.annotation.Nullable;

@JniInclude("<Jolt/Physics/Body/BodyLockInterface.h>")
@JniTypeMapping("BodyLockInterface")
public final class BodyLockInterface extends JoltNativeImpl {
    private BodyLockInterface(long address) { super(address); }
    public static BodyLockInterface ref(long address) { return address == 0 ? null : new BodyLockInterface(address); }

    public SharedMutex lockRead(int bodyId) { return SharedMutex.ref(_lockRead(address, bodyId)); }
    @JniBindSelf("return (jlong) self->LockRead(BodyID(bodyId));")
    private static native long _lockRead(long _a, int bodyId);

    public void unlockRead(SharedMutex mutex) { _unlockRead(address, mutex.getAddress()); }
    @JniBindSelf("self->UnlockRead((SharedMutex*) mutex);")
    private static native void _unlockRead(long _a, long mutex);

    public SharedMutex lockWrite(int bodyId) { return SharedMutex.ref(_lockWrite(address, bodyId)); }
    @JniBindSelf("return (jlong) self->LockWrite(BodyID(bodyId));")
    private static native long _lockWrite(long _a, int bodyId);

    public void unlockWrite(SharedMutex mutex) { _unlockWrite(address, mutex.getAddress()); }
    @JniBindSelf("self->UnlockWrite((SharedMutex*) mutex);")
    private static native void _unlockWrite(long _a, long mutex);

    public @Nullable BodyImpl tryGetBody(int bodyId) { return BodyImpl.ref(_tryGetBody(address, bodyId)); }
    @JniBindSelf("return (jlong) self->TryGetBody(BodyID(bodyId));")
    private static native long _tryGetBody(long _a, int bodyId);
}
