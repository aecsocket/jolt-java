package jolt.physics.collision.shape;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeID.h>")
@JniType("SubShapeID")
public final class SubShapeId extends JoltNative {
    private SubShapeId(long address) { super(address); }
    public static SubShapeId ref(long address) { return address == 0 ? null : new SubShapeId(address); }
}
