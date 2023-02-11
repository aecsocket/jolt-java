package jolt.physics.collision.broadphase;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>")
@JniTypeMapping("BroadPhaseQuery")
public class BroadPhaseQuery extends JoltNative {
    protected BroadPhaseQuery(long address) { super(address); }
    public static BroadPhaseQuery ref(long address) { return address == 0 ? null : new BroadPhaseQuery(address); }


}

