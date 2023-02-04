package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/CollideShape.h>")
@JniType("CollideShapeResult")
public class CollideShapeResult extends JoltNative {
    protected CollideShapeResult(long address) { super(address); }
    public static CollideShapeResult ref(long address) { return address == 0 ? null : new CollideShapeResult(address); }
}

