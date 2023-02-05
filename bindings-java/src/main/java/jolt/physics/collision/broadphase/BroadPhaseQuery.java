package jolt.physics.collision.broadphase;

import jolt.JoltNative;
import jolt.jni.JniInclude;
import jolt.jni.JniType;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseQuery.h>")
@JniType("BroadPhaseQuery")
public class BroadPhaseQuery extends JoltNative {
    protected BroadPhaseQuery(long address) { super(address); }
    public static BroadPhaseQuery ref(long address) { return address == 0 ? null : new BroadPhaseQuery(address); }


}

