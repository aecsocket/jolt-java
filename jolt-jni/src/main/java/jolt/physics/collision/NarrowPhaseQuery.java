package jolt.physics.collision;

import io.github.aecsocket.jniglue.JniTypeMapping;
import jolt.JoltNative;
import io.github.aecsocket.jniglue.JniInclude;

@JniInclude("<Jolt/Physics/Collision/NarrowPhaseQuery.h>")
@JniTypeMapping("NarrowPhaseQuery")
public class NarrowPhaseQuery extends JoltNative {
    protected NarrowPhaseQuery(long address) { super(address); }
    public static NarrowPhaseQuery ref(long address) { return address == 0 ? null : new NarrowPhaseQuery(address); }


}

