package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Body/BodyID.h>")
public final class BodyID extends JoltNative {
    private BodyID(long address) { super(address); }
    public static BodyID ref(long address) { return address == 0 ? null : new BodyID(address); }
}
