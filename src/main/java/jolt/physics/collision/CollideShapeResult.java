package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/CollideShape.h>")
public class CollideShapeResult extends JoltNative {
    protected CollideShapeResult(long address) { super(address); }
    public static CollideShapeResult ref(long address) { return new CollideShapeResult(address); }

}

