package jolt.physics.body;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Body/Body.h>")
public final class Body extends JoltNative {
    private Body(long address) { super(address); }
    public static Body ref(long address) { return address == 0 ? null : new Body(address); }
}
