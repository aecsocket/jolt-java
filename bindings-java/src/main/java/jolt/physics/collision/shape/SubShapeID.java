package jolt.physics.collision.shape;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/Shape/SubShapeID.h>")
public final class SubShapeID extends JoltNative {
    private SubShapeID(long address) { super(address); }
    public static SubShapeID ref(long address) { return new SubShapeID(address); }
}
