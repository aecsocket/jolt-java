package jolt.physics.collision.broadphase;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>")
@JniType("BroadPhaseQuery")
public class BroadPhaseQuery extends JoltNative {
    protected BroadPhaseQuery(long address) { super(address); }
    public static BroadPhaseQuery ref(long address) { return address == 0 ? null : new BroadPhaseQuery(address); }


}

