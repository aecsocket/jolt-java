package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNativeImpl;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/CollideShape.h>")
@JniTypeMapping("CollideShapeResult")
public class CollideShapeResult extends JoltNativeImpl {
    protected CollideShapeResult(long address) { super(address); }
    public static CollideShapeResult ref(long address) { return address == 0 ? null : new CollideShapeResult(address); }
}

