package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Body/BodyID.h>")
@JniType("BodyID")
public final class BodyId extends JoltNative {
    private BodyId(long address) { super(address); }
    public static BodyId ref(long address) { return address == 0 ? null : new BodyId(address); }
}
