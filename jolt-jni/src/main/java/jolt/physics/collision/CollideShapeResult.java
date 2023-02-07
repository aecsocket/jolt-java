package jolt.physics.collision;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Physics/Collision/CollideShape.h>")
@JniType("CollideShapeResult")
public class CollideShapeResult extends JoltNative {
    protected CollideShapeResult(long address) { super(address); }
    public static CollideShapeResult ref(long address) { return address == 0 ? null : new CollideShapeResult(address); }
}

