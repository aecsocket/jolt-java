package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniBindDelete;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/PhysicsMaterial.h>")
@JniTypeMapping("PhysicsMaterial")
public class PhysicsMaterial extends JoltNativeImpl {
    private PhysicsMaterial(long address) { super(address); }
    public static PhysicsMaterial ref(long address) { return address == 0 ? null : new PhysicsMaterial(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long _a);
}

