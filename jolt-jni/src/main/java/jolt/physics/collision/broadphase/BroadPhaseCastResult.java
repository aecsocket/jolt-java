package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.JniBindSelf;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.physics.collision.shape.ConvexShape;

@JniInclude("<Jolt/Physics/Collision/CastResult.h>")
@JniTypeMapping("BroadPhaseCastResult")
public class BroadPhaseCastResult extends ConvexShape {
    protected BroadPhaseCastResult(long address) { super(address); }
    public static BroadPhaseCastResult ref(long address) { return address == 0 ? null : new BroadPhaseCastResult(address); }

    public int getBodyId() { return _getBodyId(address); }
    @JniBindSelf("return self->mBodyID.GetIndexAndSequenceNumber();")
    private static native int _getBodyId(long _a);

    public float getFraction() { return _getFraction(address); }
    @JniBindSelf("return self->mFraction;")
    private static native float _getFraction(long _a);
}
