package jolt.physics.collision;

import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;
import io.github.aecsocket.jniglue.JniType;

@JniInclude("<Jolt/Physics/Collision/NarrowPhaseQuery.h>")
@JniType("NarrowPhaseQuery")
public class NarrowPhaseQuery extends JoltNative {
    protected NarrowPhaseQuery(long address) { super(address); }
    public static NarrowPhaseQuery ref(long address) { return address == 0 ? null : new NarrowPhaseQuery(address); }


}

