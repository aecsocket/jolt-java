package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniBindDelete;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/PhysicsMaterial.h>")
@JniType("PhysicsMaterial")
public class PhysicsMaterial extends JoltNative {
    private PhysicsMaterial(long address) { super(address); }
    public static PhysicsMaterial ref(long address) { return new PhysicsMaterial(address); }

    @Override
    public void delete() {
        if (address == 0L) throw new IllegalStateException(NATIVE_OBJECT_DELETED);
        _delete(address);
        address = 0;
    }
    @JniBindDelete
    private static native void _delete(long address);
}

